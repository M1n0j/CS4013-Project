import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DisplayMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("-----Welcome to the University Of Limerick Payroll Service-----");
            System.out.println("Enter your UserID:");
            int userId = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character left by nextInt()

            // Get user details from the CSV file based on userId
            String[] userDetails = getUserDetails("src/resources/User.csv", userId);
            if (userDetails != null) {
                String correctPassword = userDetails[1].trim(); // Trim password from CSV
                String role = userDetails[2].trim(); // Trim role from CSV

                // Debugging: Print the role to check if it's correctly read
                System.out.println("User Role: " + role);

                // Ask for password and authenticate user
                System.out.print("Enter your password: ");
                String inputPassword = scanner.nextLine().trim(); // User input password (trimmed)

                if (inputPassword.equals(correctPassword)) {
                    System.out.println("Access granted!");

                    // Role-based menu handling (with trimming and case-insensitive comparison)
                    if (role.equalsIgnoreCase("Admin")) {
                        AdminMenu adminMenu = new AdminMenu(scanner);
                        adminMenu.displayMenu(); // Show Admin Menu
                    } else if (role.equalsIgnoreCase("HR")) {
                        HRMenu hrMenu = new HRMenu(scanner);
                        hrMenu.displayMenu(); // Show HR Menu
                    } else if (role.equalsIgnoreCase("Part-Time")) {
                        PartTimeMenu partTimeMenu = new PartTimeMenu(scanner);
                        partTimeMenu.displayMenu(); // Show Part-Time Menu
                    } else if (role.equalsIgnoreCase("Full-Time")) {
                        FullTimeMenu fullTimeMenu = new FullTimeMenu(scanner);
                        fullTimeMenu.displayMenu(); // Show Full-Time Menu
                    } else {
                        System.out.println("Invalid role assigned. Contact admin.");
                    }
                } else {
                    System.out.println("Invalid password. Access denied.");
                }
            } else {
                System.out.println("Invalid UserID. Please try again.");
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
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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
                if (Integer.parseInt(userDetails[0].trim()) == userId) {
                    return userDetails; // Return the user details if UserID matches
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing user ID: " + e.getMessage());
        }

        // Return null if no matching UserID is found
        return null;
    }
}
