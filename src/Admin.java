import java.io.*;

/**
 * Represents an admin user in the system with functionalities to manage employees and users.
 */
public class Admin {
    public final String EmployeesCsv = "src/Resources/employees.csv";
    public final String UsersCSV = "src/Resources/user.csv";


    public final String EmployeesCsvHeader = "employeeId,name,email,role,salaryScale,currentPoint,isFullTime, promotion";
    public final String UsersCsvHeader = "userId,userPassword,role";

    /**
     * Adds a new employee and creates a corresponding user account in the system
     *
     * @param employee       The Employee object containing the employee's details
     * @param isFullTime     Indicates if the employee is a full-time employee
     * @param salaryScaleId  The ID of the salary scale applicable to the employee
     * @param currentPoint   The current salary point of the employee on the salary scale
     * @param password       The password for the new user's account
     * @param employeeId     The unique identifier for the employee
     * @param role           The role of the employee
     * @throws IOException   If an error occurs during file operations
     */
    public void addEmployee(Employee employee, boolean isFullTime, String salaryScaleId, int currentPoint, String password, Integer employeeId, String role) throws IOException {;
        String employeeCSVLine = employee.toCSV();
        String userCSVLine = String.format("%d,%s,%s",employee.getEmployeeId(), password, role);


        writeToCSV(EmployeesCsv, EmployeesCsvHeader, employeeCSVLine);
        writeToCSV(UsersCSV, UsersCsvHeader, userCSVLine);
    }


    /**
     * Writes data to a specified csv file, adding a header if the file does not exist
     * @param filePath  The path to the csv file
     * @param header    The header line to write if the file is new
     * @param dataLine  The data line to append to the file
     * @throws IOException If an error occurs during file operations
     */
    private void writeToCSV(String filePath, String header, String dataLine) throws IOException {
        File file = new File(filePath);
        boolean fileExists = file.exists();

        if (!fileExists) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(header);
                writer.newLine();
                writer.write(dataLine);
                writer.newLine();
            }
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(dataLine);
                writer.newLine();
            }
        }
    }

}
