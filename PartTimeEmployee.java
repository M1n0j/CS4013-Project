import java.time.LocalDate;

public class PartTimeEmployee extends Employee {

    private double hourlyPay;
    private double hoursWorked;

    public PartTimeEmployee(String name, int employeeId, LocalDate dateOfBirth, String address, String email, String phoneNumber, String department, String position, double hoursWorked, double hourlyPay) {
        super(name, employeeId, dateOfBirth, address, email, phoneNumber, department, position);
    }

    @Override
    public double calculateSalary() {
        return 0;
    }
}
