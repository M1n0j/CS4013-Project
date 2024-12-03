import java.util.Scanner;
import java.io.*;

/**
 * AdminMenu class provides menu for admin users to manage employees.
 * It allows admins to add and remove employees as well as view their details.
 */
public class AdminMenu {
    private Scanner scanner;
    private Admin admin;
    private CSVWriter csvWriter;
    private CSVReader csvReader;

    /**
     * Constructs AdminMenu instance with a scanner for user input/
     * @param scanner the Scanner object for reading user input
     */

    public AdminMenu(Scanner scanner) {
        this.scanner = scanner;
        this.admin = new Admin();
        this.csvWriter = new CSVWriter("src/Resources/Employees.csv","src/Resources/user.csv", "src/Resources/Payslips.csv");
        this.csvReader = new CSVReader("src/Resources/Employees.csv","src/Resources/user.csv","src/Resources/Salaries.csv");
    }


    /**
     * Displays the admin menu and handles user input for various admin operations.
     */



    public void displayMenu() {
        boolean adminMenuRunning = true;
        while (adminMenuRunning) {
            try {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. Add New Employee");
                System.out.println("2. View Employee Details");
                System.out.println("3. Remove Employee");
                System.out.println("4. Logout");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

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
                        adminMenuRunning = false;
                        break;
                    default:
                        System.out.println("Invalid selection. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    /**
     * Allows user to enter details for a new employee and adds the employee to csv
     * @throws IOException if an error occurs while reading/writing to csv files
     */


    private void addNewEmployee() {
        try {
            System.out.println("\nEnter employee details:");


            String name;
            while (true) {
                System.out.print("Name: ");
                name = scanner.nextLine();
                if (!name.isEmpty()) {
                    break;
                }
                System.out.println("Name cannot be empty. Please try again.");
            }
            String email;
            while (true) {
                System.out.print("Email: ");
                email = scanner.nextLine();
                if (email.endsWith("@gmail.com")) {
                    break;
                }
                System.out.println("Invalid email. Please enter a valid Gmail address (e.g., example@gmail.com).");
            }

            String inputPosition = "";
            boolean isValidPosition = false;

            try {
                while (!isValidPosition) {
                    System.out.print("Position: ");
                    inputPosition = scanner.nextLine();


                    String[] positionDetails = checkPosition("src/Resources/Salaries.csv", inputPosition);

                    if (positionDetails != null) {
                        isValidPosition = true;
                        System.out.println("Valid position: " + inputPosition);
                    } else {
                        System.out.println("Invalid position. Please try again.");
                    }
                }
            } catch (Exception e) {
                System.err.println("An error occurred while validating the position: " + e.getMessage());
            }


            boolean isFullTime = false;
            while (true) {
                System.out.print("Is the employee Full-Time? (true/false): ");
                String fullTimeInput = scanner.nextLine().toLowerCase();
                if (fullTimeInput.equals("true") || fullTimeInput.equals("false")) {
                    isFullTime = Boolean.parseBoolean(fullTimeInput);
                    break;
                }
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }

            String password;
            while (true) {
                System.out.print("Set password for the employee: ");
                password = scanner.nextLine();
                if (!password.isEmpty()) {
                    break;
                }
                System.out.println("Password cannot be empty. Please try again.");
            }

            String role;
            while (true) {
                System.out.print("Set Role type for the employee: ");
                String roleInput = scanner.nextLine().toLowerCase();

                if (roleInput.equals("part-time") || roleInput.equals("full-time") || roleInput.equals("admin") || roleInput.equals("hr")) {
                    role = roleInput;
                    if (roleInput.equals("full-time") || roleInput.equals("admin") || roleInput.equals("hr")) {
                        isFullTime = true;
                    } else {
                        isFullTime = false;
                    }
                    break;
                } else {
                    System.out.println("Role type must be Admin, Full-Time, or Part-Time. Please try again.");
                }
            }


            Integer employeeId = null;
            while (true) {
                System.out.print("Enter employeeId: ");
                String employeeIdInput = scanner.nextLine();
                try {
                    employeeId = Integer.parseInt(employeeIdInput);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID. Please enter a valid integer.");
                }
            }

            int currentPoint = 0;


            Employee employee;
            if (isFullTime) {
                employee = new FullTimeEmployee(employeeId, name, email, inputPosition, 0, isFullTime, true);
            } else {
                employee = new PartTimeEmployee(employeeId, name, email, inputPosition, 0, isFullTime, true);
            }


            admin.addEmployee(employee, isFullTime, "N/A", currentPoint, password, employeeId, role);
            System.out.println("Employee added successfully!");

        } catch (IOException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Displays the details of an employee given their ID.
     * This method reads the employee data from the Employees csv file and displays the details.
     * @throws IOException if an error occurs while reading from the employee file
     */


    private void viewEmployeeDetails() {
        try {
            System.out.println("Enter Employee ID to view details: ");
            int employeeId = scanner.nextInt();
            scanner.nextLine();


            BufferedReader reader = new BufferedReader(new FileReader(admin.EmployeesCsv));
            String line;
            boolean found = false;


            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (Integer.parseInt(fields[0]) == employeeId) {
                    System.out.println("\nEmployee Details:");
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
     * Removes employee from all csv files
     *  Employee removed from the Employee, Payslip and Users csv files
     * @throws IOException if error occurs while reading/writing to csv files
     */


    private void removeEmployee() throws IOException {

        int employeeId;
        while (true) {
            System.out.print("\nEnter Employee ID to remove: ");
            String employeeIdInput = scanner.nextLine();
            try {
                employeeId = Integer.parseInt(employeeIdInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a valid integer.");
            }
        }

        boolean confirmDeletion = false;
        while (true) {
            System.out.print("Are you sure you want to delete the employee with ID " + employeeId + "? (yes/no): ");
            String confirmationInput = scanner.nextLine().trim().toLowerCase();
            if (confirmationInput.equals("yes") || confirmationInput.equals("no")) {
                confirmDeletion = confirmationInput.equals("yes");
                break;
            }
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
        }

        if (!confirmDeletion) {
            System.out.println("Employee deletion canceled.");
            return;
        }

        System.out.println("Proceeding with employee deletion...");

        Admin admin = new Admin();

        String csvName = "Employees";
        String filePath = new File("").getAbsolutePath() + "/src/Resources/" + csvName + ".csv";
        boolean isDelete = csvWriter.deleteRow(employeeId, filePath);

        String csvName2 = "user";
        String filePath2 = new File("").getAbsolutePath() + "/src/Resources/" + csvName2 + ".csv";
        boolean isDelete2 = csvWriter.deleteRow(employeeId, filePath2);
        if (isDelete2 && isDelete) {
            System.out.println("Employee deleted successfully.");
        }else{
            System.out.println("Employee not found.");
        }

    }

    /**
     * checks if the given position exists in the csv file
     * if position is found it returns the details of the position
     * @param fileName the path of the csv file being checked
     * @param position the position to search for in the csv file
     * @return a string array containing the details of the position if found or null if not found
     * @throws IOException if an error occurs while reading the file
     */


    public String[] checkPosition(String fileName, String position) {
        File file = new File(fileName);


        if (!file.exists()) {
            System.out.println("Error: File not found. Please contact the administrator.");
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {

                if (isHeader) {
                    isHeader = false;
                    continue;
                }


                String[] details = line.split(",");

                if (details[0].equalsIgnoreCase(position)) {
                    return details;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return null;
    }

}
