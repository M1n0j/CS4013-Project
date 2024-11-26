import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    // Save Payslip to CSV
    public static void savePayslip(Payslip payslip, String filePath) throws IOException {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            // Format: employeeId,paymentDate,grossPay,prsi,usc,incomeTax,netPay
            String payslipData = String.format("%d,%s,%.2f,%.2f,%.2f,%.2f,%.2f",
                    payslip.getEmployeeId(),
                    payslip.getPaymentDate(),
                    payslip.getGrossPay(),
                    Deductions.getPrsi(),
                    Deductions.getUsc(),
                    Deductions.getIncomeTax()
            );

            bw.write(payslipData);
            bw.newLine();
        }
    }

    // Additional methods for saving other entities can be added here
}
