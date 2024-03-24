import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
      
        Scanner scanner = new Scanner(System.in);
        String year = "";
        String month = "";
        String remaining = "";
        while (scanner.hasNext()) {
            year = scanner.next();
            month = scanner.next();
            remaining = scanner.next();
        }


        YearMonth yearMonth = YearMonth.of(Integer.parseInt(year), Integer.parseInt(month));


        int lastDayOfMonth = yearMonth.atEndOfMonth().getDayOfMonth();


        int nthDay = lastDayOfMonth - Integer.parseInt(remaining) + 1;


        LocalDate nthDayDate = yearMonth.atDay(nthDay);


        System.out.println(nthDayDate.toString());

    }
}