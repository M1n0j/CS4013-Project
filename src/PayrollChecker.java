import java.time.LocalDate;
import java.time.DayOfWeek;
public class PayrollChecker {
    public void checker(String[] args) {
        LocalDate today = LocalDate.now();
        if (isPayrollDay(today)) {
            System.out.println("Today is payday!");
        } else {
            System.out.println("Today is not payday!");
        }
        if (isSecondFriday(today)) {
            System.out.println("Part-time staff pay claim is due today");
        } else {
            System.out.println("Today is not the second friday of the month.");
        }
    }
    public static boolean isPayrollDay(LocalDate date) {
        return date.getDayOfMonth() == 25;
    }
    public static boolean isSecondFriday(LocalDate date) {
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        int daysUntilFriday = DayOfWeek.FRIDAY.getValue() - firstDayOfMonth.getDayOfWeek().getValue();
        if (daysUntilFriday < 0) {
            daysUntilFriday += 7;
        }
        LocalDate firstFriday = firstDayOfMonth.plusDays(daysUntilFriday);
        LocalDate secondFriday = firstFriday.plusDays(7);
        return date.equals(secondFriday);
    }
}