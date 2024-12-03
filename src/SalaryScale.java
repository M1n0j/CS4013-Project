/**
 * Represents a salary scale with a position, level, and associated salary.
 */
public class SalaryScale {
    private String position;
    private int level;
    public double salary;

    /**
     * Constructs a SalaryScale object with the specified position, level, and salary
     * @param position The job position associated with this salary scale
     * @param level    The level of the position
     * @param salary   The salary for this position and level
     */
    public SalaryScale(String position, int level, double salary) {
        this.position = position;
        this.level = level;
        this.salary = salary;
    }

    /**
     * Gets the position associated with this salary scale
     * @return The position as a string
     */
    public String getPosition() {
        return position;
    }

    /**
     * Gets the level of the position
     * @return The level as an integer
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the salary for the position and level
     * @return The salary as a double
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Converts the SalaryScale object to a csv string format
     * @return A csv representation of the SalaryScale object
     */
    public String toCSV() {
        return position + "," + level + "," + salary;
    }

    /**
     * Parses a csv line to update the current SalaryScale object
     * @param csvLine The csv line that holds salary scale data
     * @return The updated SalaryScale object
     * @throws IllegalArgumentException if the csv line is null, empty, or improperly formatted
     */
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
