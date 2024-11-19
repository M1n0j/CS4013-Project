import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataHandler {
    private static final String EMPLOYEE_FILE_PATH = "src/resources/employees.csv";

    /**
     * Reads the employee data from a CSV file and returns a list of Employee objects.
     */
    public static List<Employee> readEmployees() {
        List<Employee> employees = new ArrayList<>();
        File file = new File(EMPLOYEE_FILE_PATH);

        if (!file.exists()) {
            System.out.println("Employee file not found. Starting with an empty list.");
            return employees; // Return empty list if the file does not exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Skip the header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                employees.add(Employee.fromCSV(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading employee file: " + e.getMessage());
        }

        return employees;
    }

    /**
     * Writes the list of Employee objects to the CSV file.
     */
    public static void writeEmployees(List<Employee> employees) {
        File file = new File(EMPLOYEE_FILE_PATH);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write the header
            writer.write("employeeId,name,email,role,salaryScale,currentPoint,isFullTime,yearsAtTopPoint");
            writer.newLine();

            // Write employee data
            for (Employee employee : employees) {
                writer.write(employee.toCSV());
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error writing to employee file: " + e.getMessage());
        }
    }
}
