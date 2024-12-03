import java.util.Scanner;

public class HRMenu {
    private Scanner scanner;
    private HrUser hrUser;

    public HRMenu(Scanner scanner, int userId) {
        this.scanner = scanner;
        this.hrUser = new HrUser(Integer.toString(userId),"password");
    }

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