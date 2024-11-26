import java.time.LocalDate;

public class PartTimeEmployee extends Employee {

    private double hourlyPay;
    private double hoursWorked;

    public PartTimeEmployee(String name, int employeeId, LocalDate dateOfBirth, String address, String email, String phoneNumber, String department, String position, double hoursWorked, double hourlyPay) {
        super(name, employeeId, dateOfBirth, address, email, phoneNumber, department, position);
        this.hoursWorked = hoursWorked;
        this.hourlyPay = hourlyPay;
    }

    @Override
    public double calculateSalary() {
        return hourlyPay * hoursWorked * 52 /12; //Monthly salary based on weekly hours
    }
}
