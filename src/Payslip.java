import java.time.LocalDate;

public class Payslip {
    private int employeeId;
    private LocalDate paymentDate;
    private double grossPay;
    private Deductions deductions;
    private double netPay;

    public Payslip(int employeeId, LocalDate paymentDate, double grossPay) {
        this.employeeId = employeeId;
        this.paymentDate = paymentDate;
        this.grossPay = grossPay;
        this.deductions = new Deductions();
        calculateDeductions();
    }

    private void calculateDeductions() {
        // Calculate PRSI (4%)
        deductions.setPrsi(grossPay * 0.04);

        // Calculate USC (2%)
        deductions.setUsc(grossPay * 0.02);

        // Calculate Income Tax (20% on first €36,800, 40% on remainder)
        double annualGrossPay = grossPay * 12;
        double incomeTax = 0;

        if (annualGrossPay <= 36800) {
            incomeTax = grossPay * 0.20;
        } else {
            double standardRate = (36800 / 12) * 0.20;
            double higherRate = (grossPay - (36800 / 12)) * 0.40;
            incomeTax = standardRate + higherRate;
        }
        deductions.setIncomeTax(incomeTax);

        // Calculate net pay
        netPay = grossPay - deductions.getTotalDeductions();
    }

    public void generatePayslip() {
        System.out.println("=== PAYSLIP ===");
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Payment Date: " + paymentDate);
        System.out.println("Gross Pay: €" + String.format("%.2f", grossPay));
        System.out.println("\nDeductions:");
        System.out.println("PRSI: €" + String.format("%.2f", deductions.getPrsi()));
        System.out.println("USC: €" + String.format("%.2f", deductions.getUsc()));
        System.out.println("Income Tax: €" + String.format("%.2f", deductions.getIncomeTax()));
        System.out.println("\nNet Pay: €" + String.format("%.2f", netPay));
    }

    //TESTING -------------------------------
    public String getEmployeeId() {
        return String.valueOf(employeeId);
    }

    public String getPaymentDate() {
        return paymentDate.toString();
    }


    public String getGrossPay() {
        return getGrossPay();
    }
}