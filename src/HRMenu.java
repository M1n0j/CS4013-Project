import java.util.List;
import java.util.Scanner;

public class HRMenu {
    private Scanner scanner;

    public HRMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        boolean hrMenuRunning = true;
        while (hrMenuRunning) {
            System.out.println("\nHuman Resources Menu:");
            System.out.println("1. Implement Promotion");
            System.out.println("2. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline character

            if (choice == 1) {
                System.out.println("Implementing promotion...");
                promoteEmployee(scanner);
            } else if (choice == 2) {
                hrMenuRunning = false;
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    private void promoteEmployee(Scanner inputScanner) {

        List<Employee> employees = CSVReader.readEmployees("src/resources/Employees.csv");
        System.out.println("Select the employee ID of the employee you wish to promote:");
        int employeeID = inputScanner.nextInt();
        inputScanner.nextLine();

        Employee employeeToPromote = null;

        for(Employee employee : employees) {
            if(employee.getEmployeeId() == employeeID) {
                employeeToPromote = employee;
                break;
            }
        }

        if(employeeToPromote == null) {
            System.out.println("Employee ID not found");
            return;
        }

        employeeToPromote.setPromotion(true);
        //  CSVReader.writeEmployees(employees, "Employees.csv");

        System.out.println("Employee " + employeeID + ", awaiting employee approval");
    }
}
