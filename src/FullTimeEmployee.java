
public class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name, int employeeId, String employeePassword, String email, String department, String position, boolean promotion) {
        super(name, employeeId, employeePassword, email, department, position, promotion);
    }

    // Instance method to create a FullTimeEmployee from a CSV line
    public static FullTimeEmployee fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        return new FullTimeEmployee(
            fields[1], // name
            Integer.parseInt(fields[0]), // employeeId
            fields[2], // employeePassword
            fields[3], // email
            fields[4], // department
            fields[5], // position
            Boolean.parseBoolean(fields[6]) // promotion
        );
    }

    @Override
    public double calculateSalary() {
        return 0; // Implement salary calculation logic here
    }

    @Override
    public String toCSV() {
        return getEmployeeId() + "," + getName() + "," + getEmail() + "," + getDepartment() + "," + getPosition() + "," + getPromotion();
    }
}