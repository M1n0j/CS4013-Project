
public class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name, int employeeId, String email,  String position, boolean promotion, int level, boolean fullTime) {

        super(name, employeeId, email, position, promotion, fullTime);
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
        return 0; // Implement salary calculation logic here
    }

    @Override
    public String toCSV() {
        return getEmployeeId() + "," + getName() + "," + getEmail() + "," + getPosition() + "," + isPromotion() + "," + isFullTime();
    }
}