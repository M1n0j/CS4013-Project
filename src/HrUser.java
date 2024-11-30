import java.util.List;
import java.util.Scanner;

public class HrUser extends User {

    public HrUser(String employeeID, String password) {
        super(employeeID, password);
    }

    static void promoteEmployee(Scanner inputScanner) {
        // Null checks and error handling
        if (inputScanner == null) {
            System.out.println("Invalid input scanner.");
            return;
        }

        // Read employees from CSV
        List<Employee> employees = CSVReader.readEmployees("src/resources/employees.csv");
        
        // Check if employee list is empty
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees found in the system.");
            return;
        }

        // Display employees
        System.out.println("Current Employees:");
        for (Employee employee : employees) {
            System.out.println("Employee ID: " + employee.getEmployeeId() + 
                               ", Name: " + employee.getName());
        }

        // Prompt for employee ID
        System.out.println("Select the employee ID of the employee you wish to promote:");
        
        // Added error handling for input
        int employeeID;
        try {
            employeeID = inputScanner.nextInt();
            inputScanner.nextLine(); // Clear newline
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid employee ID.");
            inputScanner.nextLine(); // Clear any invalid input
            return;
        }

        // Find employee
        Employee employeeToPromote = null;
        for (Employee employee : employees) {
            // Use int comparison if getEmployeeId() returns an int
            if (employee.getEmployeeId() == employeeID) {
                employeeToPromote = employee;
                break;
            }
        }

        // Validate employee selection
        if (employeeToPromote == null) {
            System.out.println("Employee ID not found");
            return;
        }

        // Confirm promotion
        System.out.println("Confirm promotion for " + employeeToPromote.getName() + "? (yes/no)");
        String confirm = inputScanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            // Set promotion status
            employeeToPromote.setPromotion(true);

            // Write updated employees back to CSV
            try {
                CSVWriter.writeEmployees(employees, "Employees.csv");
                System.out.println("Employee " + employeeID + " is awaiting employee approval");
            } catch (Exception e) {
                System.out.println("Error saving promotion: " + e.getMessage());
            }
        } else {
            System.out.println("Promotion cancelled.");
        }
    }
}