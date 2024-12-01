public class PartTimeEmployee extends Employee {

    private double hourlyPay;
    private double hoursWorked;

    // Constructor for PartTimeEmployee
    public PartTimeEmployee(String name, int employeeId, String email, String position, int level, boolean promotion, boolean fullTime, double hoursWorked, double hourlyPay) {
        super(name, employeeId, email, position, level, promotion, fullTime);  // Call superclass constructor
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


    // Factory method to create PartTimeEmployee from a CSV string
    public static PartTimeEmployee fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        return new PartTimeEmployee(
                fields[1], // name
                Integer.parseInt(fields[0]), // employeeId
                fields[2], // email
                fields[3], // position
                Integer.parseInt(fields[4]), // level
                Boolean.parseBoolean(fields[5]), // promotion
                Boolean.parseBoolean(fields[6]), // fullTime (needs to be correctly parsed from CSV)
                Double.parseDouble(fields[7]), // hoursWorked
                Double.parseDouble(fields[8])  // hourlyPay
        );
    }

    // Method to calculate salary based on hours worked and hourly pay
    @Override
    public double calculateSalary() {
        double grossSalary = hoursWorked * hourlyPay;
        Deductions deductions = new Deductions();
        return deductions.calcDeductions(grossSalary);
    }

    // Method to convert PartTimeEmployee to a CSV string
    @Override
    public String toCSV() {
        return getEmployeeId() + "," + getName() + "," + getEmail() + "," + getPosition() + ","
                + getLevel() + "," + isFullTime() + "," + isPromotion() + "," + hoursWorked + "," + hourlyPay;
    }
}
