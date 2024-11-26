import java.util.Scanner;

public class DisplayMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Enter your UserID:");
            int userId = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            // Get user details from the CSV file based on userId
            String[] userDetails = getUserDetails("user.csv", userId);
            if (userDetails != null) {
                String correctPassword = userDetails[1]; // password is at index 1
                String role = userDetails[2]; // role is at index 4

                // Ask for password and authenticate user
                System.out.print("Enter your password: ");
                String inputPassword = scanner.nextLine();

                if (inputPassword.equals(correctPassword)) {
                    System.out.println("Access granted!");

                    // Role-based menu handling
                    if (role.equals("Admin")) {
                        AdminMenu adminMenu = new AdminMenu(scanner);
                        adminMenu.displayMenu();
                    } else if (role.equals("HR")) {
                        HRMenu hrMenu = new HRMenu(scanner);
                        hrMenu.displayMenu();
                    } else if (role.equals("Full-Time")) {
                        FullTimeMenu fullTimeMenu = new FullTimeMenu(scanner, userDetails);
                        fullTimeMenu.displayMenu();
                    } else if (role.equals("Part-Time")) {
                        PartTimeMenu partTimeMenu = new PartTimeMenu(scanner, userDetails);
                        partTimeMenu.displayMenu();
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
}
