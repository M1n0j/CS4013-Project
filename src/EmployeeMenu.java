import java.util.Scanner;

public class EmployeeMenu {
    private Scanner scanner;

    public EmployeeMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        boolean employeeMenuRunning = true;
        while (employeeMenuRunning) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. View My Details");
            System.out.println("2. View My Payslips");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline character

            if (choice == 1) {
                System.out.println("Viewing employee details...");
                // Add functionality here
            } else if (choice == 2) {
                System.out.println("Viewing my payslips...");
                // Add functionality here
            } else if (choice == 3) {
                employeeMenuRunning = false; // Go back to main menu
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }
}

