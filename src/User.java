public class User {
    private int employeeId; // Unique ID for the user
    private String password; // Account password

    // Constructor
    public User(int employeeId, String password) {
        this.employeeId = employeeId;
        this.password = password;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method to verify password
    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    @Override
    public String toString() {
        return "Users{employeeId=" + employeeId + "}";
    }
}
