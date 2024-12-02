import java.time.LocalDate;

public class Payslip {
    private static double grossSalary;
    private static double netSalary;
    private static Deductions deductions;

    public Payslip(double grossSalary) {
        this.grossSalary = grossSalary;
        this.deductions = new Deductions();
        calculateDeductions();
        this.netSalary = grossSalary - deductions.getTotalDeductions();
    }

    public void calculateDeductions() {
        deductions.calcDeductions(grossSalary);
    }



    public static void printPayslip() {
        System.out.println("\n----- Payslip -----");
        System.out.println("Gross Salary: €" + String.format("%.2f", grossSalary));
        System.out.println("PRSI: €" + String.format("%.2f", deductions.getPrsi()));
        System.out.println("USC: €" + String.format("%.2f", deductions.getUsc()));
        System.out.println("Income Tax: €" + String.format("%.2f", deductions.getIncomeTax()));
        System.out.println("Union Fee: €" + String.format("%.2f", deductions.getUnionFee()));
        System.out.println("Total Deductions: €" + String.format("%.2f", deductions.getTotalDeductions()));
        System.out.println("Net Salary: €" + String.format("%.2f", netSalary));
        System.out.println("-------------------");
    }
}