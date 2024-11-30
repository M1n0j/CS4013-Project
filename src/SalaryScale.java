public class SalaryScale {
    private String position;
    private int level;
    private double salary;

    // Constructor to initialize the salary scale object
    public SalaryScale(String position, int level, double salary) {
        this.position = position;
        this.level = level;
        this.salary = salary;
    }

    // Getters for SalaryScale
    public String getPosition() {
        return position;
    }

    public int getLevel() {
        return level;
    }

    public double getSalary() {
        return salary;
    }

    // Method to convert the SalaryScale object to a CSV string format (for writing to CSV)
    public String toCSV() {
        return position + "," + level + "," + salary;
    }

    // Static method to create a SalaryScale object from a CSV line
    public static SalaryScale fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");

        // Handle potential errors in CSV format
        if (fields.length < 3) {
            throw new IllegalArgumentException("Invalid CSV line for SalaryScale: " + csvLine);
        }

        String position = fields[0];
        int level = Integer.parseInt(fields[1]);
        double salary = Double.parseDouble(fields[2]);

        return new SalaryScale(position, level, salary);
    }
}
