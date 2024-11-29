import java.time.LocalDate;

public class FullTimeEmployee extends Employee{


    public FullTimeEmployee(String name, int employeeId, String address, String email, String department, String position, boolean promotion) {
        super(name, employeeId, email, department, position, promotion);
    }

    @Override
    public double calculateSalary() {
        return 0;
    }

}
