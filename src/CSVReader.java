import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private static String employeeCSVPath;
    private static String userCSVPath;

    // Constructor accepting both paths
    public CSVReader(String employeeCSVPath, String userCSVPath) {
        this.employeeCSVPath = employeeCSVPath;
        this.userCSVPath = userCSVPath;
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

}
