import java.util.List;

public class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name, int employeeId, String email, String position, boolean promotion, int level, boolean fullTime) {

        super(name, employeeId, email, position, level, promotion, fullTime);
    }

    // Instance method to create a FullTimeEmployee from a CSV line
    public static FullTimeEmployee fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        return new FullTimeEmployee(
            fields[0], 
            Integer.parseInt(fields[1]), 
            fields[2], 
            fields[3], 
            Boolean.parseBoolean(fields[4]), 
            Integer.parseInt(fields[5]), 
            Boolean.parseBoolean(fields[6]) 
        );
    }

    @Override
    public double calculateSalary() {
        List<SalaryScale> salaryScaleList = CSVReader.readSalaryScales("src/Resources/Salaries.csv");

        double grossSalary = 0.0;
        double netSalary = 0.0;
        for(SalaryScale s : salaryScaleList) {
            if (s.getPosition().equals(this.getPosition()) && s.getLevel() == this.getLevel()) {
                grossSalary = s.getSalary();
                break;
            }
        }

        if (grossSalary == 0.0) {
            System.err.println("No Salary Found");
            return 0.0;
        }
        Deductions deductions = new Deductions();
        double totalDeductions = deductions.calcDeductions(grossSalary);
        netSalary = grossSalary - totalDeductions;

        return netSalary;
    }

    @Override
    public String toCSV() {
        return getEmployeeId() + "," + getName() + "," + getEmail() + "," + getPosition() + "," + isPromotion() + "," + isFullTime();
    }
}