import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read year and month from the user

        int year = scanner.nextInt();

        int month = scanner.nextInt();

        // Create a YearMonth object with the given year and month
        YearMonth yearMonth = YearMonth.of(year, month);

        // Get the first day of the month
        LocalDate firstDayOfMonth = yearMonth.atDay(1);

        // Get the last day of the month
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        // Define a date-time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Print the first and last day of the month
        System.out.println(firstDayOfMonth.format(formatter) + " " + lastDayOfMonth.format(formatter));


        scanner.close();



        }





}