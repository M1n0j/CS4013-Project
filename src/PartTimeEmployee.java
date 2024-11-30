import java.time.LocalDate;

public class PartTimeEmployee extends Employee {

    private double hourlyPay;
    private double hoursWorked;

    public PartTimeEmployee(String name, int employeeId, String employeePassword, String email, String department, String position, boolean promotion, double hoursWorked, double hourlyPay) {
        super(name, employeeId, employeePassword, email, department, position, promotion);
    }


    @Override
    public double calculateSalary() {
        return 0;
    }
}
