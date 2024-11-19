import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalaryScaleDataHandler {
    private static final String SALARY_SCALES_FILE = "src/resources/salaries.csv";

    public static List<SalaryScale> readSalaryScales() {
        List<SalaryScale> salaryScales = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(SALARY_SCALES_FILE))) {
            String line;
            boolean isFirstLine = true; // To handle the header row
            while ((line = br.readLine()) != null) {
                // Skip the header row
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Parse and add the salary scale
                SalaryScale scale = SalaryScale.fromCSV(line);
                salaryScales.add(scale);
            }
        } catch (IOException e) {
            System.err.println("Error reading salary scales file: " + e.getMessage());
        }

        return salaryScales;
    }
}
