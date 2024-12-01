import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;

public class AdminMenu {
    private Scanner scanner;
    private Admin admin;

    public AdminMenu(Scanner scanner) {
        this.scanner = scanner;
        this.admin = new Admin();
    }

    // Display menu to the admin
    public void displayMenu() {
        boolean adminMenuRunning = true;
        while (adminMenuRunning) {
            try {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. Add New Employee");
                System.out.println("2. View Employee Details");
                System.out.println("3. Remove Employee");
                System.out.println("4. Back to Main Menu");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear newline character

                switch (choice) {
                    case 1:
                        addNewEmployee();
                        break;
                    case 2:
                        viewEmployeeDetails();
                        break;
                    case 3:
                        removeEmployee();
                        break;
                    case 4:
                        adminMenuRunning = false; // Go back to the main menu
                        break;
                    default:
                        System.out.println("Invalid selection. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear scanner buffer
            }
        }
    }

    // Method to add a new employee
    private void addNewEmployee() {
        try {
            System.out.println("\nEnter employee details:");

            // Collect the employee's common details
            String name;
            while (true) {
                System.out.print("Name: ");
                name = scanner.nextLine();
                if (!name.isEmpty()) {
                    break; // Exit the loop if the input is valid
                }
                System.out.println("Name cannot be empty. Please try again.");
            }
            String email;
            while (true) {
                System.out.print("Email: ");
                email = scanner.nextLine();
                if (email.endsWith("@gmail.com")) {
                    break; // Exit the loop if the input is valid
                }
                System.out.println("Invalid email. Please enter a valid Gmail address (e.g., example@gmail.com).");
            }

            String inputPosition = "";
            boolean isValidPosition = false;

            try {
                while (!isValidPosition) {
                    System.out.print("Position: ");
                    inputPosition = scanner.nextLine();

                    // Check if the position is valid using `findPosition` method
                    String[] positionDetails = PositionChecker.checkPosition("src/Resources/Salaries.csv", inputPosition);

                    if (positionDetails != null) {
                        isValidPosition = true; // Exit the loop if position is valid
                        System.out.println("Valid position: " + inputPosition);
                    } else {
                        System.out.println("Invalid position. Please try again.");
                    }
                }
            } catch (Exception e) {
                System.err.println("An error occurred while validating the position: " + e.getMessage());
            }

            // Collect additional details specific to full-time or part-time
            boolean isFullTime = false;
            while (true) {
                System.out.print("Is the employee Full-Time? (true/false): ");
                String fullTimeInput = scanner.nextLine().toLowerCase();
                if (fullTimeInput.equals("true") || fullTimeInput.equals("false")) {
                    isFullTime = Boolean.parseBoolean(fullTimeInput);
                    break; // Exit the loop if the input is valid
                }
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }

            String password;
            while (true) {
                System.out.print("Set password for the employee: ");
                password = scanner.nextLine();
                if (!password.isEmpty()) {
                    break; // Exit the loop if the input is valid
                }
                System.out.println("Password cannot be empty. Please try again.");
            }

            // Automatically set employeeId if nothing is entered
            Integer employeeId = null;
            while (true) {
                System.out.print("Enter employeeId: ");
                String employeeIdInput = scanner.nextLine();
                try {
                    employeeId = Integer.parseInt(employeeIdInput);
                    break; // Exit the loop if the input is valid
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID. Please enter a valid integer.");
                }
            }

            int currentPoint = 0;  // For now, we are assuming this as 0

            // Create the employee object
            Employee employee;
            if (isFullTime) {
                employee = new FullTimeEmployee("", 1004, "", "", false, 1, true);
            } else {
                while (true) {
                    System.out.print("Hourly Pay: ");
                    try {
                        hourlyPay = Double.parseDouble(scanner.nextLine().trim());
                        if (hourlyPay > 0) {
                            break;
                        } else {
                            System.out.println("Hourly pay must be positive. Please try again.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numeric value.");
                    }
                }
                while (true) {
                    System.out.print("Hours Worked per week: ");
                    try {
                        hoursWorked = Double.parseDouble(scanner.nextLine().trim());
                        if (hoursWorked > 0) {
                            break;
                        } else {
                            System.out.println("Hours worked must be positive. Please try again.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numeric value.");
                    }
                }

                employee = new PartTimeEmployee("", 0, "", "", 0, false, false, 0.0, 0.0);
            }


            // Add the employee to the CSV files
            admin.addEmployee(employee, isFullTime, "N/A", currentPoint, password, employeeId);
            System.out.println("Employee added successfully!");

        } catch (IOException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    // Method to view employee details
    private void viewEmployeeDetails() {
        try {
            System.out.print("\nEnter Employee ID to view details: ");
            int employeeId = scanner.nextInt();
            scanner.nextLine(); // Clear newline character

            // Use the `admin` instance to access the non-static field
            BufferedReader reader = new BufferedReader(new FileReader(admin.EmployeesCsv));
            String line;
            boolean found = false;

            // Skip header
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (Integer.parseInt(fields[0]) == employeeId) {
                    System.out.println("\nEmployee Details:");
                    System.out.println("Employee ID: " + fields[0]);
                    System.out.println("Name: " + fields[1]);
                    System.out.println("Email: " + fields[2]);
                    System.out.println("Position: " + fields[3]);
                    System.out.println("Salary Scale: " + fields[4]);
                    System.out.println("Current Point: " + fields[5]);
                    System.out.println("Full-Time: " + fields[6]);
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

    // Method to remove an employee from all CSV files with a confirmation check
    private void removeEmployee() {
        try {
            // Prompt the user for the employee ID to remove
            System.out.print("\nEnter Employee ID to remove: ");
            int employeeId = scanner.nextInt();
            scanner.nextLine(); // Clear newline character

            // First, we confirm the deletion with the admin
            System.out.print("Are you sure you want to delete the employee with ID " + employeeId + "? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            // Check if the admin confirmed the deletion
            if (!confirmation.equals("yes")) {
                System.out.println("Employee deletion canceled.");
                return; // Exit the method if not confirmed
            }

            // Create an instance of Admin to access the file paths
            Admin admin = new Admin();

            // Create temporary files for each CSV file to store updated data
            File employeesFile = new File(admin.EmployeesCsv);  // Accessing instance fields
            File payslipsFile = new File(admin.PayslipsCsv);    // Accessing instance fields
            File usersFile = new File(admin.UsersCSV);          // Accessing instance fields
            File tempEmployeesFile = new File("src/resources/temp_employees.csv");
            File tempPayslipsFile = new File("src/resources/temp_payslips.csv");
            File tempUsersFile = new File("src/resources/temp_users.csv");

            // BufferedReader and PrintWriter for all files
            BufferedReader reader = new BufferedReader(new FileReader(employeesFile));
            PrintWriter writerEmployees = new PrintWriter(new FileWriter(tempEmployeesFile));
            BufferedReader payslipsReader = new BufferedReader(new FileReader(payslipsFile));
            PrintWriter writerPayslips = new PrintWriter(new FileWriter(tempPayslipsFile));
            BufferedReader usersReader = new BufferedReader(new FileReader(usersFile));
            PrintWriter writerUsers = new PrintWriter(new FileWriter(tempUsersFile));

            String line;
            boolean found = false;

            // Read headers and write them to temporary files
            String headerEmployees = reader.readLine();
            writerEmployees.println(headerEmployees);
            String headerPayslips = payslipsReader.readLine();
            writerPayslips.println(headerPayslips);
            String headerUsers = usersReader.readLine();
            writerUsers.println(headerUsers);

            // Remove employee from employees.csv
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int currentEmployeeId = Integer.parseInt(fields[0]);

                if (currentEmployeeId != employeeId) {
                    writerEmployees.println(line); // Write non-matching records to tempEmployees
                } else {
                    found = true;  // Employee found, we will not write this line to the temp file
                }
            }

            // Remove employee from payslips.csv
            while ((line = payslipsReader.readLine()) != null) {
                String[] fields = line.split(",");
                int currentEmployeeId = Integer.parseInt(fields[0]);

                if (currentEmployeeId != employeeId) {
                    writerPayslips.println(line); // Write non-matching records to tempPayslips
                }
            }

            // Remove employee from user.csv
            while ((line = usersReader.readLine()) != null) {
                String[] fields = line.split(",");
                int currentUserId = Integer.parseInt(fields[0]);

                if (currentUserId != employeeId) {
                    writerUsers.println(line); // Write non-matching records to tempUsers
                }
            }

            // Close readers and writers
            reader.close();
            payslipsReader.close();
            usersReader.close();
            writerEmployees.close();
            writerPayslips.close();
            writerUsers.close();

            // If the employee was found and removed, replace the old file with the temp files
            if (found) {
                if (employeesFile.delete()) {
                    tempEmployeesFile.renameTo(employeesFile);  // Replace employees file
                }
                if (payslipsFile.delete()) {
                    tempPayslipsFile.renameTo(payslipsFile);  // Replace payslips file
                }
                if (usersFile.delete()) {
                    tempUsersFile.renameTo(usersFile);  // Replace users file
                }
                System.out.println("Employee removed successfully from all files.");
            } else {
                // Employee was not found
                System.out.println("Employee with ID " + employeeId + " not found.");
                tempEmployeesFile.delete();  // Delete the temp file if employee wasn't found
                tempPayslipsFile.delete();
                tempUsersFile.delete();
            }
        } catch (IOException e) {
            System.out.println("Error removing employee: " + e.getMessage());
        }
    }

    public class PositionChecker {

        private static String[] checkPosition(String fileName, String position) {
            File file = new File(fileName);

            // Check if the file exists
            if (!file.exists()) {
                System.out.println("Error: File not found. Please contact the administrator.");
                return null;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                boolean isHeader = true;

                // Read the file line by line
                while ((line = br.readLine()) != null) {
                    // Skip the header row
                    if (isHeader) {
                        isHeader = false;
                        continue;
                    }

                    // Split the line by commas into an array
                    String[] details = line.split(",");

                    // Trim whitespace and compare positions
                    if (details[0].equalsIgnoreCase(position)) {
                        return details; // Return the row details if the position matches
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }

            return null; // Return null if no matching position is found
        }

    }
}
