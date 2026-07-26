import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {

    public static void printReceipt(Customer customer,
                                    String transaction,
                                    double amount) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("         BANK RECEIPT");
        System.out.println("=================================");
        System.out.println("Date : " +
            LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        System.out.println("Account : " +
            customer.getAccountNumber());

        System.out.println("Name : " +
            customer.getName());

        System.out.println("Transaction : " +
            transaction);

        System.out.println("Amount : ₹" +
            amount);

        System.out.println("Balance : ₹" +
            customer.getAccount().getBalance());

        System.out.println("=================================");
        System.out.println("     THANK YOU - VISIT AGAIN");
        System.out.println("=================================");
    }
}