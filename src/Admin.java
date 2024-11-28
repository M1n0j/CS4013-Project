import java.io.*;

public class Admin {
    //  Final constants for CSV file paths(Bomberclart)
    public final String EmployeesCsv = "src/resources/employees.csv";
    public final String PayslipsCsv = "src/resources/payslips.csv";
    public final String UsersCSV = "src/resources/users.csv";

    // Header constants for CSV files
    public final String EmployeesCsvHeader = "employeeId,name,email,role,salaryScale,currentPoint,isFullTime";
    public final String PayslipsCsvHeader = "employeeId,name,email,role,salaryScale,currentPoint,isFullTime";
    public final String UsersCsvHeader = "userId,password,role";

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
        writeToCSV(EmployeesCsv, EmployeesCsvHeader, employeeCSVLine);
        writeToCSV(PayslipsCsv, PayslipsCsvHeader, payslipCSVLine);
        writeToCSV(UsersCSV, UsersCsvHeader, userCSVLine);
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
        File file = new File(EmployeesCsv);

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
