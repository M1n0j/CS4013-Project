import java.util.Scanner;

/**
 * The HrMenu class provides a menu interface for hr users
 * Allows hr users to implement promotions or log out of the system
 */
public class HRMenu {
    private Scanner scanner;
    private HrUser hrUser;

    public HRMenu(Scanner scanner, int userId) {
        this.scanner = scanner;
        this.hrUser = new HrUser(Integer.toString(userId),"password");
    }

    /**
     * Displays the Human Resources menu and handles user actions.
     * HR users can choose to implement promotions or log out of the system.
     */
    public void displayMenu() {
        boolean hrMenuRunning = true;
        while (hrMenuRunning) {
            System.out.println("\nHuman Resources Menu:");
            System.out.println("1. Implement Promotion");
            System.out.println("2. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                hrUser.promoteEmployee(scanner);
            } else if (choice == 2) {
                hrMenuRunning = false;
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }
}