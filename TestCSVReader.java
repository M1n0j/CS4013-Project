import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestCSVReader {
    public static void readCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line); // Print each line
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Change this to your actual file path
        String filePath = "src/resources/Payslips.csv";

        System.out.println("Reading the CSV file...");
        readCSV(filePath);
    }
}
