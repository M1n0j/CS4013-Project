import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DisplayMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayrollService payrollService = new PayrollService();  // Instance of the payroll service
        boolean running = true;

        // Example: Creating a test employee (this would normally be read from CSV)
        Employee newEmployee = new FullTimeEmployee("John Doe", 1, LocalDate.of(1985, 6, 15),
                "123 Main St", "johndoe@example.com", "555-1234", "IT", "Developer",
                new SalaryScale("SS001", "Junior Developer", List.of(30000.0, 35000.0, 40000.0), 3), 1);
        payrollService.addEmployee(newEmployee);  // Adding employee to payroll service

        // Main loop for the CLI
        while (running) {
            // Main Menu
            System.out.println("Welcome to the UL Payroll System");
            System.out.println("Please select your role:");
            System.out.println("1. Employee");
            System.out.println("2. Admin");
            System.out.println("3. Human Resources");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear the newline character left in the input buffer

            switch (choice) {
                case 1:
                    // Employee Menu
                    if (authenticate(scanner, "employee123")) {
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
                                    // Display employee personal details
                                    System.out.println(newEmployee);
                                    break;
                                case 2:
                                    // View most recent payslip
                                    Payslip recentPayslip = payrollService.getMostRecentPayslip(newEmployee.getEmployeeId());
                                    if (recentPayslip != null) {
                                        recentPayslip.generatePayslip();
                                    } else {
                                        System.out.println("No payslips available.");
                                    }
                                    break;
                                case 3:
                                    // View historical payslips
                                    List<Payslip> payslips = payrollService.getEmployeePayslipHistory(newEmployee.getEmployeeId());
                                    if (!payslips.isEmpty()) {
                                        payslips.forEach(Payslip::generatePayslip);
                                    } else {
                                        System.out.println("No payslips available.");
                                    }
                                    break;
                                case 4:
                                    // Implement promotion logic (for full-time employees, for example)
                                    if (newEmployee instanceof FullTimeEmployee) {
                                        FullTimeEmployee fte = (FullTimeEmployee) newEmployee;
                                        fte.incrementSalaryPoint();
                                        System.out.println("Promotion confirmed! New salary: " + fte.calculateSalary());
                                    }
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
                    // Admin Menu logic
                    // Admin logic for adding/viewing employees can be implemented here
                    break;
                case 3:
                    // HR Menu logic
                    // HR logic for promotions can be implemented here
                    break;
                case 4:
                    // Exit the system
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }

        scanner.close();
    }

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
