public class Employee {
    private int employeeId;
    private String name;
    private String email;
    private String role;
    private String salaryScale;
    private String currentPoint; // Fixed: Now a String to align with the SalaryScale level
    private boolean isFullTime;
    private int yearsAtTopPoint;

    // Constructor
    public Employee(int employeeId, String name, String email, String role, String salaryScale,
                    String currentPoint, boolean isFullTime, int yearsAtTopPoint) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.salaryScale = salaryScale;
        this.currentPoint = currentPoint;
        this.isFullTime = isFullTime;
        this.yearsAtTopPoint = yearsAtTopPoint;
    }

    // Getter methods
    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getSalaryScale() { return salaryScale; }
    public String getCurrentPoint() { return currentPoint; }
    public boolean isFullTime() { return isFullTime; }
    public int getYearsAtTopPoint() { return yearsAtTopPoint; }

    // Static method to parse a CSV line into an Employee object
    public static Employee fromCSV(String csvLine) {
        String[] values = csvLine.split(",");  // Split the line by commas
        if (values.length != 8) {
            throw new IllegalArgumentException("Invalid CSV format: " + csvLine);
        }

        try {
            int employeeId = Integer.parseInt(values[0].trim());
            String name = values[1].trim();
            String email = values[2].trim();
            String role = values[3].trim();
            String salaryScale = values[4].trim();
            String currentPoint = values[5].trim(); // Fixed: Parsing current point as String
            boolean isFullTime = Boolean.parseBoolean(values[6].trim());
            int yearsAtTopPoint = Integer.parseInt(values[7].trim());

            return new Employee(employeeId, name, email, role, salaryScale, currentPoint, isFullTime, yearsAtTopPoint);
        } catch (Exception e) {
            System.err.println("Error parsing employee data: " + csvLine);
            throw new RuntimeException(e);
        }
    }

    // Method to format employee data for CSV
    public String toCSV() {
        return employeeId + "," + name + "," + email + "," + role + "," + salaryScale + "," +
                currentPoint + "," + isFullTime + "," + yearsAtTopPoint;
    }

    // Method to display employee details
    @Override
    public String toString() {
        return "Employee{id=" + employeeId + ", name=" + name + ", role=" + role +
                ", currentPoint=" + currentPoint + ", fullTime=" + isFullTime + "}";
    }
}
