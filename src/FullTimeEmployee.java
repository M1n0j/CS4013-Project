import java.util.List;

public class FullTimeEmployee extends Employee {

    public FullTimeEmployee(int employeeId, String name, String email, String position, int level, boolean fullTime, boolean promotion) {

        super(employeeId, name, email, position, level, fullTime, promotion);
    }

    // Instance method to create a FullTimeEmployee from a CSV line
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
        return getEmployeeId() + "," + getName() + "," + getEmail() + "," + getPosition() + "," + getLevel() + "," + isFullTime() + "," + isPromotion();
    }
}