import java.time.LocalDate;

public class Payslip {
    private int employeeId;
    private double grossPay;
    private double netPay;
    private int payslipId;
    private String name;
    private LocalDate payPeriod;
    private Deductions deductions;

    public Payslip(int employeeId, double grossPay, double netPay, int payslipId, String name, LocalDate payPeriod) {
        this.employeeId = employeeId;
        this.grossPay = grossPay;
        this.netPay = netPay;
        this.payslipId = payslipId;
        this.name = name;
        this.payPeriod = payPeriod;
        this.deductions = new Deductions();
    }

    private void calculateDeductions() {
        deductions.setPrsi(grossPay * 0.04);
        deductions.setUsc(grossPay * 0.02);
        deductions.setIncomeTax(grossPay * 0.20);

        netPay = grossPay - deductions.calcDeductions(grossPay);
    }

    public void newPayslip() {
        System.out.println("--------PAYSLIP--------");
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Payment Date: " + payPeriod);
        System.out.println("Gross Pay: €" + String.format("%.2f", grossPay));
        System.out.println("------Deductions:------");
        System.out.println("PRSI: €" + String.format("%.2f", deductions.getPrsi()));
        System.out.println("USC: €" + String.format("%.2f", deductions.getUsc()));
        System.out.println("Income Tax: €" + String.format("%.2f", deductions.getIncomeTax()));
        System.out.println("Net Pay: €" + String.format("%.2f", netPay));
    }
}
