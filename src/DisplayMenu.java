import java.util.Scanner;

public class DisplayMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Main Menu
            System.out.print("Enter your UserID: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear the newline character left in the input buffer

            switch (choice) {
                case 1:
                    // Employee Menu
                    if (authenticate(scanner, EMPLOYEE_PASSWORD)) {
                        boolean employeeMenuRunning = true;
                        while (employeeMenuRunning) {
                            System.out.println("\nEmployee Menu:");
                            System.out.println("1. View Personal Details");
                            System.out.println("2. View Most Recent Payslip");
                            System.out.println("3. View Historical Payslips");
                            System.out.println("4. Confirm Promotion Changes");
                            System.out.println("5. Back to Main Menu");
                            System.out.print("Enter your choice: ");

                            int employeeChoice = scanner.nextInt();
                            scanner.nextLine(); // Clear the newline character left in the input buffer

                            switch (employeeChoice) {
                                case 1:
                                    System.out.println("Viewing personal details...");
                                    break;
                                case 2:
                                    System.out.println("Viewing most recent payslip...");
                                    break;
                                case 3:
                                    System.out.println("Viewing historical payslips...");
                                    break;
                                case 4:
                                    System.out.println("Confirming promotional changes...");
                                    break;
                                case 5:
                                    employeeMenuRunning = false;
                                    break;
                                default:
                                    System.out.println("Invalid selection. Please try again.");
                            }
                        }
                    }
                    break;

                case 2:
                    // Admin Menu
                    if (authenticate(scanner, ADMIN_PASSWORD)) {
                        boolean adminMenuRunning = true;
                        while (adminMenuRunning) {
                            System.out.println("\nAdmin Menu:");
                            System.out.println("1. Add New Employee");
                            System.out.println("2. View Employee Details");
                            System.out.println("3. Back to Main Menu");
                            System.out.print("Enter your choice: ");

                            int adminChoice = scanner.nextInt();
                            scanner.nextLine(); // Clear the newline character left in the input buffer

                            switch (adminChoice) {
                                case 1:
                                    System.out.println("Adding a new employee...");
                                    break;
                                case 2:
                                    System.out.println("Viewing employee details...");
                                    break;
                                case 3:
                                    adminMenuRunning = false;
                                    break;
                                default:
                                    System.out.println("Invalid selection. Please try again.");
                            }
                        }
                    }
                    break;

                case 3:
                    // HR Menu
                    if (authenticate(scanner, HR_PASSWORD)) {
                        boolean hrMenuRunning = true;
                        while (hrMenuRunning) {
                            System.out.println("\nHuman Resources Menu:");
                            System.out.println("1. Implement Promotion");
                            System.out.println("2. Back to Main Menu");
                            System.out.print("Enter your choice: ");

                            int hrChoice = scanner.nextInt();
                            scanner.nextLine(); // Clear the newline character left in the input buffer

                            switch (hrChoice) {
                                case 1:
                                    System.out.println("Implementing promotion...");
                                    break;
                                case 2:

                                hrMenuRunning = false;
                                    break;
                                default:
                                    System.out.println("Invalid selection. Please try again.");
                            }
                        }
                    }
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }

        scanner.close();
    }

    // Method to authenticate user with a password
    private static boolean authenticate(Scanner scanner, String correctPassword) {
        System.out.print("Enter your password: ");
        String inputPassword = scanner.nextLine();  // Clear the newline character left in the input buffer
        if (inputPassword.equals(correctPassword)) {
            System.out.println("Access granted!");
            return true;
        } else {
            System.out.println("Invalid password. Access denied.");
            return false;
        }
    }
}
