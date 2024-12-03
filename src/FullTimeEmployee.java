import java.util.List;

public class FullTimeEmployee extends Employee {


    public FullTimeEmployee(int employeeId, String name, String email, String position, int level, boolean fullTime, boolean promotion) {

        super(employeeId, name, email, position, level, fullTime, promotion);
    }

    public static FullTimeEmployee fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        return new FullTimeEmployee
                (Integer.parseInt(fields[0]),
            fields[1],
            fields[2],
            fields[3],
            Integer.parseInt(fields[4]),
            Boolean.parseBoolean(fields[5]),
            Boolean.parseBoolean(fields[6])
        );
    }

    @Override
    public double calculateSalary() {
        List<SalaryScale> salaryScaleList = csvReader.readSalaryScales();

        double grossSalary = 0.0;

        for (SalaryScale s : salaryScaleList) {
            if (s.getPosition().equalsIgnoreCase(this.getPosition()) && s.getLevel() == this.getLevel()) {
                grossSalary = s.getSalary();
                System.out.println("Match found: Gross Salary = " + grossSalary);
                break;
            }
        }

        if (grossSalary == 0.0) {
            System.err.println("Error: No match found for Position: " + this.getPosition() + ", Level: " + this.getLevel());
            return 0.0; // No salary found, returning 0.
        }

        Deductions deductions = new Deductions();
        double totalDeductions = deductions.calcDeductions(grossSalary);

        double netSalary = grossSalary - totalDeductions;
        System.out.println("Gross Salary: " + grossSalary);
        System.out.println("Total Deductions: " + totalDeductions);
        System.out.println("Net Salary: " + netSalary);

        return netSalary;
    }

    @Override
    public String toCSV() {
        return getEmployeeId() + "," + getName() + "," + getEmail() + "," + getPosition() + "," + getLevel() + "," + isFullTime() + "," + isPromotion();
    }

    @Override
    public double calculateSalaryForCurrentPeriod() {
        return 0;
    }
}