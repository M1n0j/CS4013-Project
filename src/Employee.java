import java.time.LocalDate;

public abstract class Employee {
    private String name;
    private int employeeId;
    private String email;
    private String position;
    private boolean promotion;
    private boolean fullTime;


    // Constructor with all fields
    public Employee(String name, int employeeId, String email, String position, boolean promotion) {
        this.name = name;
        this.employeeId = employeeId;
        this.email = email;
        this.position = position;
        this.promotion = promotion;
        this.fullTime = fullTime;
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

    public boolean isPromotion() {return promotion;}

    public boolean isFullTime() {return fullTime;}

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

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public void setFullTime(boolean fullTime) {this.fullTime = fullTime;}

    // Abstract method to calculate salary (to be implemented by subclasses)
    public abstract double calculateSalary();

    public abstract String toCSV();
}
