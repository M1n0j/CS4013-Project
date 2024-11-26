import java.time.LocalDate;

public class FullTimeEmployee extends Employee {
    private SalaryScale salaryScale; // refers to an instance of the SalaryScale class
    private int currentPoint;
    private double annualSalary;

    // Constructor to initialize FullTimeEmployee
    public FullTimeEmployee(String name, int employeeId, LocalDate dateOfBirth,
                            String address, String email, String phoneNumber,
                            String department, String position,
                            SalaryScale salaryScale, int currentPoint) {
        super(name, employeeId, dateOfBirth, address, email, phoneNumber, department, position);

        // Set salaryScale and currentPoint
        this.salaryScale = salaryScale;
        this.currentPoint = currentPoint;

        // Get the annual salary based on the currentPoint from the salaryScale
        this.annualSalary = salaryScale.getSalaryPoint(currentPoint);
    }

    @Override
    public double calculateSalary() {
        return annualSalary / 12; // Monthly salary calculation
    }

    public void incrementSalaryPoint() {
        if (!salaryScale.isAtMaxPoint(currentPoint)) {
            currentPoint++;
            this.annualSalary = salaryScale.getSalaryPoint(currentPoint);
        }
    }
}
