import java.io.FileNotFoundException;
import java.io.PrintWriter;

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

    public void writePayslipToCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
        writer.println("Gross Salary,PRSI,USC,Income Tax,Union Fee,Total Deductions,Net Salary");
        writer.println(toCSV());
        writer.println("\n----- Payslip -----");
        writer.println("Gross Salary: €" + String.format("%.2f", this.grossSalary));
        writer.println("PRSI: €" + String.format("%.2f", this.deductions.getPrsi()));
        writer.println("USC: €" + String.format("%.2f", this.deductions.getUsc()));
        writer.println("Income Tax: €" + String.format("%.2f", this.deductions.getIncomeTax()));
        writer.println("Union Fee: €" + String.format("%.2f", this.deductions.getUnionFee()));
        writer.println("Total Deductions: €" + String.format("%.2f", this.deductions.getTotalDeductions()));
        writer.println("Net Salary: €" + String.format("%.2f", this.netSalary));
        writer.println("-------------------");
    } catch (FileNotFoundException e) {
        System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}