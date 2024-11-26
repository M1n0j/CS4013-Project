import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalaryReader {
    private static final String CSV_FILE_PATH = "src/resources/Salaries.csv";

    public static List<String[]> findSalariesByPosition(String position) {
        List<String[]> matchingSalaries = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            // Skip header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].toLowerCase().contains(position.toLowerCase())) {
                    matchingSalaries.add(values);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return matchingSalaries;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a job position (or 'exit' to quit): ");
            String position = scanner.nextLine();

            if (position.equalsIgnoreCase("exit")) {
                break;
            }

            List<String[]> salaries = findSalariesByPosition(position);

            if (salaries.isEmpty()) {
                System.out.println("No salaries found for position: " + position);
                continue;
            }

            System.out.println("Available levels for " + position + ":");
            for (String[] salary : salaries) {
                System.out.println("Level: " + salary[1]);
            }

            System.out.print("Enter the salary level: ");
            String level = scanner.nextLine();

            boolean found = false;
            for (String[] salary : salaries) {
                if (salary[1].equals(level)) {
                    System.out.println("Salary for " + position + " at Level " + level + ": €" + salary[2]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("No salary found for Level " + level);
            }

            System.out.println(); // Add a blank line for readability
        }

        scanner.close();
        System.out.println("Salary search ended.");
    }
}