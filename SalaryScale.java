public class SalaryScale {
    private String positionName;
    private String level;
    private double salary;

    // Constructor
    public SalaryScale(String positionName, String level, double salary) {
        this.positionName = positionName;
        this.level = level;
        this.salary = salary;
    }

    // Getter methods
    public String getPositionName() {
        return positionName;
    }

    public String getLevel() {
        return level;
    }

    public double getSalary() {
        return salary;
    }

    // Method to parse the CSV line into a SalaryScale object
    public static SalaryScale fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        if (values.length != 3) {
            throw new IllegalArgumentException("Invalid CSV format: " + csvLine);
        }

        try {
            String positionName = values[0].replaceAll("\"", "").trim();  // Remove quotes
            String level = values[1].replaceAll("\"", "").trim();  // Remove quotes
            // Ensure to remove quotes around the salary value and parse it as double
            double salary = Double.parseDouble(values[2].replaceAll("\"", "").trim());

            return new SalaryScale(positionName, level, salary);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing salary scale data: " + csvLine);
            throw new RuntimeException(e);
        }
    }

    // Method to convert the SalaryScale object to a CSV format string
    public String toCSV() {
        return positionName + "," + level + "," + salary;
    }

    @Override
    public String toString() {
        return "SalaryScale{" + "positionName='" + positionName + "', level='" + level + "', salary=" + salary + '}';
    }
}
