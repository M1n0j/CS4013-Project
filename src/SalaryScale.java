import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryScale {
    // List to store salary scale data: position, level, and salary
    private List<String[]> salaryData = new ArrayList<>();

    public SalaryScale(String filePath) throws IOException {
        loadSalaryData(filePath);
    }

    // Load salary data from CSV file into the list
    private void loadSalaryData(String filePath) throws IOException {
        try (BufferedReader salaryReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            salaryReader.readLine();  // Skip header line

            while ((line = salaryReader.readLine()) != null) {
                String[] fields = line.split(",");
                salaryData.add(fields); // Store each row (Position, Level, Salary) in the list
            }
        }
    }

    // Method to get the salary based on position and level
    public double getSalary(String position, String level) {
        for (String[] row : salaryData) {
            String role = row[0].trim();  // Position (role)
            String levelInFile = row[1].trim();  // Level
            double salary = Double.parseDouble(row[2].trim());  // Salary

            // If both the role and level match, return the corresponding salary
            if (role.equalsIgnoreCase(position) && levelInFile.equalsIgnoreCase(level)) {
                return salary;
            }
        }
        return -1.0; // Return -1 if no match is found
    }
}
