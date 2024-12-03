import java.io.*;
import java.util.Scanner;
/**
 * Menu interface for full-time employees, providing options to view details, payslips, and manage promotions.
 */
public class FullTimeMenu {
    private Scanner scanner;
    private int userId;
    double grossSalary = 0.0;
    private AdminMenu adminMenu;
    private PayslipManager payslipManager;

    /**
     * Constructor to initialize the FullTimeMenu with the user ID and scanner
     * @param scanner The scanner object for user input
     * @param userId  The ID of the logged-in full-time employee
     */

    public FullTimeMenu(Scanner scanner, int userId) {
        this.scanner = scanner;
        this.userId = userId;
        this.payslipManager = new PayslipManager();
    }


    /**
     * Displays the menu for full-time employees and handles user input for various actions
     */

    public void displayMenu() {

        if (checkPromotionStatus()) {
            System.out.println("You have been promoted! Type 'accept' to accept or 'decline' to decline:");
            String response = scanner.nextLine().toLowerCase();

            if ("accept".equals(response)) {
                System.out.println("Congratulations on accepting the promotion!");
                int newLevel = addLevel(true);
                updatePromotion(false);
            } else if ("decline".equals(response)) {
                System.out.println("You have declined the promotion.");
                updatePromotion(false);
            } else {
                System.out.println("Invalid response. Returning to the main menu.");
            }
        }

        boolean fullTimeMenuRunning = true;
        while (fullTimeMenuRunning) {
            System.out.println("\nFull-Time Employee Menu:");
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
                payslipManager.printEmployeePayslips(String.valueOf(userId));
            } else if (choice == 4) {
                fullTimeMenuRunning = false;
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }
    /**
     * Displays the details of the current logged-in employee by reading from the employee csv
     */
    private void viewMyDetails() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("src/resources/Employees.csv"));
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

    /**
     * Checks if the current logged-in employee has a pending promotion status
     * @return True if the promotion status is true, otherwise false
     */
    private boolean checkPromotionStatus() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Resources/Employees.csv"));
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 7) {

                        String employeeId = (fields[0]);
                        if (employeeId.equals(Integer.toString(userId)) ) {
                            boolean isPromoted = Boolean.parseBoolean(fields[6]);
                            return isPromoted;
                        }
                   }
            }

        } catch (IOException e) {
            System.out.println("Error reading employee details: " + e.getMessage());
        }

        return false;
    }
    /**
     * Updates the promotion status of the logged-in employee in the employee csv
     * @param newStatus The new promotion status to set
     */
    private void updatePromotion(boolean newStatus) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Resources/Employees.csv"));
            StringBuilder updatedData = new StringBuilder();
            String line;

            String header = reader.readLine();
            updatedData.append(header).append("\n");

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (Integer.parseInt(fields[0]) == userId) {
                    fields[6] = String.valueOf(newStatus);
                }
                updatedData.append(String.join(",", fields)).append("\n");
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/Employees.csv"));
            writer.write(updatedData.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error updating promotion status: " + e.getMessage());
        }
    }

    /**
     * Increments the pay level of the logged-in employee if they are eligible
     * @param incrementLevel Whether to increment the level
     * @return The new level of the employee
     */
    private int addLevel(boolean incrementLevel) {
        int currentLevel = currentLevel();

        if (incrementLevel && currentLevel > 0) {
            currentLevel++;
            upEmployeeLevel(currentLevel);
        }
        return currentLevel;
    }

    /**
     * Retrieves the current pay level of the logged-in employee
     * @return The current pay level of the employee
     */
    private int currentLevel() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/Employees.csv"));
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 5) {


                    int employeeId = Integer.parseInt(fields[0].trim());
                    if (employeeId == userId) {
                        return Integer.parseInt(fields[4].trim());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading current level: " + e.getMessage());
        }
        return 0;
    }
    /**
     * Updates the pay level of the logged-in employee in the employee csv
     * @param newLevel The new pay level to set
     */

    private void upEmployeeLevel(int newLevel) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/Employees.csv"));
            StringBuilder updatedData = new StringBuilder();
            String line;

            String header = reader.readLine();

            if (header == null) {
                System.err.println("CSV file is empty or cannot be read.");
                return;
            }
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                        int employeeId = Integer.parseInt(fields[0]);
                        if (employeeId == userId) {
                            fields[4] = String.valueOf(newLevel);
                        }
                }
                updatedData.append(String.join(",", fields)).append("\n");
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/Employees.csv"));
            writer.write(updatedData.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error updating employee level: " + e.getMessage());
        }
    }



}
