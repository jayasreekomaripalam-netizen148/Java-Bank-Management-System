import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utils {

    private static final DecimalFormat df = new DecimalFormat("#0.00");

    // Read integer safely
    public static int readInt(Scanner sc, String message) {

        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid integer. Try again.");
            }
        }
    }

    // Read double safely
    public static double readDouble(Scanner sc, String message) {

        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    // Format currency
    public static String formatMoney(double amount) {
        return "£" + df.format(amount);
    }

    // Current Date & Time
    public static String currentDateTime() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return LocalDateTime.now().format(formatter);
    }

    // Divider
    public static void line() {
        System.out.println("--------------------------------------------");
    }

    // Title
    public static void title(String text) {
        line();
        System.out.println(text.toUpperCase());
        line();
    }
}