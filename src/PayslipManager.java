import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PayslipManager {
    private static final String FILE_PATH = "src/Resources/Payslips.csv"; // File path for storing payslips

    // A list to store payslips in memory (you can also read/write from the file as needed)
    private static List<Payslip> payslips = new ArrayList<>();

    // Method to write a payslip to the CSV file
    public static void writePayslipToCSV(Payslip payslip) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Write payslip data to the file (including employeeId)
            writer.write(payslip.getEmployeeId() + "," + payslip.toCSV() + "\n");
            System.out.println("Payslip for Employee ID " + payslip.getEmployeeId() + " written to CSV.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

    // Method to read historical payslips for a specific employee by employeeId
    public static List<Payslip> readPayslipsForEmployee(String employeeId) {
        List<Payslip> employeePayslips = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            reader.readLine(); // Skip header line if present

            // Loop through each line in the CSV
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                // Ensure line has enough columns
                if (values.length < 9) {
                    continue;  // Skip this line if it has insufficient columns
                }

                String empId = values[1].trim();  // Trim extra spaces
                // Only print this if debugging is needed:
                // System.out.println("Employee ID from CSV: " + empId);

                // If employeeId matches, parse and add the payslip to the list
                if (empId.equals(employeeId)) {
                    Payslip payslip = parsePayslipFromCSV(values);
                    if (payslip != null) {
                        employeePayslips.add(payslip);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from CSV: " + e.getMessage());
        }

        return employeePayslips;
    }



    // Helper method to parse a payslip from CSV data
    private static Payslip parsePayslipFromCSV(String[] values) {
        try {
            double grossSalary = Double.parseDouble(values[4]);
            double usc = Double.parseDouble(values[5]);
            double prsi = Double.parseDouble(values[6]);
            double incomeTax = Double.parseDouble(values[7]);
            double netSalary = Double.parseDouble(values[8]);
            String payPeriod = values[3];

            // Now you can directly handle the data as you like
            System.out.println("Pay Period: " + payPeriod);
            System.out.println("Gross Salary: €" + grossSalary);
            System.out.println("USC: €" + usc);
            System.out.println("PRSI: €" + prsi);
            System.out.println("Income Tax: €" + incomeTax);
            System.out.println("Net Salary: €" + netSalary);


        } catch (NumberFormatException e) {
            System.err.println("Error parsing numeric data in payslip: " + e.getMessage());
            return null;
        }
        return null;
    }

    public static void printEmployeePayslips(String employeeId) {
        List<Payslip> payslips = readPayslipsForEmployee(employeeId);

        if (payslips.isEmpty()) {

            System.out.println("End of historic payslip search for Employee ID: " + employeeId);
        } else {
            System.out.println("\n----- Historical Payslips for Employee ID: " + employeeId + " -----");
            for (Payslip payslip : payslips) {

                payslip.printPayslip();
            }
            // End of search message after printing the payslips
            System.out.println("End of historic payslip search for Employee ID: " + employeeId);
        }
    }



    // Method to add a payslip to the in-memory list (if needed)
    public static void addPayslip(Payslip payslip) {
        payslips.add(payslip);
    }
}
