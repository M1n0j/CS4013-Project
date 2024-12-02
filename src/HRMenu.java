import java.util.Scanner;

public class HRMenu {
    private Scanner scanner;
    private HrUser hrUser; // Add a reference to HrUser

    // Modify the constructor to accept an HrUser
    public HRMenu(Scanner scanner) {
        this.scanner = scanner;
        this.hrUser = hrUser; // Store the HrUser reference
    }

    public void displayMenu() {
        boolean hrMenuRunning = true;
        while (hrMenuRunning) {
            System.out.println("Human Resources Menu:");
            System.out.println("1. Implement Promotion");
            System.out.println("2. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline character

            if (choice == 1) {
                // Call the promotion method from the HrUser instance
                HrUser.promoteEmployee(scanner);
            } else if (choice == 2) {
                hrMenuRunning = false;
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }
}