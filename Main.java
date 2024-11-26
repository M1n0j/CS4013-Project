import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        PayrollService payrollService = new PayrollService();

        // Process payroll for all employees
        payrollService.processMonthlyPayroll();

        // Retrieve and print payslip history for a specific employee
        int employeeId = 1; // Example employee ID
        List<Payslip> history = payrollService.getEmployeePayslipHistory(employeeId);
        for (Payslip payslip : history) {
            payslip.generatePayslip();
        }
    }
}
