import java.util.Scanner;

public class FullTimeMenu {
    private Scanner scanner;
    private String[] userDetails;

    public FullTimeMenu(Scanner scanner) {
        this.scanner = scanner;
        this.userDetails = userDetails;
    }

    public void displayMenu() {
        boolean fullTimeMenuRunning = true;
        while (fullTimeMenuRunning) {
            System.out.println("\nFull-Time Employee Menu:");
            System.out.println("1. View My Details");
            System.out.println("2. View My Most Recent Payslip");
            System.out.println("3. View My Historical Payslips");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline character

            if (choice == 1) {
                System.out.println("\nEmployee Details:");
                // Display user details
            } else if (choice == 2) {
                System.out.println("\nMost Recent Payslip:");
                // Display most recent payslip
            } else if (choice == 3) {
                System.out.println("\nHistorical Payslips:");
                // Display historical payslips
            } else if (choice == 4) {
                fullTimeMenuRunning = false;
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }
}
