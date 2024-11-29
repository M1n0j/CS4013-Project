import java.time.LocalDate;

public class FullTimeEmployee extends  Employee{

    private int salary;


    public FullTimeEmployee(String name, int employeeId, LocalDate dateOfBirth, String address, String email, String phoneNumber, String department, String position, int salary) {
        super(name, employeeId, dateOfBirth, address, email, phoneNumber, department, position);
        this.salary = salary;
    }

    @Override
    public double calculateSalary() {
        return 0;
    }

}
