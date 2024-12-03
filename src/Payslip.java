import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Payslip {
    private double netSalary;
    private Deductions deductions;
    private SalaryScale salaryScale;
    private int salary;
    private PartTimeEmployee partTimeEmployee;
    private int employeeId;
    private String payPeriod;

    // Constructor to initialize Payslip with employeeId and calculate salary based on the available data
    public Payslip(int employeeId) {
        this.employeeId = employeeId;
        this.salaryScale = new SalaryScale("", 0, 0);
        this.deductions = new Deductions();
        this.partTimeEmployee = new PartTimeEmployee(0, "", "", "", 0, false, false);
        this.partTimeEmployee.setHoursWorked();

        readSalaryDataFromCSV("src/Resources/Salaries.csv");
        if (salaryScale.getSalary() > 0) {
            this.deductions.calcDeductions(salaryScale.getSalary());
            this.netSalary = salaryScale.getSalary() - deductions.getTotalDeductions();
        } else if (salaryScale.getSalary() == 0) {
            double grossSalary = partTimeEmployee.calculateSalaryForCurrentPeriod();
            this.deductions.calcDeductions(grossSalary);
            this.netSalary = grossSalary - deductions.getTotalDeductions();
        }
    }

    // Method to read salary data from the provided CSV file
    private void readSalaryDataFromCSV(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                salaryScale = salaryScale.fromSalaries(line); // Assuming the fromSalaries method is available
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getter and Setter Methods
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary() {
        this.netSalary = netSalary;
    }

    public Deductions getDeductions() {
        return deductions;
    }

    public void setDeductions(Deductions deductions) {
        this.deductions = deductions;
    }

    public SalaryScale getSalaryScale() {
        return salaryScale;
    }

    public void setSalaryScale(SalaryScale salaryScale) {
        this.salaryScale = salaryScale;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public PartTimeEmployee getPartTimeEmployee() {
        return partTimeEmployee;
    }

    public void setPartTimeEmployee(PartTimeEmployee partTimeEmployee) {
        this.partTimeEmployee = partTimeEmployee;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    // Method to print the payslip
    public void printPayslip() {
        System.out.println("\n----- Payslip -----");
        if (salaryScale.getSalary() > 0) {
            System.out.println("Gross Salary: €" + String.format("%.2f", salaryScale.getSalary()));
        } else if (salaryScale.getSalary() == 0) {
            System.out.println("Gross Salary: €" + String.format("%.2f", partTimeEmployee.calculateSalaryForCurrentPeriod()));
        }
        System.out.println("PRSI: €" + String.format("%.2f", this.deductions.getPrsi()));
        System.out.println("USC: €" + String.format("%.2f", this.deductions.getUsc()));
        System.out.println("Income Tax: €" + String.format("%.2f", this.deductions.getIncomeTax()));
        System.out.println("Union Fee: €" + String.format("%.2f", this.deductions.getUnionFee()));
        System.out.println("Total Deductions: €" + String.format("%.2f", this.deductions.getTotalDeductions()));
        System.out.println("Net Salary: €" + String.format("%.2f", this.netSalary));
        System.out.println("-------------------");
    }

    // Method to return payslip data as CSV
    public String toCSV() {
        return String.format("%.2f", salaryScale.getSalary()) + "," +
                String.format("%.2f", deductions.getPrsi()) + "," +
                String.format("%.2f", deductions.getUsc()) + "," +
                String.format("%.2f", deductions.getIncomeTax()) + "," +
                String.format("%.2f", deductions.getUnionFee()) + "," +
                String.format("%.2f", deductions.getTotalDeductions()) + "," +
                String.format("%.2f", netSalary);
    }

    public void setGrossSalary() {
        return;
    }

    public void setIncomeTax() {
        return;
    }

    public void setPrsi() {
        return;
    }

    public void setUsc() {
        return;
    }
}
