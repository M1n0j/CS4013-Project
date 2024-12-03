import java.io.FileReader;
import java.io.FileWriter;

public class User {
    private String employeeID;
    private String password;
    private String role;
    protected CSVWriter csvWriter;
    protected CSVReader csvReader;

    public User(String employeeID, String password, String role) {
        this.employeeID = employeeID;
        this.password = password;
        this.role = role;
        this.csvWriter = new CSVWriter("src/Resources/Employees.csv","src/Resources/user.csv");
        this.csvReader = new CSVReader("src/Resources/Employees.csv","src/Resources/user.csv","src/Resources/Salaries.csv");
    }

    public User(String employeeID, String password) {
        this.employeeID = employeeID;
        this.password = password;
        this.role = "Unknown";
        this.csvWriter = new CSVWriter("src/Resources/Employees.csv","src/Resources/user.csv");
        this.csvReader = new CSVReader("src/Resources/Employees.csv","src/Resources/user.csv","src/Resources/Salaries.csv");
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "employeeID='" + employeeID + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    /**
     * Static method to create a User object from a CSV line.
     *
     * @param csvLine The CSV line containing user data.
     * @return A User object populated with data from the CSV line.
     */
    public static User fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");


        if (fields.length < 2) {
            throw new IllegalArgumentException("Invalid CSV line for User: " + csvLine);
        }

        String employeeID = fields[0];
        String password = fields[1];
        String role = fields.length > 2 ? fields[2] : "Unknown";

        return new User(employeeID, password, role);
    }

    
    public String toCSV() {
        return employeeID + "," + password + "," + role;
    }
}
