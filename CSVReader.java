import java.io.*;
import java.util.List;

public class CSVReader {

    // Write data to a CSV file
    public static void writeToCSV(String filePath, String header, String dataLine) throws IOException {
        File file = new File(filePath);
        boolean fileExists = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (!fileExists) {
                writer.write(header);  // Write header if the file is new
                writer.newLine();
            }
            writer.write(dataLine);  // Write the data
            writer.newLine();
        }
    }

    // Get the max ID from the CSV to generate the next employee ID
    public static int getMaxId(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int maxId = 0;

        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            if (values.length > 0) {
                try {
                    int id = Integer.parseInt(values[0].trim());
                    maxId = Math.max(maxId, id);  // Keep track of the max ID
                } catch (NumberFormatException e) {
                    // Ignore invalid ID format
                }
            }
        }
        reader.close();
        return maxId;
    }
}
