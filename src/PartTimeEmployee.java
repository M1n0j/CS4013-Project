import java.util.Scanner;

public class PartTimeEmployee extends Employee {

    private double hourlyPay;
    private double hoursWorked;

    /**
     * Constructs a PartTimeEmployee object with the specified attributes
     * @param employeeId The unique identifier for the employee
     * @param name       The name of the employee
     * @param email      The email address of the employee
     * @param position   The position or job title of the employee
     * @param level      The level of the employee in the organization
     * @param promotion  Indicates if the employee is eligible for promotion
     * @param fullTime   Indicates if the employee is a full-time employee
     */
    public PartTimeEmployee(int employeeId, String name, String email, String position, int level, boolean promotion, boolean fullTime) {
        super(employeeId, name, email, position, level, promotion, fullTime);
        this.hoursWorked = hoursWorked;
        this.hourlyPay = 15;
    }

    /**
     * Updates the hours worked for the employee based on user input
     * @return The updated total hours worked
     */
    public double setHoursWorked() {
        this.hoursWorked = inputHoursWorked();
        return this.hoursWorked;
    }

    /**
     * Parses a csv line to create a PartTimeEmployee object
     * @param csvLine The csv line containing employee details
     * @return A PartTimeEmployee object created from the csv line
     */
    public static PartTimeEmployee fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        return new PartTimeEmployee(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                fields[3],
                Integer.parseInt(fields[4]),
                Boolean.parseBoolean(fields[5]),
                Boolean.parseBoolean(fields[6])
        );
    }

    /**
     * Converts the PartTimeEmployee object to a csv format string
     * @return A csv representation of the PartTimeEmployee details
     */
    @Override
    public String toCSV() {
        return getEmployeeId() + "," + getName() + "," + getEmail() + "," + getPosition() + ","
                + getLevel() + "," + isFullTime() + "," + isPromotion() + ",";
    }

    /**
     * Prompts the user to input hours worked and updates the employee's total hours worked
     * @return The updated total hours worked
     */
    public double inputHoursWorked() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter hours worked for " + getName() + ": ");
        try {
            double hours = Double.parseDouble(scanner.nextLine());
            if (hours <= 0) {
                System.out.println("Hours worked can't be 0 or negative!");
            }
            this.hoursWorked += hours;
            System.out.println(hours + " hours added to total hours worked.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! No hours added.");
        }
        return hoursWorked;
    }

    public double getCurrentPayHours() {
        return this.hoursWorked;
    }

    /**
     * Calculates the net salary for the current pay period based on hours worked and hourly pay rate
     * Deducts applicable amounts from the gross salary
     * @return The net salary for the current pay period
     */
    @Override
    public double calculateSalaryForCurrentPeriod() {
        double grossSalary = hoursWorked * hourlyPay;
        Deductions deductions = new Deductions();
        return deductions.calcDeductions(grossSalary);
    }
}