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
                    removeEmployee();  // Added option to remove employee
                    break;
                case 4:
                    adminMenuRunning = false; // Go back to the main menu
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    // Method to add a new employee
    private void addNewEmployee() {
        try {
            System.out.println("\nEnter employee details:");

            // Collect the employee's common details
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Position: ");
            String position = scanner.nextLine();

            // Collect additional details specific to full-time or part-time
            System.out.print("Is the employee Full-Time? (true/false): ");
            boolean isFullTime = scanner.nextBoolean();
            scanner.nextLine(); // Clear the newline character

            String password = "defaultPassword";  // Default password, can be changed as needed
            System.out.print("Set password for the employee: ");
            password = scanner.nextLine();

            // Optionally set employeeId (for testing purposes, or can be left as null)
            System.out.print("Enter employeeId (leave blank for auto-generate): ");
            String employeeIdInput = scanner.nextLine();
            Integer employeeId = (employeeIdInput.isEmpty()) ? null : Integer.parseInt(employeeIdInput);

            int currentPoint = 0;  // For now, we are assuming this as 0

            // Create the employee object
            Employee employee;
            if (isFullTime) {
                employee = new FullTimeEmployee("", 0, "", "", "", "", false);
            } else {
                System.out.print("Hourly Pay: ");
                double hourlyPay = scanner.nextDouble();
                System.out.print("Hours Worked per week: ");
                double hoursWorked = scanner.nextDouble();
                scanner.nextLine(); // Clear newline character

                employee = new PartTimeEmployee(name, 0, "", email, "", false, 0.0, 0.0);
            }


            // Add the employee to the CSV files
            admin.addEmployee(employee, isFullTime, "N/A", currentPoint, password, employeeId);
            System.out.println("Employee added successfully!");

        } catch (IOException e) {
            System.out.println("Error adding employee: " + e.getMessage());
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
}
