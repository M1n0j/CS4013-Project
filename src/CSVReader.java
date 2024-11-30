import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.Position;

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
     * Reads user data from the user CSV file and returns a list of User objects.
     */
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

    /**
     * Reads employee data from the employee CSV file and returns a list of Employee objects.
     */
    public static List<Employee> readEmployees(String EmployeeCSVPath) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(employeeCSVPath))) {
            String line;

            // Skip header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                // Create an Employee object from the CSV line and add it to the list
                String[] fields = line.split(",");
                boolean isFullTime = Boolean.parseBoolean(fields[5]);
                if (isFullTime) {
                    // Full-time employee
                    employees.add(FullTimeEmployee.fromCSV(line));
                } else {
                    // Part-time employee
                    employees.add(PartTimeEmployee.fromCSV(line));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading employee file: " + e.getMessage());
        }

        return employees;
    }

    /**
     * Reads Salary data from SalaryCSV and returns list of salarys
     */

    // Method to read SalaryScale data from CSV
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
                    String position = fields[0].trim();

                    // Check if level is "N/A" or empty and handle accordingly
                    String levelString = fields[1].trim();
                    int level = 0;
                    if (!levelString.isEmpty() && !levelString.equalsIgnoreCase("N/A")) {
                        level = Integer.parseInt(levelString);
                    }

                    // Check if salary is "N/A" or empty and handle accordingly
                    String salaryString = fields[2].trim();
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
