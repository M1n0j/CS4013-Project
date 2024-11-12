import java.util.Scanner;

public class DisplayMenu {

    public static void main(String[] args) {
        System.out.println("Enter Email:");
        System.out.println("Quit");

        String options = null;
        Scanner scan = new Scanner(System.in); // Capturing the email input

        do {
            options = scan.nextLine();
            switch (options) {
                case "admin@gmail.com":
                    // Prompt for password
                    System.out.println("Enter your password:");
                    String adminPassword = scan.nextLine();
                    if (adminPassword.equals("admin123")) { // Example password check
                        System.out.println("Welcome to the Admin Menu");
                    } else {
                        System.out.println("Invalid password for Admin.");
                    }
                    break;
                case "user@gmail.com":
                    // Prompt for password
                    System.out.println("Enter your password:");
                    String userPassword = scan.nextLine();
                    if (userPassword.equals("user123")) { // Example password check
                        System.out.println("Welcome to the User Menu");
                    } else {
                        System.out.println("Invalid password for User.");
                    }
                    break;
                case "customer@gmail.com":
                    // Prompt for password
                    System.out.println("Enter your password:");
                    String customerPassword = scan.nextLine();
                    if (customerPassword.equals("customer123")) { // Example password check
                        System.out.println("Welcome to the Customer Menu");
                    } else {
                        System.out.println("Invalid password for Customer.");
                    }
                    break;
            }
        } while (!options.equals("Quit")); // quitting the program
        System.out.println("Program exited.");
        scan.close();
    }
}