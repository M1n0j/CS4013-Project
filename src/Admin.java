import java.io.*;

public class Admin {
    public final String EmployeesCsv = "src/resources/employees.csv";
    public final String UsersCSV = "src/resources/user.csv";


    public final String EmployeesCsvHeader = "employeeId,name,email,role,salaryScale,currentPoint,isFullTime, promotion";
    public final String UsersCsvHeader = "userId,password,role, promotion";


    public void addEmployee(Employee employee, boolean isFullTime, String salaryScaleId, int currentPoint, String password, Integer employeeId) throws IOException {;
        String employeeCSVLine = employee.toCSV();
        String userCSVLine = String.format("%d,%s,%s,%b", employee.getEmployeeId(), password, isFullTime ? "Full-Time" : "Part-Time", false);


        writeToCSV(EmployeesCsv, EmployeesCsvHeader, employeeCSVLine);
        writeToCSV(UsersCSV, UsersCsvHeader, userCSVLine);
    }


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
                writer.println(dataLine);
            }
        }
    }


    private int generateNewEmployeeId() throws IOException {
        int maxId = 0;
        File file = new File(EmployeesCsv);

        if (!file.exists()) {
            return 1;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int currentId = Integer.parseInt(fields[0]);
                maxId = Math.max(maxId, currentId);
            }
        }

        return maxId + 1;
    }
}
