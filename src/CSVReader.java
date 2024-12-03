import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading data from various csv files
 * This class includes methods to read employee, user, and salary scale data into their object lists
 */

public class CSVReader {
    private String employeeCSVPath;
    private String userCSVPath;
    private String salaryCSVPath;



    /**
     * Constructs a CSVReader object with the specified paths for employee, user, and salary csv files
     * @param employeeCSVPath The file path for the employee csv file
     * @param userCSVPath     The file path for the user csv file
     * @param salaryCSVPath   The file path for the salary csv file
     */

    public CSVReader(String employeeCSVPath, String userCSVPath, String salaryCSVPath) {
        this.employeeCSVPath = employeeCSVPath;
        this.userCSVPath = userCSVPath;
        this.salaryCSVPath = salaryCSVPath;
    }

    /**
     * Reads employee data from the employee csv file and returns a list of Employee objects
     * Differentiates between full-time and part-time employees based on the "isFullTime" field
     * @return A list of Employee objects read from the csv file. Returns an empty list if the file is missing or invalid.
     */
    public List<Employee> readEmployees(String s) {
        List<Employee> employees = new ArrayList<>();


        File file = new File("/src/Resources/Employees.csv");
        if (!file.exists()) {
            System.err.println("Error: Employee CSV file not found at " + "/src/Resources/Employees.csv");
            System.err.println("Current working directory: " + System.getProperty("user.dir"));
            return employees;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("/src/Resources/Employees.csv"))) {

            String header = reader.readLine();
            if (header == null) {
                System.err.println("CSV file is empty or cannot be read.");
                return employees;
            }

            String line;
            while ((line = reader.readLine()) != null) {

                String[] fields = line.split(",");


                if (fields.length >= 6) {
                    boolean isFullTime = Boolean.parseBoolean(fields[5]);
                    if (isFullTime) {
                        employees.add(FullTimeEmployee.fromCSV(line));
                    } else {
                        employees.add(PartTimeEmployee.fromCSV(line));
                    }
                } else {
                    System.err.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading employee file: " + e.getMessage());
        }

        return employees;
    }

    /**
     * Reads user data from the user csv file and returns a list of User objects
     * @return A list of User objects read from the csv file. Returns an empty list if the file is missing or invalid
     */

    public List<User> readUsers() {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("/src/Resources/user.csv"))) {
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                users.add(User.fromCSV(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading user file: " + e.getMessage());
        }

        return users;
    }

    /**
     * Reads salary scale data from the salary csv file and returns a list of SalaryScale objects
     * @return A list of SalaryScale objects read from the csv file. Returns an empty list if the file is missing or invalid
     */

    public List<SalaryScale> readSalaryScales() {
        List<SalaryScale> salaryScales = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("/src/Resources/Salaries.csv"))) {
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                if (fields.length >= 3) {
                    String position = fields[0];

                    String levelString = fields[1];
                    int level = 1;
                    if (!levelString.isEmpty() && !levelString.equalsIgnoreCase("N/A")) {
                        level = Integer.parseInt(levelString);
                    }

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