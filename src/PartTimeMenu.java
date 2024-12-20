import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;


/**
 * Represents the menu interface for part-time employees
 * Provides options to view employee details, payslips, and work claims
 */
public class PartTimeMenu{
    private Scanner scanner;
    private int userId;
    private PayslipManager payslipManager;

    /**
     * Constructs a PartTimeMenu object with the specified scanner and user ID
     * @param scanner The scanner object for user input
     * @param userId  The ID of the currently logged-in user
     */
    public PartTimeMenu(Scanner scanner, int userId) {
        this.scanner = scanner;
        this.userId = userId;
        this.payslipManager = new PayslipManager();
    }

    /**
     * Displays the menu options for part-time employees and handles user interactions
     */
    public void displayMenu() {
        boolean partTimeMenuRunning = true;
        boolean isSecondFriday = LocalDate.now().getDayOfMonth() < 15;
        while (partTimeMenuRunning) {
            System.out.println("\nPart-Time Employee Menu:");
            System.out.println("1. View My Details");
            System.out.println("2. View My Most Recent Payslip");
            System.out.println("3. View My Historical Payslips");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Employee Details:");
                viewMyDetails();
            } else if (choice == 2) {
                System.out.println("Most Recent Payslip:");
                Payslip payslip = new Payslip(0);
                payslip.printPayslip();
            } else if (choice == 3) {
                System.out.println("Historical Payslips:");
                PayrollChecker payrollChecker = new PayrollChecker();
                payrollChecker.checker();
                payslipManager.printEmployeePayslips(String.valueOf(userId));
            } else if (choice == 4) {
                partTimeMenuRunning = false;
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }
    private void viewMyDetails() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("src/Resources/Employees.csv"));
            String line;
            boolean found = false;


            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (Integer.parseInt(fields[0]) == userId) {
                    System.out.println("Employee ID: " + fields[0]);
                    System.out.println("Name: " + fields[1]);
                    System.out.println("Email: " + fields[2]);
                    System.out.println("Position: " + fields[3]);
                    System.out.println("Pay Level: " + fields[4]);
                    System.out.println("Is Full-Time: " + fields[5]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Employee not found.");
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading employee details: " + e.getMessage());
        }
    }
}
