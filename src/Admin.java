import java.io.*;

public class Admin {
    public final String EmployeesCsv = "src/Resources/employees.csv";
    public final String UsersCSV = "src/Resources/user.csv";


    public final String EmployeesCsvHeader = "employeeId,name,email,role,salaryScale,currentPoint,isFullTime, promotion";
    public final String UsersCsvHeader = "userId,password,position";


    public void addEmployee(Employee employee, boolean isFullTime, String salaryScaleId, int currentPoint, String password, Integer employeeId) throws IOException {;
        String employeeCSVLine = employee.toCSV();
        String userCSVLine = String.format("%d,%s,%s", employee.getEmployeeId(), password, employee.getPosition());


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

}
