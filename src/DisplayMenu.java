import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class DisplayMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("-----Welcome to the University Of Limerick Payroll Service-----");
            int userId;
            try {
                System.out.println("Enter your UserID:");
                userId = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character left by nextInt()
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! UserID must be a number!");
                scanner.nextLine(); // Clear invalid input
                continue; // Restart the loop
            }

            // Get user details from the CSV file based on userId
            String[] userDetails = getUserDetails("src/resources/User.csv", userId);
            if (userDetails != null) {
                String correctPassword = userDetails[1];
                String role = userDetails[2];

                System.out.println("User Role: " + role);

                // Ask for password and authenticate user
                System.out.print("Enter your password: ");
                String inputPassword = scanner.nextLine();

                if (inputPassword.equals(correctPassword)) {
                    System.out.println("Access granted!");

                    // Role-based menu handling
                    switch (role.toLowerCase()) {
                        case "admin":
                            AdminMenu adminMenu = new AdminMenu(scanner);
                            adminMenu.displayMenu(); // Show Admin Menu
                            break;
                        case "hr":
                            HRMenu hrMenu = new HRMenu(scanner);
                            hrMenu.displayMenu(); // Show HR Menu
                            break;
                        case "part-time":
                            PartTimeMenu partTimeMenu = new PartTimeMenu(scanner);
                            partTimeMenu.displayMenu(); // Show Part-Time Menu
                            break;
                        case "full-time":
                            FullTimeMenu fullTimeMenu = new FullTimeMenu(scanner);
                            fullTimeMenu.displayMenu(); // Show Full-Time Menu
                            break;
                        default:
                            System.out.println("Invalid role! Contact admin.");
                    }
                } else {
                    System.out.println("Invalid password! Access denied.");
                }
            } else {
                System.out.println("Invalid UserID or unable to access user data. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Method to retrieve user details from a CSV file based on the given UserID.
     *
     * @param fileName The name of the CSV file to read.
     * @param userId   The UserID to search for.
     * @return An array of user details (UserID, Password, Role) if found, or null if not found.
     */
    private static String[] getUserDetails(String fileName, int userId) {
        File file = new File(fileName);

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("Error: User data file not found. Please contact the administrator.");
            return null; // Return null to indicate the file is missing
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true; // Flag to identify the first row as a header

            // Read the file line by line
            while ((line = br.readLine()) != null) {
                // Skip the header row (first row)
                if (isHeader) {
                    isHeader = false; // Mark the header as skipped
                    continue;         // Move to the next line
                }

                // Split the line by commas into an array
                String[] userDetails = line.split(",");

                // Trim whitespace to ensure clean parsing
                if (Integer.parseInt(userDetails[0]) == userId) {
                    return userDetails; // Return the user details if UserID matches
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file!");
        } catch (NumberFormatException e) {
            System.err.println("Error with UserID. Contact the administrator.");
        }
        // Return null if no matching UserID is found
        return null;
    }
}
