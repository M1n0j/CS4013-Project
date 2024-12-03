/**
 * Represents a User in the system, typically an employee with a specific role and credentials.
 * The class provides functionality to read and write user data to CSV files.
 */

public class User {
    private String employeeID;
    private String password;
    private String role;
    protected CSVWriter csvWriter;
    protected CSVReader csvReader;

    /**
     * Constructs a User object with specified employee ID, password, and role.
     *
     * @param employeeID The unique identifier for the employee.
     * @param password   The password for the user account.
     * @param role       The role of the user, e.g., "Admin" or "Employee".
     */
    public User(String employeeID, String password, String role) {
        this.employeeID = employeeID;
        this.password = password;
        this.role = role;
        this.csvWriter = new CSVWriter("src/Resources/Employees.csv", "src/Resources/user.csv", "src/Resources/Payslips.csv");
        this.csvReader = new CSVReader("src/Resources/Employees.csv", "src/Resources/user.csv", "src/Resources/Salaries.csv");
    }

    /**
     * Constructs a User object with specified employee ID and password.
     * The role is set to "Unknown" by default.
     *
     * @param employeeID The unique identifier for the employee.
     * @param password   The password for the user account.
     */
    public User(String employeeID, String password) {
        this(employeeID, password, "Unknown");
    }

    /**
     * Gets the employee ID of the user.
     *
     * @return The employee ID.
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the employee ID of the user.
     *
     * @param employeeID The new employee ID.
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     *
     * @return The role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role The new role.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return A string containing the user's details.
     */
    @Override
    public String toString() {
        return "User{" +
                "employeeID='" + employeeID + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    /**
     * Static method to create a User object from a CSV line.
     *
     * @param csvLine The CSV line containing user data.
     * @return A User object populated with data from the CSV line.
     * @throws IllegalArgumentException If the CSV line does not contain at least employee ID and password.
     */
    public static User fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length < 2) {
            throw new IllegalArgumentException("Invalid CSV line for User: " + csvLine);
        }

        String employeeID = fields[0];
        String password = fields[1];
        String role = fields.length > 2 ? fields[2] : "Unknown";

        return new User(employeeID, password, role);
    }

    /**
     * Converts the User object into a CSV-formatted string.
     *
     * @return A string representing the User in CSV format.
     */
    public String toCSV() {
        return employeeID + "," + password + "," + role;
    }
}
