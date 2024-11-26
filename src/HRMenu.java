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
            } else if (choice == 2) {
                hrMenuRunning = false;
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }
}
