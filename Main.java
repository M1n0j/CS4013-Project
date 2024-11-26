import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String EMPLOYEES_CSV_PATH = "src/resources/Employees.csv";
    private static final String EMPLOYEES_CSV_HEADER = "employeeId,name,email,role,salaryScale,currentPoint,isFullTime,yearsAtTopPoint";

    public static void main(String[] args) {
        try {
            // Ensure the CSV file is ready (header is correct or file is created)
            validateOrCreateCsvFile();

            // Add a new full-time employee
            Employee fullTimeEmployee = new Employee("John Doe", 0, LocalDate.of(1990, 1, 1),
                    "123 Main St", "john.doe@example.com",
                    "123-456-7890", "Engineering",
                    "Software Engineer") {
                @Override
                public double calculateSalary() {
                    return 60000; // Example salary calculation
                }
            };

            addEmployeeToCsv(fullTimeEmployee, true, "A1", 3);
            System.out.println("Full-time employee added successfully.");

            // Add a part-time employee
            Employee partTimeEmployee = new Employee("Jane Smith", 0, LocalDate.of(1995, 5, 15),
                    "456 Oak St", "jane.smith@example.com",
                    "987-654-3210", "Design",
                    "Graphic Designer") {
                @Override
                public double calculateSalary() {
                    return 30000; // Example salary calculation
                }
            };

            addEmployeeToCsv(partTimeEmployee, false, "N/A", 0);
            System.out.println("Part-time employee added successfully.");

            // Remove an employee by ID (example: removing employee with ID 1)
            removeEmployeeFromCsv(2);  // This should remove the employee with ID 2
        } catch (IOException e) {
            System.err.println("An error occurred while managing employees: " + e.getMessage());
        }
    }

    private static void validateOrCreateCsvFile() throws IOException {
        File file = new File(EMPLOYEES_CSV_PATH);

        // If file doesn't exist, create it with header
        if (!file.exists()) {
            try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
                out.println(EMPLOYEES_CSV_HEADER);
            }
            return;
        }

        // Read the file contents and verify header
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String header = reader.readLine();
            if (!EMPLOYEES_CSV_HEADER.equals(header)) {
                // Rewrite the file with correct header
                rewriteCsvWithCorrectHeader(reader);
            }
        }
    }

    private static void rewriteCsvWithCorrectHeader(BufferedReader reader) throws IOException {
        List<String> fileContents = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            fileContents.add(line);
        }

        // Rewrite the file with the correct header
        try (PrintWriter out = new PrintWriter(new FileWriter(EMPLOYEES_CSV_PATH))) {
            out.println(EMPLOYEES_CSV_HEADER);
            for (String content : fileContents) {
                out.println(content);
            }
        }
    }

    private static void addEmployeeToCsv(Employee employee, boolean isFullTime, String salaryScaleId, int currentPoint) throws IOException {
        // Generate a new unique employee ID
        int newEmployeeId = generateEmployeeId();

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
        try (FileWriter fw = new FileWriter(EMPLOYEES_CSV_PATH, true); // Open in append mode
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(csvLine); // Write the line for the new employee
            out.flush(); // Ensure data is written immediately
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    private static int generateEmployeeId() throws IOException {
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
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length > 0) {
                    try {
                        int currentId = Integer.parseInt(fields[0]);
                        maxId = Math.max(maxId, currentId);
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping malformed line (non-numeric employee ID): " + line);
                        continue; // Skip lines with invalid employee IDs
                    }
                }
            }
        }

        return maxId + 1;
    }

    private static void removeEmployeeFromCsv(int employeeId) throws IOException {
        File file = new File(EMPLOYEES_CSV_PATH);
        List<String> fileContents = new ArrayList<>();

        // Read the file contents
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Read header and add to the list
            String header = reader.readLine();
            fileContents.add(header);

            // Read the rest of the lines and collect those that don't match the employeeId
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length > 0) {
                    try {
                        int currentId = Integer.parseInt(fields[0]);
                        if (currentId != employeeId) {
                            fileContents.add(line); // If the ID doesn't match, keep the line
                        } else {
                            found = true; // Mark the employee as found and removed
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping malformed line (non-numeric employee ID): " + line);
                        continue; // Skip lines with invalid employee IDs
                    }
                }
            }

            if (!found) {
                System.out.println("Employee with ID " + employeeId + " was not found.");
                return; // Exit if the employee was not found
            }
        }

        // Write the filtered list back to the file
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (String line : fileContents) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }

        System.out.println("Employee with ID " + employeeId + " has been removed.");
    }
}
