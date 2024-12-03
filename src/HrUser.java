import java.util.List;
import java.util.Scanner;

public class HrUser extends User {


    public HrUser(String employeeID, String password) {
        super(employeeID, password);
    }

    public void promoteEmployee(Scanner inputScanner) {
        if (inputScanner == null) {
            System.out.println("Invalid input scanner.");
            return;
        }


        List<Employee> employees = csvReader.readEmployees("src/Resources/Employees.csv");

        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\nEmployees:");
        for (Employee employee : employees) {
            System.out.println("ID: " + employee.getEmployeeId() + ", Name: " + employee.getName());
        }


        System.out.print("Enter employee ID to promote: ");
        int employeeID = inputScanner.nextInt();
        inputScanner.nextLine();

        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeID) {
                employee.setPromotion(true);
                int newLevel = employee.getLevel() + 1;
                employee.setLevel(newLevel);
                csvWriter.writeEmployees(employees, "src/resources/employees.csv");
                System.out.println("Employee promoted.");
                return;
            }
        }

        System.out.println("Employee not found.");
    }
}