import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    private static String employeeCSVPath;
    private String userCSVPath;

    public CSVWriter(String employeeCSVPath, String userCSVPath) {
        this.employeeCSVPath = employeeCSVPath;
        this.userCSVPath = userCSVPath;
    }

    /**
     * Writes a list of User objects to the user CSV file.
     */
    public void writeUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userCSVPath))) {
            writer.write("userID, userPassword, Role");
            writer.newLine();

            for (User  user : users) {
                writer.write(user.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing user file: " + e.getMessage());
        }
    }

    /**
     * Writes a list of Employee objects to the employee CSV file.
     */
    public static void writeEmployees(List<Employee> employees, String s) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(s))) {
            writer.write("employeeId, name, email, position, level, isFullTime, promotion");
            writer.newLine();

            for (Employee employee : employees) {
                writer.write(employee.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing employee file: " + e.getMessage());
        }
    }

/**
 * Writes a list of Payslip objects to the payslip CSV file.
 */
    public void writePayslips(List<Payslip> payslips, String payslipCSVPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(payslipCSVPath))) {
        writer.write("payslipId, employeeId, payDate, grossPay, tax, netPay");
        writer.newLine();

        for (Payslip payslip : payslips) {
            writer.write(payslip.toCSV());
            writer.newLine();
        }
    } catch (IOException e) {
        System.err.println("Error writing payslip file: " + e.getMessage());
    }
}


}