import java.util.*;

public class EmployeeCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load employees and salary scales
        List<Employee> employees = EmployeeDataHandler.readEmployees();
        List<SalaryScale> salaryScales = SalaryScaleDataHandler.readSalaryScales();

        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. List Employees");
            System.out.println("2. Add New Employee");
            System.out.println("3. Check Employee Salary");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    // List all employees
                    if (employees.isEmpty()) {
                        System.out.println("No employees found.");
                    } else {
                        for (Employee employee : employees) {
                            System.out.println(employee);
                        }
                    }
                    break;

                case 2:
                    // Add a new employee
                    Employee newEmployee = createEmployee(scanner);
                    employees.add(newEmployee);
                    EmployeeDataHandler.writeEmployees(employees);
                    System.out.println("Employee added successfully.");
                    break;

                case 3:
                    // Check employee salary
                    System.out.print("Enter Employee ID: ");
                    int empId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Employee emp = findEmployeeById(employees, empId);
                    if (emp == null) {
                        System.out.println("Employee not found.");
                    } else {
                        double salary = calculateEmployeeSalary(emp, salaryScales);
                        System.out.println("Employee Details: " + emp);
                        System.out.println("Salary: " + salary);
                    }
                    break;

                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Create a new employee by taking input from the user.
     */
    private static Employee createEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Role: ");
        String role = scanner.nextLine();

        System.out.print("Enter Salary Scale: ");
        String salaryScale = scanner.nextLine();

        System.out.print("Enter Current Point: ");
        String currentPoint = scanner.nextLine(); // Fixed: Take input as String

        System.out.print("Is Full Time (true/false): ");
        boolean isFullTime = scanner.nextBoolean();

        System.out.print("Enter Years at Top Point: ");
        int yearsAtTopPoint = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return new Employee(employeeId, name, email, role, salaryScale, currentPoint, isFullTime, yearsAtTopPoint);
    }

    /**
     * Find an employee by ID.
     */
    private static Employee findEmployeeById(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == id) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Calculate the salary of an employee based on salary scales.
     */
    private static double calculateEmployeeSalary(Employee employee, List<SalaryScale> salaryScales) {
        for (SalaryScale scale : salaryScales) {
            if (scale.getPositionName().equalsIgnoreCase(employee.getRole()) &&
                    scale.getLevel().equalsIgnoreCase(employee.getCurrentPoint())) {
                return scale.getSalary();
            }
        }
        System.out.println("Salary scale not found for the employee.");
        return 0; // Return 0 if no matching salary scale is found
    }
}
