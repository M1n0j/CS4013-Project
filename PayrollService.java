import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayrollService {
    private List<Employee> employees;
    private List<SalaryScale> salaryScales;
    private List<WorkClaim> workClaims;
    private PayslipHistory payslipHistory;

    public PayrollService() {
        try {
            // Initialize data by reading from CSV files
            this.employees = CSVReader.readEmployees("Employees.csv");
            //this.salaryScales = CSVReader.getSalaryPoints("Salaries.csv");
            this.workClaims = CSVReader.readWorkClaims("WorkClaims.csv");
            this.payslipHistory = new PayslipHistory();

            // Initialize payslip history for all employees
            for (Employee employee : employees) {
                payslipHistory.initializeEmployeeHistory(employee.getEmployeeId());
            }
        } catch (IOException e) {
            // Handle the exception, log it, or take action based on your needs
            System.err.println("Error loading data from CSV files: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void addEmployee(Employee employee) {
        employees.add(employee);
        payslipHistory.initializeEmployeeHistory(employee.getEmployeeId());

        // Save updated employee list to the CSV (not implemented in CSVFileHandler but can be added)
        // Example: CSVFileHandler.saveEmployees(employees, "Employees.csv");
    }

    public void processMonthlyPayroll() throws IOException {
        LocalDate paymentDate = LocalDate.now().withDayOfMonth(25);

        for (Employee employee : employees) {
            double grossPay = employee.calculateSalary();
            Payslip payslip = new Payslip(employee.getEmployeeId(), paymentDate, grossPay);

            // Store payslip in history
            payslipHistory.addPayslip(employee.getEmployeeId(), payslip);

            // Save the payslip to the CSV file
            CSVWriter.savePayslip(payslip, "Payslips.csv");

            // Optionally print the payslip for verification
            payslip.generatePayslip();
        }
    }

    public List<Payslip> getEmployeePayslipHistory(int employeeId) {
        return payslipHistory.getHistory(employeeId);
    }

    public Payslip getMostRecentPayslip(int employeeId) {
        return payslipHistory.getMostRecent(employeeId);
    }

    public void addWorkClaim(WorkClaim claim) {
        workClaims.add(claim);

        // Save updated work claims list to the CSV (not implemented in CSVFileHandler but can be added)
        // Example: CSVFileHandler.saveWorkClaims(workClaims, "WorkClaims.csv");
    }

    public List<WorkClaim> getWorkClaims() {
        return new ArrayList<>(workClaims);
    }
}
