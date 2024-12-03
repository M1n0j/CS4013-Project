import java.util.List;
import java.util.Scanner;

/**
 * Represents an HR user in the system with the ability to manage employee promotions
 * Extends the User class to provide HR-specific functionality
 */
public class HrUser extends User {

    /**
     * Constructs an HrUser object with the specified employee ID and password
     * @param employeeID The unique identifier for the HR user
     * @param password   The password for the HR user
     */
    public HrUser(String employeeID, String password) {
        super(employeeID, password);
    }

    /**
     * Promotes an employee by increasing their level and marking them as eligible for promotion
     * Updates the employee records in the csv file
     * @param inputScanner The scanner object used for user input
     * @throws IllegalArgumentException If the scanner is null
     */
    public void promoteEmployee(Scanner inputScanner) {
        if (inputScanner == null) {
            System.out.println("Invalid input scanner.");
            return;
        }


        List<Employee> employees = csvReader.readEmployees("src/Resources/Employees.csv");

        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\nEmployees:");
        for (Employee employee : employees) {
            System.out.println("ID: " + employee.getEmployeeId() + ", Name: " + employee.getName());
        }


        System.out.print("Enter employee ID to promote: ");
        int employeeID = inputScanner.nextInt();
        inputScanner.nextLine();

        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeID) {
                employee.setPromotion(true);
                int newLevel = employee.getLevel() + 1;
                employee.setLevel(newLevel);
                csvWriter.writeEmployees(employees, "src/Resources/employees.csv");
                System.out.println("Employee promoted.");
                return;
            }
        }

        System.out.println("Employee not found.");
    }
}