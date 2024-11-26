import java.util.ArrayList;
import java.util.List;

public class PayslipHistory {
    private List<EmployeePayslips> records;

    public PayslipHistory() {
        this.records = new ArrayList<>();
    }

    public void initializeEmployeeHistory(int employeeId) {
        records.add(new EmployeePayslips(employeeId));
    }

    public void addPayslip(int employeeId, Payslip payslip) {
        getEmployeeRecord(employeeId).addPayslip(payslip);
    }

    public List<Payslip> getHistory(int employeeId) {
        EmployeePayslips record = getEmployeeRecord(employeeId);
        return record != null ? new ArrayList<>(record.getPayslips()) : new ArrayList<>();
    }

    public Payslip getMostRecent(int employeeId) {
        EmployeePayslips record = getEmployeeRecord(employeeId);
        return record != null ? record.getMostRecent() : null;
    }

    private EmployeePayslips getEmployeeRecord(int employeeId) {
        return records.stream()
                .filter(record -> record.getEmployeeId() == employeeId)
                .findFirst()
                .orElse(null);
    }
}