import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Payslip {
    private double netSalary;
    private Deductions deductions;
    private SalaryScale salaryScale;
    private int salary;
    private PartTimeEmployee partTimeEmployee;


    public Payslip() {
        this.salaryScale = new SalaryScale("",0,0);
        this.deductions = new Deductions();

        readSalaryDataFromCSV("src/Resources/Salaries.csv");
        if (salaryScale.getSalary() > 0) {
            this.deductions.calcDeductions(salaryScale.getSalary());
            this.netSalary = salaryScale.getSalary() - deductions.getTotalDeductions();
        } else if (salaryScale.getSalary() < 0 && partTimeEmployee != null) {
            double grossSalary = partTimeEmployee.calculateSalaryForCurrentPeriod();
            this.deductions.calcDeductions(grossSalary);
            this.netSalary = grossSalary - deductions.getTotalDeductions();
        }
    }


    private void readSalaryDataFromCSV(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {

                salaryScale = salaryScale.fromSalaries(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void printPayslip() {
        System.out.println("\n----- Payslip -----");
        System.out.println("Gross Salary: €" + String.format("%.2f",salaryScale.getSalary()));
        System.out.println("PRSI: €" + String.format("%.2f", this.deductions.getPrsi()));
        System.out.println("USC: €" + String.format("%.2f", this.deductions.getUsc()));
        System.out.println("Income Tax: €" + String.format("%.2f", this.deductions.getIncomeTax()));
        System.out.println("Union Fee: €" + String.format("%.2f", this.deductions.getUnionFee()));
        System.out.println("Total Deductions: €" + String.format("%.2f", this.deductions.getTotalDeductions()));
        System.out.println("Net Salary: €" + String.format("%.2f", this.netSalary));
        System.out.println("-------------------");
    }

    public String toCSV() {

        return String.format("%.2f", salaryScale.getSalary()) + "," +
                String.format("%.2f", deductions.getPrsi()) + "," +
                String.format("%.2f", deductions.getUsc()) + "," +
                String.format("%.2f", deductions.getIncomeTax()) + "," +
                String.format("%.2f", deductions.getUnionFee()) + "," +
                String.format("%.2f", deductions.getTotalDeductions()) + "," +
                String.format("%.2f", netSalary);

    }

}