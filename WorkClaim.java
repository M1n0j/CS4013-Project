
import java.time.LocalDate;

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

}

