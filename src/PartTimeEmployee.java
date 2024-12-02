public class PartTimeEmployee extends Employee {

    private double hourlyPay;
    private double hoursWorked;


    public PartTimeEmployee(int employeeId, String name, String email, String position, int level, boolean fullTime, boolean promotion) {
        super(employeeId, name, email, position, level, promotion, fullTime);  // Call superclass constructor
        this.hoursWorked = hoursWorked;
        this.hourlyPay = hourlyPay;
    }

    public double getHourlyPay() {
        return hourlyPay;
    }
    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }
    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }


    public static PartTimeEmployee fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        return new PartTimeEmployee(
                Integer.parseInt(fields[0]),
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
        double grossSalary = hoursWorked * hourlyPay;
        Deductions deductions = new Deductions();
        return deductions.calcDeductions(grossSalary);
    }

    @Override
    public String toCSV() {
        return getEmployeeId() + "," + getName() + "," + getEmail() + "," + getPosition() + ","
                + getLevel() + "," + isFullTime() + "," + isPromotion() + ",";
    }
}
