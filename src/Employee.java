/**
 * Abstract class representing an employee in the system
 * Provides common properties and methods for both full-time and part-time employees
 */

public abstract class Employee {
    private String name;
    private int employeeId;
    private String email;
    private String position;
    private int level;
    private boolean promotion;
    public boolean fullTime;
    protected CSVReader csvReader;
    protected CSVWriter csvWriter;

    /**
     * Constructs an Employee object with the specified attributes
     * @param employeeId The unique identifier for the employee
     * @param name       The name of the employee
     * @param email      The email address of the employee
     * @param position   The position or job title of the employee
     * @param level      The level of the employee in the organization
     * @param promotion  Indicates if the employee is eligible for promotion
     * @param fullTime   Indicates if the employee is a full-time employee
     */
    public Employee(int employeeId, String name, String email, String position, int level, boolean promotion, boolean fullTime) {
        this.name = name;
        this.employeeId = employeeId;
        this.email = email;
        this.position = position;
        this.level = level;
        this.promotion = promotion;
        this.fullTime = fullTime;
    }
    /**
     * Gets the name of the employee
     * @return The employee's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the unique identifier of the employee.
     * @return The employee's ID.
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Gets the email address of the employee.
     * @return The employee's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the position or job title of the employee
     * @return The employee's position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Checks if the employee is eligible for promotion
     * @return True if eligible for promotion, false otherwise
     */
    public boolean isPromotion() {
        return promotion;
    }

    /**
     * Checks if the employee is a full-time employee
     * @return True if the employee is full-time, false otherwise
     */
    public boolean isFullTime() {
        return fullTime;
    }

    /**
     * Sets the name of the employee
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the unique identifier for the employee
     * @param employeeId The employee ID to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Sets the email address of the employee
     * @param email The email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the position or job title of the employee
     * @param position The position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Sets whether the employee is eligible for promotion
     * @param promotion True if eligible for promotion, false otherwise
     */
    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    /**
     * Sets whether the employee is a full-time employee
     * @param fullTime True if the employee is full-time, false otherwise
     */
    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    /**
     * Sets the level of the employee in the organization
     * @param level The level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets the current pay
     * @return The employee's level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Abstract method to convert the employee's details to a CSV format string
     * This must be implemented by subclasses
     * @return A csv representation of the employee's details
     */
    public abstract String toCSV();

    /**
     * Abstract method to calculate the salary for the current pay period
     * This must be implemented by subclasses
     * @return The calculated salary for the current period
     */
    public abstract double calculateSalaryForCurrentPeriod();
}
