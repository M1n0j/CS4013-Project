import java.util.ArrayList;
import java.util.List;

public class EmployeePayslips {
    private int employeeId;
    private List<Payslip> payslips;

    public EmployeePayslips(int employeeId) {
        this.employeeId = employeeId;
        this.payslips = new ArrayList<>();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public List<Payslip> getPayslips() {
        return payslips;
    }

    public void addPayslip(Payslip payslip) {
        payslips.add(payslip);
    }

    public Payslip getMostRecent() {
        return !payslips.isEmpty() ? payslips.get(payslips.size() - 1) : null;
    }
}