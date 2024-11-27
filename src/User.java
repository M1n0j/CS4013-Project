import java.util.Scanner;

public abstract class User {
    private String employeeID;
    private String password;

    public User(String employeeID, String password) {
        this.employeeID = employeeID;
        this.password = password;
    }

    //getters and setter methods
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    public String getPassword() {
        return this.password.equals(inputPassword);
    }

    public abstract void displayUserMenu(Scanner scanner);

    public void setPassword(String password) {
        this.password = password;
    }


}
