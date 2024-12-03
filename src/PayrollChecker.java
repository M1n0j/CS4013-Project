import java.io.*;
import java.time.DayOfWeek;
import java.util.*;
import java.time.LocalDate;

/**
 * The PayrollChecker class handles payroll processing and payslip generation for employees.
 * It checks whether today is a payroll day or the second Friday of the month
 * and generates payslips for all employees accordingly.
 */
public class PayrollChecker {

    /** Path to the CSV file containing employee data. */
    private static final String EMPLOYEES_FILE_PATH = "src/Resources/Employees.csv";

    /** Path to the CSV file where payslips will be saved. */
    private static final String PAYSLIPS_FILE_PATH = "src/Resources/Payslips.csv";

    /**
     * Main method to perform payroll checks and generate payslips.
     */
    public void checker() {
        LocalDate today = LocalDate.now();


        List<Employee> employees = readEmployees(EMPLOYEES_FILE_PATH);


        if (isPayrollDay(today)) {
            System.out.println("Today is payday!");

            for (Employee employee : employees) {
                Payslip payslip = generatePayslip(employee, today);
                writePayslipToCSV(payslip);
            }
        } else {
            System.out.println("Today is not payday.");
        }


        if (isSecondFriday(today)) {
            System.out.println("Part-time staff pay claim is due today.");
        } else {
            System.out.println("Today is not the second Friday of the month.");
        }
    }

    /**
     * Checks if the given date is the payroll day (25th of the month).
     *
     * @param date The date to check.
     * @return true if the date is the 25th, false otherwise.
     */
    public static boolean isPayrollDay(LocalDate date) {
        return date.getDayOfMonth() == 25;
    }

    /**
     * Checks if the given date is the second Friday of the month.
     *
     * @param date The date to check.
     * @return true if the date is the second Friday of the month, false otherwise.
     */
    public static boolean isSecondFriday(LocalDate date) {
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        int daysUntilFriday = DayOfWeek.FRIDAY.getValue() - firstDayOfMonth.getDayOfWeek().getValue();
        if (daysUntilFriday < 0) {
            daysUntilFriday += 7;
        }
        LocalDate firstFriday = firstDayOfMonth.plusDays(daysUntilFriday);
        LocalDate secondFriday = firstFriday.plusDays(7);
        return date.equals(secondFriday);
    }

    /**
     * Reads employees from a CSV file and returns a list of Employee objects.
     *
     * @param filePath The path to the CSV file.
     * @return A list of Employee objects.
     */
    public List<Employee> readEmployees(String filePath) {
        List<Employee> employees = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("Error: Employee CSV file not found at " + filePath);
            return employees;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String header = reader.readLine(); // Read and ignore the header line
            if (header == null) {
                System.err.println("CSV file is empty or cannot be read.");
                return employees;
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 6) {
                    boolean isFullTime = Boolean.parseBoolean(fields[5]);
                    Employee employee;
                    if (isFullTime) {
                        employee = FullTimeEmployee.fromCSV(line);
                    } else {
                        employee = PartTimeEmployee.fromCSV(line);
                    }
                    employees.add(employee);
                } else {
                    System.err.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading employee file: " + e.getMessage());
        }

        return employees;
    }

    /**
     * Generates a payslip for a given employee on a specific payday.
     *
     * @param employee The employee for whom the payslip is generated.
     * @param payday   The date of the payday.
     * @return The generated Payslip object.
     */
    private Payslip generatePayslip(Employee employee, LocalDate payday) {
        Payslip payslip = new Payslip(employee.getEmployeeId());
        payslip.setEmployeeId(employee.getEmployeeId());
        payslip.setGrossSalary();
        payslip.setUsc();
        payslip.setPrsi();
        payslip.setIncomeTax();
        payslip.setNetSalary();
        payslip.setPayPeriod(payday.toString());

        return payslip;
    }

    /**
     * Writes a Payslip object to the payslips CSV file.
     *
     * @param payslip The Payslip object to write.
     */
    public static void writePayslipToCSV(Payslip payslip) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PAYSLIPS_FILE_PATH, true))) {
            writer.write(payslip.getEmployeeId() + "," + payslip.toCSV() + "\n");
            System.out.println("Payslip for Employee ID " + payslip.getEmployeeId() + " written to CSV.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

}
