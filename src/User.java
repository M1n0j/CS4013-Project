public class User {
    private String employeeID;
    private String password;
    private String role; // Role for user (Admin, HR, etc.)

    // Constructor with employeeID, password, and role
    public User(String employeeID, String password, String role) {
        this.employeeID = employeeID;
        this.password = password;
        this.role = role;
    }

    // Constructor for cases where the role isn't needed (optional)
    public User(String employeeID, String password) {
        this.employeeID = employeeID;
        this.password = password;
        this.role = "Unknown";
    }

    // Getters and Setters
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


        if (fields.length < 2) { // Assuming le Bomberclarting CSV has two parameters
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
