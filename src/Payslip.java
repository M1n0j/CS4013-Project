import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Represents a payslip for an employee, including salary details, deductions, and net pay.
 * Supports both full-time and part-time employees.
 */

public class Payslip {
    private double netSalary;
    private Deductions deductions;
    private SalaryScale salaryScale;
    private int salary;
    private PartTimeEmployee partTimeEmployee;
    private int employeeId;
    private String payPeriod;

    /**
     * Constructs a Payslip object for a specific employee by calculating salary and deductions
     * @param employeeId The unique identifier for the employee
     */
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

    /**
     * Reads salary data from a csv file and updates the salary scale for the employee
     * @param csvFilePath The file path of the salary data csv file
     */
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
    /**
     * Gets the employee ID associated with this payslip
     * @return The employee ID
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the employee ID for this payslip
     * @param employeeId The employee ID to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Gets the net salary for this payslip
     * @return The net salary
     */
    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary() {
        this.netSalary = netSalary;
    }

    /**
     * Gets the deductions associated with this payslip
     * @return The deductions object
     */
    public Deductions getDeductions() {
        return deductions;
    }

    /**
     * Sets the deductions for this payslip
     * @param deductions The deductions object to set
     */
    public void setDeductions(Deductions deductions) {
        this.deductions = deductions;
    }

    /**
     * Gets the salary scale for this payslip.
     *
     * @return The salary scale object.
     */
    public SalaryScale getSalaryScale() {
        return salaryScale;
    }

    /**
     * Sets the salary scale for this payslip
     * @param salaryScale The salary scale object to set
     */
    public void setSalaryScale(SalaryScale salaryScale) {
        this.salaryScale = salaryScale;
    }


    /**
     * Gets the gross salary for this payslip
     * @return The gross salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Sets the gross salary for this payslip
     * @param salary The gross salary to set
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Gets the part-time employee object associated with this payslip
     * @return The PartTimeEmployee object
     */
    public PartTimeEmployee getPartTimeEmployee() {
        return partTimeEmployee;
    }
    /**
     * Sets the part-time employee object for this payslip
     * @param partTimeEmployee The PartTimeEmployee object to set
     */
    public void setPartTimeEmployee(PartTimeEmployee partTimeEmployee) {
        this.partTimeEmployee = partTimeEmployee;
    }

    /**
     * Gets the pay period for this payslip
     * @return The pay period
     */
    public String getPayPeriod() {
        return payPeriod;
    }

    /**
     * Sets the pay period for this payslip
     * @param payPeriod The pay period to set
     */
    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    /**
     * Prints the details of this payslip, including gross salary, deductions, and net salary.
     */
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

    /**
     * Returns a CSV string representation of this payslip
     * @return A CSV string containing gross salary, deductions, and net salary
     */
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
