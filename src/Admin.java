import java.io.*;

public class Admin {
    // Static final constants for CSV file paths
    public static final String EMPLOYEES_CSV_PATH = "src/resources/employees.csv";
    public static final String PAYSLIPS_CSV_PATH = "src/resources/payslips.csv";
    public static final String USERS_CSV_PATH = "src/resources/users.csv";

    // Static header constants for CSV files
    public static final String EMPLOYEES_CSV_HEADER = "employeeId,name,email,role,salaryScale,currentPoint,isFullTime";
    public static final String PAYSLIPS_CSV_HEADER = "employeeId,name,email,role,salaryScale,currentPoint,isFullTime";
    public static final String USERS_CSV_HEADER = "userId,password,role";

    // Method to add employee (to be used in AdminMenu)
    public void addEmployee(Employee employee, boolean isFullTime, String salaryScaleId, int currentPoint, String password, Integer employeeId) throws IOException {
        int newEmployeeId = (employeeId != null) ? employeeId : generateNewEmployeeId();
        int newUserId = newEmployeeId; // Use the same ID for user

        // Prepare CSV lines
        String employeeCSVLine = String.format("%d,%s,%s,%s,%s,%d,%b",
                newEmployeeId, employee.getName(), employee.getEmail(), employee.getPosition(),
                isFullTime ? salaryScaleId : "N/A", currentPoint, isFullTime);
        String payslipCSVLine = String.format("%d,%s,%s,%s,%s,%d,%b",
                newEmployeeId, employee.getName(), employee.getEmail(), employee.getPosition(),
                isFullTime ? salaryScaleId : "N/A", currentPoint, isFullTime);
        String userCSVLine = String.format("%d,%s,%s", newUserId, password, isFullTime ? "Full-Time" : "Part-Time");

        // Write to the CSV files
        writeToCSV(EMPLOYEES_CSV_PATH, EMPLOYEES_CSV_HEADER, employeeCSVLine);
        writeToCSV(PAYSLIPS_CSV_PATH, PAYSLIPS_CSV_HEADER, payslipCSVLine);
        writeToCSV(USERS_CSV_PATH, USERS_CSV_HEADER, userCSVLine);
    }

    // Helper method to write data to a CSV file
    private void writeToCSV(String filePath, String header, String dataLine) throws IOException {
        File file = new File(filePath);
        boolean fileExists = file.exists();

        if (!fileExists) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println(header);
                writer.println(dataLine);
            }
        } else {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                writer.println(dataLine); // Append data
            }
        }
    }

    // Generate a new unique employee ID by finding the max existing ID
    private int generateNewEmployeeId() throws IOException {
        int maxId = 0;
        File file = new File(EMPLOYEES_CSV_PATH);

        if (!file.exists()) {
            return 1; // Start at employeeId 1 if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine(); // Skip the header

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int currentId = Integer.parseInt(fields[0]);
                maxId = Math.max(maxId, currentId); // Get max employeeId
            }
        }

        return maxId + 1; // Return the next available employeeId
    }
}
