import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private static String employeeCSVPath;
    private static String userCSVPath;
    private static String salaryCSVPath;

    // Constructor accepting both paths
    public CSVReader(String employeeCSVPath, String userCSVPath, String salaryCSVPath) {
        this.employeeCSVPath = employeeCSVPath;
        this.userCSVPath = userCSVPath;
        this.salaryCSVPath = salaryCSVPath;
    }

    /**
     * Reads employee data from the employee CSV file and returns a list of Employee objects.
     * Includes file existence check and improved error handling.
     */
    public static List<Employee> readEmployees(String employeeCSVPath) {
        List<Employee> employees = new ArrayList<>();

        // Add file existence check
        File file = new File(employeeCSVPath);
        if (!file.exists()) {
            System.err.println("Error: Employee CSV file not found at " + employeeCSVPath);
            System.err.println("Current working directory: " + System.getProperty("user.dir"));
            return employees;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(employeeCSVPath))) {
            // Skip header line
            String header = reader.readLine();
            if (header == null) {
                System.err.println("CSV file is empty or cannot be read.");
                return employees;
            }

            String line;
            while ((line = reader.readLine()) != null) {
                // Debug print to see what's being read
                System.out.println("Reading line: " + line);

                // Split the line and validate fields
                String[] fields = line.split(",");

                // Ensure you have enough fields
                if (fields.length >= 6) {
                    boolean isFullTime = Boolean.parseBoolean(fields[5]);
                    if (isFullTime) {
                        // Full-time employee
                        employees.add(FullTimeEmployee.fromCSV(line));
                    } else {
                        // Part-time employee
                        employees.add(PartTimeEmployee.fromCSV(line));
                    }
                } else {
                    System.err.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading employee file: " + e.getMessage());
            e.printStackTrace();
        }

        return employees;
    }

    // Other methods from the original class remain the same
    public List<User> readUsers() {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(userCSVPath))) {
            String line;

            // Skip header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                // Create a User object from the CSV line and add it to the list
                users.add(User.fromCSV(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading user file: " + e.getMessage());
        }

        return users;
    }

    // Method to read SalaryScales from a CSV file
    public static List<SalaryScale> readSalaryScales(String salaryScaleCSVPath) {
        List<SalaryScale> salaryScales = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(salaryScaleCSVPath))) {
            // Skip the header
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                // Ensure we have at least 3 fields
                if (fields.length >= 3) {
                    String position = fields[0];

                    // Check if level is "N/A" or empty and handle accordingly
                    String levelString = fields[1];
                    int level = 1;
                    if (!levelString.isEmpty() && !levelString.equalsIgnoreCase("N/A")) {
                        level = Integer.parseInt(levelString);
                    }

                    // Check if salary is "N/A" or empty and handle accordingly
                    String salaryString = fields[2];
                    double salary = 0.0;
                    if (!salaryString.isEmpty() && !salaryString.equalsIgnoreCase("N/A")) {
                        salary = Double.parseDouble(salaryString);
                    }

                    salaryScales.add(new SalaryScale(position, level, salary));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading salary scale file: " + e.getMessage());
        }

        return salaryScales;
    }
}