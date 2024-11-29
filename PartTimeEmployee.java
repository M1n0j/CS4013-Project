import java.time.LocalDate;

public class PartTimeEmployee extends Employee {

    private double hourlyPay;
    private double hoursWorked;

    public PartTimeEmployee(String name, int employeeId, String email, String department, String position, boolean promotion, double hoursWorked, double hourlyPay) {
        super(name, employeeId, email, department, position, promotion);
    }


    @Override
    public double calculateSalary() {
        return 0;
    }
}
