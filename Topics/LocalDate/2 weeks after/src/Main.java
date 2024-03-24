import java.time.LocalDate;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        LocalDate date = LocalDate.parse(text);
        System.out.println(date.plusWeeks(2));
    }
}