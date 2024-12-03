public class SalaryScale {
    private String position;
    private int level;
    public double salary;

    public SalaryScale(String position, int level, double salary) {
        this.position = position;
        this.level = level;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public int getLevel() {
        return level;
    }

    public double getSalary() {
        return salary;
    }

    public String toCSV() {
        return position + "," + level + "," + salary;
    }

    public SalaryScale fromSalaries(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            throw new IllegalArgumentException("CSV line cannot be null or empty: " + csvLine);
        }

        String[] fields = csvLine.split(",");

        if (fields.length < 3) {
            throw new IllegalArgumentException("Invalid CSV line for SalaryScale: " + csvLine);
        }

        // Parse the salary from the CSV data
        double salary = Double.parseDouble(fields[2]);

        // Update the current object fields
        this.salary = salary;

        // Return the current SalaryScale object
        return this;
    }
}
