import java.time.LocalDate;

public abstract class Employee {
    private String name;
    private int employeeId;
    private String employeePassword;
    private String email;
    private String role;
    private String position;
    private boolean promotion;


    // Constructor with all fields
    public Employee(String name, int employeeId, String employeePassword, String email, String role, String position, boolean promotion) {
        this.name = name;
        this.employeeId = employeeId;
        this.email = email;
        this.role = role;
        this.position = position;
        this.promotion = promotion;
        this.employeePassword = employeePassword;
    }

    // Default constructor
    public Employee() {
        this.name = "";
        this.employeeId = 0;
        this.email = "";
        this.role = "";
        this.position = "";

        this.promotion = false;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }


    public void setEmail(String email) {
        this.email = email;
    }



    public void setDepartment(String department) {
        this.role = role;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public boolean getPromotion() {
        return promotion;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }
    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }



    // Abstract method to calculate salary (to be implemented by subclasses)
    public abstract double calculateSalary();

    // Method to print employee details
    public void printEmployee() {
        System.out.println("Name: " + name);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
        System.out.println("Position: " + position);
        System.out.println("Promotion: " + promotion);
    }
}
