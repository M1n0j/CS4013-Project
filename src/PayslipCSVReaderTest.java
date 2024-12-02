import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PayslipCSVReaderTest {

    private static final String PAYSLIP_CSV_PATH = "src/resources/Payslips.csv"; // Update the path as necessary

    public static void main(String[] args) {
        List<Payslip> payslips = readPayslipsFromCSV(PAYSLIP_CSV_PATH);
        displayPayslipData(payslips);
    }

    /**
     * Reads payslip data from a CSV file and returns a list of Payslip objects.
     *
     * @param csvFilePath The path to the CSV file.
     * @return A list of Payslip objects.
     */
    public static List<Payslip> readPayslipsFromCSV(String csvFilePath) {
        List<Payslip> payslips = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            // Skip the header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                // Assuming the fields are in the following order:
                // employeeId, paymentDate, grossSalary
                if (fields.length >= 3) {
                    int employeeId = Integer.parseInt(fields[0]);
                    String paymentDate = fields[1]; // You might want to parse this to a LocalDate
                    double grossSalary = Double.parseDouble(fields[2]);

                    // Create a new Payslip object (you might need to adjust the constructor)
                    Payslip payslip = new Payslip(grossSalary); // Adjust constructor as necessary
                    // You can also set other fields if your Payslip class allows it
                    payslips.add(payslip);
                } else {
                    System.err.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading payslip file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
        }

        return payslips;
    }

    /**
     * Displays the payslip data.
     *
     * @param payslips The list of Payslip objects to display.
     */
    public static void displayPayslipData(List<Payslip> payslips) {
        System.out.println("----- Payslip Data -----");
        for (Payslip payslip : payslips) {
            // Assuming the Payslip class has a method to print its details
            payslip.printPayslip(); // Adjust if needed
        }
    }
}