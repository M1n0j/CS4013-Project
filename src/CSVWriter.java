import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    private static String employeeCSVPath;
    private String userCSVPath;

    // Constructor accepting both paths
    public CSVWriter(String employeeCSVPath, String userCSVPath) {
        this.employeeCSVPath = employeeCSVPath;
        this.userCSVPath = userCSVPath;
    }

    /**
     * Writes a list of User objects to the user CSV file.
     */
    public void writeUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userCSVPath))) {
            // Write header
            writer.write("id,name,email"); // Adjust this header based on your User fields
            writer.newLine();

            for (User  user : users) {
                writer.write(user.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing user file: " + e.getMessage());
        }
    }

    /**
     * Writes a list of Employee objects to the employee CSV file.
     */
    public static void writeEmployees(List<Employee> employees, String s) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(employeeCSVPath))) {
            // Write header
            writer.write("id,name,department,salary,isFullTime"); // Adjust this header based on your Employee fields
            writer.newLine();

            for (Employee employee : employees) {
                writer.write(employee.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing employee file: " + e.getMessage());
        }
    }

    /**
     * Writes a
     */


}