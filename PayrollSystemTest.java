import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PayrollSystemTest {
    public static void main(String[] args) {
        try {
            // Test CSV Reading
            testEmployeeReading();

            // Test Payroll Processing
            testPayrollProcessing();

            // Test Work Claims
            testWorkClaimProcessing();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testEmployeeReading() throws IOException {
        System.out.println("--- Employee Reading Test ---");
        List<Employee> employees = CSVReader.readEmployees("Employees.csv");

        System.out.println("Total Employees Loaded: " + employees.size());

        // Sample validation
        if (!employees.isEmpty()) {
            Employee firstEmployee = employees.get(0);
            System.out.println("First Employee: " + firstEmployee.getName());
            System.out.println("Employee ID: " + firstEmployee.getEmployeeId());
        }
    }

    private static void testPayrollProcessing() throws IOException {
        System.out.println("\n--- Payroll Processing Test ---");
        PayrollService payrollService = new PayrollService();

        // Process monthly payroll
        payrollService.processMonthlyPayroll();

        // Test retrieving payslip history for a specific employee
        List<Payslip> payslipHistory = payrollService.getEmployeePayslipHistory(1001); // Example employee ID

        System.out.println("Payslip History Size: " + payslipHistory.size());

        // Print most recent payslip details
        Payslip mostRecentPayslip = payrollService.getMostRecentPayslip(1001);
        if (mostRecentPayslip != null) {
            System.out.println("Most Recent Payslip Details:");
            System.out.println("Gross Pay: €" + mostRecentPayslip.getGrossPay());
        }
    }

    private static void testWorkClaimProcessing() throws IOException {
        System.out.println("\n--- Work Claim Processing Test ---");
        PayrollService payrollService = new PayrollService();

        // Create a sample work claim
        WorkClaim newClaim = new WorkClaim(
                LocalDate.now(),
                10.5,
                "Tutorial Assistance"
        );

        // Add and test work claim
        payrollService.addWorkClaim(newClaim);

        List<WorkClaim> claims = payrollService.getWorkClaims();
        System.out.println("Total Work Claims: " + claims.size());

        // Validate last added claim
        if (!claims.isEmpty()) {
            WorkClaim lastClaim = claims.get(claims.size() - 1);
            System.out.println("Last Claim Hours: " + lastClaim.getHoursWorked());
        }
    }
}
