import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DisplayMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("-----Welcome to the University Of Limerick Payroll Service-----");
            int userId;
            try {
                System.out.println("Enter your UserID (or type 'Quit' to exit):");
                String userIdInput = scanner.nextLine();

                if (userIdInput.equalsIgnoreCase("Quit")) {
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                    continue;
                }

                userId = Integer.parseInt(userIdInput);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! UserID must be a number!");
                scanner.nextLine();
                continue;
            }

            String[] userDetails = getUserDetails("src/Resources/user.csv", userId);
            if (userDetails != null) {
                String correctPassword = userDetails[1];
                String role = userDetails[2];

                System.out.println("User Role: " + role);

                System.out.print("Enter your password: ");
                String inputPassword = scanner.nextLine();

                if (inputPassword.equals(correctPassword)) {
                    System.out.println("Access granted!");

                    switch (role.toLowerCase()) {
                        case "admin":
                            AdminMenu adminMenu = new AdminMenu(scanner);
                            adminMenu.displayMenu();
                            break;
                        case "hr":
                            HRMenu hrMenu = new HRMenu(scanner, userId);
                            hrMenu.displayMenu();
                            break;
                        case "part-time":
                            PartTimeMenu partTimeMenu = new PartTimeMenu(scanner, userId);
                            partTimeMenu.displayMenu();
                            break;
                        case "full-time":
                            FullTimeMenu fullTimeMenu = new FullTimeMenu(scanner, userId);
                            fullTimeMenu.displayMenu();
                            break;
                        default:
                            System.out.println("Invalid role! Contact the Administrator.");
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


        if (!file.exists()) {
            System.out.println("Error: User data file not found. Please contact the administrator.");
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


                String[] userDetails = line.split(",");


                if (Integer.parseInt(userDetails[0]) == userId) {
                    return userDetails;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file!");
        } catch (NumberFormatException e) {
            System.err.println("Error with UserID. Contact the administrator.");
        }

        return null;
    }
}