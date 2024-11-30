import java.time.LocalDate;

public class FullTimeEmployee extends Employee{


    public FullTimeEmployee(String name, int employeeId, String employeePassword, String address, String email, String department, String position, boolean promotion) {
        super(name, employeeId, employeePassword, email, department, position, promotion);
    }


    @Override
    public double calculateSalary() {
        return 0;
    }

}
