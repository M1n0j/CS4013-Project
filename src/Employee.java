import java.time.LocalDate;

public abstract class Employee {
    private String name;
    private int employeeId;
    private String employeePassword;
    private LocalDate dateOfBirth;
    private String email;
    private String department;
    private String position;
    private boolean promotion;


    // Constructor with all fields
    public Employee(String name, int employeeId, String email, String department, String position, boolean promotion) {
        this.name = name;
        this.employeeId = employeeId;
        this.email = email;
        this.department = department;
        this.position = position;
        this.promotion = promotion;
    }

    // Default constructor
    public Employee() {
        this.name = "";
        this.employeeId = 0;
        this.dateOfBirth = LocalDate.now(); // Default to the current date

        this.email = "";

        this.department = "";
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }



    public String getEmail() {
        return email;
    }



    public String getDepartment() {
        return department;
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

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public void setDepartment(String department) {
        this.department = department;
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



    // Abstract method to calculate salary (to be implemented by subclasses)
    public abstract double calculateSalary();

    // Method to print employee details
    public void printEmployee() {
        System.out.println("Name: " + name);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Email: " + email);
        System.out.println("Department: " + department);
        System.out.println("Position: " + position);
        System.out.println("Promotion: " + promotion);
    }
}
