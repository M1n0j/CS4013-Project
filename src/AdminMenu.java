import java.util.Scanner;

public class AdminMenu {

    private Scanner scanner;

    public AdminMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        boolean adminMenuRunning = true;
        while (adminMenuRunning) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add New Employee");
            System.out.println("2. View Employee Details");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline character

            if (choice == 1) {
                System.out.println("Adding a new employee...");
            } else if (choice == 2) {
                System.out.println("Viewing employee details...");
            } else if (choice == 3) {
                adminMenuRunning = false;
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }
}
