import java.util.Scanner;

public class PartTimeEmployee extends Employee {

    private double hourlyPay;
    private double hoursWorked;

    public PartTimeEmployee(int employeeId, String name, String email, String position, int level, boolean promotion, boolean fullTime, double hoursWorked, double hourlyPay) {
        super(employeeId, name, email, position, level, promotion, fullTime);
        this.hoursWorked = hoursWorked;
        this.hourlyPay = 15;
    }

    public static PartTimeEmployee fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        return new PartTimeEmployee(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                fields[3],
                Integer.parseInt(fields[4]),
                Boolean.parseBoolean(fields[5]),
                Boolean.parseBoolean(fields[6]),
                Double.parseDouble(fields[7]),
                Double.parseDouble(fields[8])
        );
    }

    @Override
    public double calculateSalary() {
        double grossSalary = hoursWorked * hourlyPay;
        Deductions deductions = new Deductions();
        return deductions.calcDeductions(grossSalary);
    }

    @Override
    public String toCSV() {
        return getEmployeeId() + "," + getName() + "," + getEmail() + "," + getPosition() + ","
                + getLevel() + "," + isFullTime() + "," + isPromotion() + ",";
    }

    public void inputHoursWorked(int HoursWorked) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter hours worked for " + getName() + ": ");
        try {
            double hours = Double.parseDouble(scanner.nextLine());
            if (hours <= 0) {
                System.out.println("Hours worked can't be 0 or negative!");
                return;
            }
            this.hoursWorked += hours;
            System.out.println(hours + " hours added to total hours worked.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! No hours added.");
        }
    }

    public double getCurrentPayHours() {
        return this.hoursWorked;
    }

    @Override
    public double calculateSalaryForCurrentPeriod() {
        double grossSalary = hoursWorked * hourlyPay;
        Deductions deductions = new Deductions();
        return deductions.calcDeductions(grossSalary);
    }
}