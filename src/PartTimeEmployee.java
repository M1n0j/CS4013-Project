
public class PartTimeEmployee extends Employee {

    private double hourlyPay;
    private double hoursWorked;

    public PartTimeEmployee(String name, int employeeId, String employeePassword, String email, String department, String position, boolean promotion, double hoursWorked, double hourlyPay) {
        super(name, employeeId, employeePassword, email, department, position, promotion);
        this.hoursWorked = hoursWorked;
        this.hourlyPay = hourlyPay;
    }

    // Instance method to create a PartTimeEmployee from a CSV line
    public static PartTimeEmployee fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        return new PartTimeEmployee(
            fields[1], // name
            Integer.parseInt(fields[0]), // employeeId
            fields[2], // employeePassword
            fields[3], // email
            fields[4], // department
            fields[5], // position
            Boolean.parseBoolean(fields[6]), // promotion
            0.0, // Placeholder for hoursWorked
            0.0  // Placeholder for hourlyPay 
        );
    }

    @Override
    public double calculateSalary() {
        return hourlyPay * hoursWorked; // Implement salary calculation logic here
    }

    @Override
    public String toCSV() {
        return getEmployeeId() + "," + getName()  + "," + getEmail() + "," + getDepartment() + "," + getPosition() + "," + getPromotion() + "," + hoursWorked + "," + hourlyPay;
    }

}