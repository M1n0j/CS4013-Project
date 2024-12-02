import java.io.*;
import java.util.Scanner;

public class FullTimeMenu {
    private Scanner scanner;
    private int userId;
    double grossSalary = 0.0;


    public FullTimeMenu(Scanner scanner, int userId) {
        this.scanner = scanner;
        this.userId = userId;
    }

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
                Payslip payslip = new Payslip(grossSalary);
                Payslip.printPayslip();
            } else if (choice == 3) {
                System.out.println("Historical Payslips:");
            } else if (choice == 4) {
                fullTimeMenuRunning = false;
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }

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

    private boolean checkPromotionStatus() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/Employees.csv"));
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 7) {

                        int employeeId = Integer.parseInt(fields[0]);
                        if (employeeId == userId) {
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
    private void updatePromotion(boolean newStatus) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/Employees.csv"));
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

    private int addLevel(boolean incrementLevel) {
        int currentLevel = currentLevel();
        int maxLevel = maxLevelForPosition();

        if (incrementLevel && currentLevel < maxLevel) {
            currentLevel++;
            upEmployeeLevel(currentLevel);
        }
        return currentLevel;
    }


    private int currentLevel() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/Employees.csv"));
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 5) {

                    try {
                        int employeeId = Integer.parseInt(fields[0].trim());
                        if (employeeId == userId) {
                            return Integer.parseInt(fields[4].trim());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid employee ID format in the CSV file: " + fields[0]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading current level: " + e.getMessage());
        }
        return 0;
    }

    private int maxLevelForPosition() {
        String userPosition = AdminMenu.checkPosition();
        int maxLevel = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/salaries.csv"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equalsIgnoreCase(userPosition)) {
                    maxLevel = Math.max(maxLevel, Integer.parseInt(fields[1])); // Compare levels
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading max level: " + e.getMessage());
        }
        return maxLevel;
    }

    private void upEmployeeLevel(int newLevel) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/Employees.csv"));
            StringBuilder updatedData = new StringBuilder();
            String line;

            String header = reader.readLine();
            updatedData.append(header).append("\n");

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (Integer.parseInt(fields[0]) == userId) {
                    fields[4] = String.valueOf(newLevel);
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
