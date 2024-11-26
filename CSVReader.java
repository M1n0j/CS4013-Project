import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    // Read Employees from CSV
    public static List<Employee> readEmployees(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Determine employee type based on CSV structure
                if (data.length > 8) {  // Assume full-time if more detailed
                    SalaryScale scale = findSalaryScale(data[8]);

                    FullTimeEmployee employee = new FullTimeEmployee(
                            data[0],                               // name
                            Integer.parseInt(data[1]),             // employeeId
                            LocalDate.parse(data[2]),              // dateOfBirth
                            data[3],                               // address
                            data[4],                               // email
                            data[5],                               // phoneNumber
                            data[6],                               // department
                            data[7],                               // position
                            scale,                                 // salaryScale
                            Integer.parseInt(data[9])              // startingPoint
                    );
                    employees.add(employee);
                } else {
                    // Part-time employee
                    PartTimeEmployee employee = new PartTimeEmployee(
                            data[0],                               // name
                            Integer.parseInt(data[1]),             // employeeId
                            LocalDate.parse(data[2]),              // dateOfBirth
                            data[3],                               // address
                            data[4],                               // email
                            data[5],                               // phoneNumber
                            data[6],                               // department
                            data[7],                               // position
                            Double.parseDouble(data[8]),           // hourlyRate
                            Double.parseDouble(data[9])            // hoursPerWeek
                    );
                    employees.add(employee);
                }
            }
        }
        return employees;
    }

    // Read Work Claims from CSV
    public static List<WorkClaim> readWorkClaims(String filePath) throws IOException {
        List<WorkClaim> claims = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                WorkClaim claim = new WorkClaim(
                        LocalDate.parse(data[0]),     // date
                        Double.parseDouble(data[1]),  // hoursWorked
                        data[2]                       // workDescription
                );

                claims.add(claim);
            }
        }
        return claims;
    }

    // Helper method to find salary scale (you might want to cache this)
    private static SalaryScale findSalaryScale(String scaleId) throws IOException {
        List<SalaryScale> scales = readSalaryScales("salary_scales.csv");
        return scales.stream()
                .filter(scale -> scale.getScaleId().equals(scaleId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Scale not found: " + scaleId));
    }

    // Read Salary Scales from CSV
    private static List<SalaryScale> readSalaryScales(String filePath) throws IOException {
        List<SalaryScale> scales = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                List<Double> salaryPoints = new ArrayList<>();
                String[] pointStrings = data[2].split(";");
                for (String point : pointStrings) {
                    salaryPoints.add(Double.parseDouble(point));
                }

                SalaryScale scale = new SalaryScale(
                        data[0],                               // scaleId
                        data[1],                               // scaleName
                        salaryPoints,                          // salaryPoints
                        Integer.parseInt(data[3])              // maxPoints
                );

                scales.add(scale);
            }
        }
        return scales;
    }
}
