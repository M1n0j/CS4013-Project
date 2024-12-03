import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for writing and managing CSV files for employees, users, and payslips.
 */
public class CSVWriter {
    private String employeeCSVPath;
    private String userCSVPath;
    private String paySlipCSVPath;

    /**
     * Constructs a CSVWriter object with specified file paths
     * @param employeeCSVPath The file path for the employee CSV file
     * @param userCSVPath     The file path for the user csv file
     * @param paySlipCSVPath  The file path for the payslip csv file
     */
    public CSVWriter(String employeeCSVPath, String userCSVPath, String paySlipCSVPath) {
        this.employeeCSVPath = employeeCSVPath;
        this.userCSVPath = userCSVPath;
        this.paySlipCSVPath = paySlipCSVPath;

    }

    /**
     * Writes a list of User objects to the user csv file
     * @param users The list of User objects to write
     * @throws IOException If an I/O error occurs during writing
     */
    public void writeUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userCSVPath))) {
            writer.newLine();
            writer.write("userID, userPassword, Role");

            for (User  user : users) {
                writer.newLine();
                writer.write(user.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Error writing user file: " + e.getMessage());
        }
    }

    /**
     * Writes a list of Employee objects to the employee CSV file.
     *
     * @param employees The list of Employee objects to write
     * @param s A file path
     * @throws IOException If an I/O error occurs during writing
     */
    public void writeEmployees(List<Employee> employees, String s) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Resources/Employees.csv"))) {
            writer.newLine();
            writer.write("employeeId, name, email, position, level, isFullTime, promotion");

            for (Employee employee : employees) {
                writer.newLine();
                writer.write(employee.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Error writing employee file: " + e.getMessage());
        }
    }

    /**
     * Writes a list of Payslip objects to the payslip csv file
     * @param payslips The list of Payslip objects to write
     * @param payslipCSVPath The file path for the payslip csv file
     * @throws IOException If an I/O error occurs during writing
     */
    public void writePayslips(List<Payslip> payslips, String payslipCSVPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Resources/Payslips.csv"))) {
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

    /**
     * Deletes a row from a csv file based on a specific ID
     * @param id   The ID of the row to delete
     * @param path The file path of the csv file
     * @return True if the row was successfully deleted, false if the ID was not found
     * @throws RuntimeException If an I/O error occurs during reading or writing
     */
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