public class PartTimeEmployee extends Employee {

    private double hourlyPay;
    private double hoursWorked;

    // Constructor
    public PartTimeEmployee(String name, int employeeId, String email, String position, int level, boolean promotion, double hoursWorked, double hourlyPay) {
        super(name, employeeId, email, position, level, false, false);
        this.hoursWorked = hoursWorked;
        this.hourlyPay = hourlyPay;
    }


    public static PartTimeEmployee fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        return new PartTimeEmployee(
                fields[1], // name
                Integer.parseInt(fields[0]), // employeeId
                fields[2], // email
                fields[3], // position
                Boolean.parseBoolean(fields[4]), // promotion
                Double.parseDouble(fields[5]), // hoursWorked
                Double.parseDouble(fields[6])  // hourlyPay
        );
    }


    @Override
    public double calculateSalary() {
        double grossSalary = hoursWorked * hourlyPay;
        Deductions deductions = new Deductions();
        return deductions.calcDeductions(grossSalary);

    }

    public double


    @Override
    public String toCSV() {
        return getEmployeeId() + "," + getName() + "," + getEmail() + "," + getPosition() + ","
                + isPromotion() + "," + hoursWorked + "," + hourlyPay;
    }
}
