import java.time.LocalDate;

public class Payslip {
    private int employeeId;
    private double grossPay;
    private double netPay;
    private int payslipId;
    private String name;
    private LocalDate payPeriod;
    private double usc;
    private double prsi;
    private double incomeTax;

    public Payslip(int employeeId, double grossPay, double netPay, int payslipId, String name, LocalDate payPeriod, double usc, double prsi, double incomeTax) {
    this.employeeId = employeeId;
    this.grossPay = grossPay;
    this.netPay = netPay;
    this.payslipId = payslipId;
    this.name = name;
    this.payPeriod = payPeriod;
    this.usc = usc;
    this.prsi = prsi;
    this.incomeTax = incomeTax;

    private void calculateNetPay() {
        netPay = grossPay - Deductions.getTotalDeductions();
    }

    private void calculateDeductions() {
        // Calculate PRSI (4%)
        Deductions.setPrsi(grossPay * 0.04);

        // Calculate USC (2%)
        Deductions.setUsc(grossPay * 0.02);

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
        Deductions.setIncomeTax(incomeTax);

        // Calculate net pay
        //netPay = grossPay - deductions.getTotalDeductions();
    }

    public void generatePayslip() {
        System.out.println("=== PAYSLIP ===");
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Payment Date: " + payPeriod);
        System.out.println("Gross Pay: €" + String.format("%.2f", grossPay));
        System.out.println("\nDeductions:");
        System.out.println("PRSI: €" + String.format("%.2f", Deductions.getPrsi()));
        System.out.println("USC: €" + String.format("%.2f", Deductions.getUsc()));
        System.out.println("Income Tax: €" + String.format("%.2f", Deductions.getIncomeTax()));
        System.out.println("\nNet Pay: €" + String.format("%.2f", netPay));
    }
}