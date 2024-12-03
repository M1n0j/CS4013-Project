import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PayslipManager {
    private static final String FILE_PATH = "src/Resources/Payslips.csv";


    private static List<Payslip> payslips = new ArrayList<>();

    public static void writePayslipToCSV(Payslip payslip) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(payslip.getEmployeeId() + "," + payslip.toCSV() + "\n");
            System.out.println("Payslip for Employee ID " + payslip.getEmployeeId() + " written to CSV.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }


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


    public static void addPayslip(Payslip payslip) {
        payslips.add(payslip);
    }
}
