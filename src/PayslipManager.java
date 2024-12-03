import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the operations related to payslips, including reading and writing payslips to a csv file
 */
public class PayslipManager {
    private static final String FILE_PATH = "src/Resources/Payslips.csv";


    private static List<Payslip> payslips = new ArrayList<>();

    /**
     * Writes a payslip to the csv file.
     * @param payslip The Payslip object to be written to the CSV file
     */
    public static void writePayslipToCSV(Payslip payslip) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(payslip.getEmployeeId() + "," + payslip.toCSV() + "\n");
            System.out.println("Payslip for Employee ID " + payslip.getEmployeeId() + " written to CSV.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }


    /**
     * Reads payslips for a specific employee from the CSV file.
     * @param employeeId The ID of the employee whose payslips are to be read.
     * @return A list of Payslip objects for the specified employee.
     */
    public static List<Payslip> readPayslipsForEmployee(String employeeId) {
        List<Payslip> employeePayslips = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length < 9) {
                    continue;
                }

                String empId = values[1];

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

    /**
     * Parses a payslip from a CSV record.
     * @param values The array of strings representing the values of a payslip record.
     * @return A Payslip object, or null if parsing fails.
     */
    private static Payslip parsePayslipFromCSV(String[] values) {
        try {
            double grossSalary = Double.parseDouble(values[4]);
            double usc = Double.parseDouble(values[5]);
            double prsi = Double.parseDouble(values[6]);
            double incomeTax = Double.parseDouble(values[7]);
            double netSalary = Double.parseDouble(values[8]);
            String payPeriod = values[3];

            System.out.println("\nPay Period: " + payPeriod);
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

    /**
     * Prints all payslips for a specific employee.
     * @param employeeId The ID of the employee whose payslips will be printed.
     */
    public void printEmployeePayslips(String employeeId) {
        List<Payslip> employeePayslips = readPayslipsForEmployee(employeeId);

        if (employeePayslips.isEmpty()) {
            System.out.println("No historical payslips found for Employee ID: " + employeeId);
        } else {
            System.out.println("\n----- Historical Payslips for Employee ID: " + employeeId + " -----");
            for (Payslip payslip : employeePayslips) {
                payslip.printPayslip();
            }
            System.out.println("End of historical payslip search for Employee ID: " + employeeId);
        }
    }


    /**
     * Adds a payslip to the internal list of payslips.
     * @param payslip The Payslip object to be added.
     */
    public static void addPayslip(Payslip payslip) {
        payslips.add(payslip);
    }
}
