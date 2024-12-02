
import java.time.LocalDate;
import java.util.Scanner;

public class WorkClaim {
    private LocalDate date;
    private double hoursWorked;
    private String workDescription;
    private boolean approved; //Either yes or no (True or False)

    public WorkClaim(LocalDate date, double hoursWorked, String workDescription){
        this.date = date;
        this.hoursWorked = hoursWorked;
        this.workDescription = workDescription;
        this.approved = false;
    }

    //Getter and Setters
    public double getHoursWorked(){
        return hoursWorked;
    }

    public void approve(){
        this.approved = true;
    }

    public double submitPayClaim(double hourlyPay) {
        LocalDate currentDate = LocalDate.now();
        LocalDate submittionDate = currentDate;

        if(currentDate.getDayOfMonth() > 15) {
            submittionDate = currentDate.plusMonths(1);
            System.out.println("You are submitting a claim for next month");
        }else {
            System.out.println("You are submitting a claim for the current month");
        }

        Scanner pcInput = new Scanner(System.in);
        System.out.print("Enter hours worked this month: ");
        double hoursWorked = pcInput.nextDouble();
        this.hoursWorked = hoursWorked;

        double grossSalary = hoursWorked * hourlyPay;

        return grossSalary;
    }

}

