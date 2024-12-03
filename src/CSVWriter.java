import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVWriter {
    private String employeeCSVPath;
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
    public void writeEmployees(List<Employee> employees, String s) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/Employees"))) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/Payslips"))) {
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
    public boolean deleteRow(int id, String path) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String header = reader.readLine();
            lines.add(header);

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].isEmpty()) {
                    continue;
                }
                int currentId = Integer.parseInt(values[0]);

                if (currentId != id) {
                    lines.add(line);
                } else {
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Employee not found");
                return false;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (int i = 0; i < lines.size(); i++) {
                writer.write(lines.get(i));
                if (i < lines.size() - 1) {
                    writer.newLine();
                }
            }
            writer.close();
            return true;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}