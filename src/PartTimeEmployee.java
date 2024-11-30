public class PartTimeEmployee extends Employee {

    private double hourlyPay;
    private double hoursWorked;

    // Constructor
    public PartTimeEmployee(String name, int employeeId, String email, String position, boolean promotion, double hoursWorked, double hourlyPay) {
        super(name, employeeId, email, position, promotion, false);
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
        return hourlyPay * hoursWorked;
    }


    @Override
    public String toCSV() {
        return getEmployeeId() + "," + getName() + "," + getEmail() + "," + getPosition() + ","
                + isPromotion() + "," + hoursWorked + "," + hourlyPay;
    }
}
