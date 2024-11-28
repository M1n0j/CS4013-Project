import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    private static final String EMPLOYEES_CSV_PATH = "src/resources/Payslips.csv";
    private static final String EMPLOYEES_CSV_HEADER = "employeeId,name,email,role,salaryScale,currentPoint,isFullTime,yearsAtTopPoint";

    /**
     * Adds a new employee to the Employees.csv file (whether full-time or part-time)
     *
     * @param employee Employee object containing all the data fields
     * @param isFullTime Whether the employee is full-time or part-time
     * @param salaryScaleId The salary scale identifier for full-time employees (N/A for part-time)
     * @param currentPoint The current point on the salary scale for full-time employees (0 for part-time)
     * @throws IOException If there's an error writing to the file
     */
    public void addEmployee(Employee employee, boolean isFullTime, String salaryScaleId, int currentPoint) throws IOException {
        // Generate a new unique employee ID
        int newEmployeeId = generateNewEmployeeId();

        // Prepare the CSV line for the employee
        String csvLine = String.format("%d,%s,%s,%s,%s,%d,%b,%d",
                newEmployeeId,
                employee.getName(),
                employee.getEmail(),
                employee.getPosition(),
                isFullTime ? salaryScaleId : "N/A", // Full-time gets salaryScale, part-time gets N/A
                currentPoint,
                isFullTime, // Whether the employee is full-time
                0 // Set yearsAtTopPoint to 0 for both (can be modified later)
        );

        // Append to the CSV file
        try (FileWriter fw = new FileWriter(EMPLOYEES_CSV_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(csvLine);
        }
    }

    /**
     * Generates a new unique employee ID by finding the maximum existing ID and adding 1
     *
     * @return A new unique employee ID
     * @throws IOException If there's an error reading the CSV file
     */
    private int generateNewEmployeeId() throws IOException {
        int maxId = 0;
        File file = new File(EMPLOYEES_CSV_PATH);

        // If file doesn't exist, return 1
        if (!file.exists()) {
            return 1;
        }

        // Read the file and find the maximum ID
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Skip the header
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    int currentId = Integer.parseInt(fields[0]);
                    maxId = Math.max(maxId, currentId);
                }
            }
        }

        return maxId + 1;
    }

    /**
     * Validates the CSV file structure and ensures the header is correct
     *
     * @throws IOException If there's an error with file operations
     */
    public void validateOrCreateCsvFile() throws IOException {
        File file = new File(EMPLOYEES_CSV_PATH);

        // If file doesn't exist, create it with header
        if (!file.exists()) {
            try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
                out.println(EMPLOYEES_CSV_HEADER);
            }
            return;
        }

        // Read the file contents
        List<String> fileContents = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Read first line (header)
            String header = reader.readLine();

            // If header is incorrect, collect remaining lines
            if (!EMPLOYEES_CSV_HEADER.equals(header)) {
                while ((line = reader.readLine()) != null) {
                    fileContents.add(line);
                }
            } else {
                // Header is correct, no need to do anything
                return;
            }
        }

        // Rewrite the file with correct header
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            out.println(EMPLOYEES_CSV_HEADER);
            for (String line : fileContents) {
                out.println(line);
            }
        }
    }
}
