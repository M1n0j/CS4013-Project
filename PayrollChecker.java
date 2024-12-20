import java.io.*;
import java.time.DayOfWeek;
import java.util.*;
import java.time.LocalDate;

public class PayrollChecker {

    private static final String EMPLOYEES_FILE_PATH = "src/Resources/Employees.csv";
    private static final String PAYSLIPS_FILE_PATH = "src/Resources/Payslips.csv";


    public void checker(String[] args) {
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


    public static boolean isPayrollDay(LocalDate date) {
        return date.getDayOfMonth() == 25;
    }


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

    // Method to read employees from CSV and return a list of Employee objects
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

    // Method to generate a payslip for an employee on payday
    private Payslip generatePayslip(Employee employee, LocalDate payday) {
        // Generate payslip based on the employee data (You can adapt this as per your logic)
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

    public static void writePayslipToCSV(Payslip payslip) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Resources/Payslips.csv", true))) {
            writer.write(payslip.getEmployeeId() + "," + payslip.toCSV() + "\n");
            System.out.println("Payslip for Employee ID " + payslip.getEmployeeId() + " written to CSV.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        PayrollChecker payrollChecker = new PayrollChecker();
        payrollChecker.checker(args); // Start the payroll check and payslip generation
    }
}
