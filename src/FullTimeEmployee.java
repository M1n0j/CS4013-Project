import java.util.List;

/**
 * Represents a full-time employee in the organization
 * Extends the Employee class to provide specific functionality for full-time employees
 */
public class FullTimeEmployee extends Employee {

    /**
     * Constructs a FullTimeEmployee object with the specified attributes
     * @param employeeId The unique identifier for the employee
     * @param name       The name of the employee
     * @param email      The email address of the employee
     * @param position   The position or job title of the employee
     * @param fullTime   Indicates if the employee is full-time
     * @param promotion  Indicates if the employee is eligible for promotion
     */
    public FullTimeEmployee(int employeeId, String name, String email, String position, int level, boolean fullTime, boolean promotion) {

        super(employeeId, name, email, position, level, fullTime, promotion);
    }

    /**
     * Parses a csv line to create a FullTimeEmployee object
     * @param csvLine The csv line containing employee details
     * @return A FullTimeEmployee object created from the csv line
     */
    public static FullTimeEmployee fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        return new FullTimeEmployee
                (Integer.parseInt(fields[0]),
            fields[1],
            fields[2],
            fields[3],
            Integer.parseInt(fields[4]),
            Boolean.parseBoolean(fields[5]),
            Boolean.parseBoolean(fields[6])
        );
    }



    /**
     * Converts the FullTimeEmployee object to a csv format string
     * @return A csv representation of the FullTimeEmployee details
     */
    @Override
    public String toCSV() {
        return getEmployeeId() + "," + getName() + "," + getEmail() + "," + getPosition() + "," + getLevel() + "," + isFullTime() + "," + isPromotion();
    }

    /**
     * Calculates the salary for the current pay period for the full-time employee
     * This method is not yet implemented and currently returns 0
     * @return The salary for the current pay period, currently 0
     */
    @Override
    public double calculateSalaryForCurrentPeriod() {
        return 0;
    }


}