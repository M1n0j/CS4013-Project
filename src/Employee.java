
public abstract class Employee {
    private String name;
    private int employeeId;
    private String email;
    private String position;
    private int level;
    private boolean promotion;
    public boolean fullTime;
    protected CSVReader csvReader;
    protected CSVWriter csvWriter;


    public Employee(int employeeId, String name, String email, String position, int level, boolean promotion, boolean fullTime) {
        this.name = name;
        this.employeeId = employeeId;
        this.email = email;
        this.position = position;
        this.level = level;
        this.promotion = promotion;
        this.fullTime = fullTime;
    }

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

    public boolean isPromotion() {
        return promotion;
    }

    public boolean isFullTime() {
        return fullTime;
    }

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

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }

    public abstract double calculateSalary();

    public abstract String toCSV();

    public abstract double calculateSalaryForCurrentPeriod();
}

