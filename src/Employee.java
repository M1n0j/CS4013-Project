import java.time.LocalDate;

public abstract class Employee {
    private String name;
    private int employeeId;
    private LocalDate dateOfBirth;
    private String address;
    private String email;
    private String phoneNumber;
    private String department;
    private String position;

    // Constructor with all fields
    public Employee(String name, int employeeId, LocalDate dateOfBirth,
                    String address, String email, String phoneNumber,
                    String department, String position) {
        this.name = name;
        this.employeeId = employeeId;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.position = position;
    }

    // Default constructor
    public Employee() {
        this.name = "";
        this.employeeId = 0;
        this.dateOfBirth = LocalDate.now(); // Default to the current date
        this.address = "";
        this.email = "";
        this.phoneNumber = "";
        this.department = "";
        this.position = "";
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

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // Abstract method to calculate salary (to be implemented by subclasses)
    public abstract double calculateSalary();

    // Method to print employee details
    public void printEmployee() {
        System.out.println("Name: " + name);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Department: " + department);
        System.out.println("Position: " + position);
    }
}
