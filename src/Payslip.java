import java.util.List;

public class Payslip {
    private double grossSalary;
    private double netSalary;
    private Deductions deductions;

    public Payslip(double grossSalary) {
        this.grossSalary = grossSalary;
        this.deductions = new Deductions();
        this.netSalary = grossSalary - deductions.getTotalDeductions();
    }

    public void printPayslip() {
        System.out.println("\n----- Payslip -----");
        System.out.println("Gross Salary: €" + String.format("%.2f", this.grossSalary));
        System.out.println("PRSI: €" + String.format("%.2f", this.deductions.getPrsi()));
        System.out.println("USC: €" + String.format("%.2f", this.deductions.getUsc()));
        System.out.println("Income Tax: €" + String.format("%.2f", this.deductions.getIncomeTax()));
        System.out.println("Union Fee: €" + String.format("%.2f", this.deductions.getUnionFee()));
        System.out.println("Total Deductions: €" + String.format("%.2f", this.deductions.getTotalDeductions()));
        System.out.println("Net Salary: €" + String.format("%.2f", this.netSalary));
        System.out.println("-------------------");
    }

    public String toCSV() {

        return String.format("%.2f", grossSalary) + "," +
                String.format("%.2f", deductions.getPrsi()) + "," +
                String.format("%.2f", deductions.getUsc()) + "," +
                String.format("%.2f", deductions.getIncomeTax()) + "," +
                String.format("%.2f", deductions.getUnionFee()) + "," +
                String.format("%.2f", deductions.getTotalDeductions()) + "," +
                String.format("%.2f", netSalary);

    }

}