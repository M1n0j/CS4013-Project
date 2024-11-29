import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HrUser extends User {

    public HrUser(String employeeID, String password) {
        super(employeeID, password);
    }



    private void promoteEmployee(Scanner inputScanner) {

        List<Employee> employees = CSVReader.loadEmployees("Employees.csv");
        System.out.println("Select the employee ID of the employee you wish to promote:");
        int employeeID = inputScanner.nextInt();
        inputScanner.nextLine();

        Employee employeeToPromote = null;

        for(Employee employee : employees) {
            if(employee.getEmployeeId() == employeeID) {
                employeeToPromote = employee;
                break;
            }
        }

        if(employeeToPromote == null) {
            System.out.println("Employee ID not found");
            return;
        }

        employeeToPromote.setPromotion(true);
        CSVReader.writeEmployees(employees, "Employees.csv");

        System.out.println("Employee " + employeeID + ", awaiting employee approval");
    }


}

