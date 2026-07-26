import java.io.Serializable;
import java.time.LocalDateTime;

public class BankStatement implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void generate(Customer customer) {

        System.out.println("\n========== BANK STATEMENT ==========");

        System.out.println("Date : " + LocalDateTime.now());

        System.out.println("Account Number : "
                + customer.getAccountNumber());

        System.out.println("Name : "
                + customer.getName());

        System.out.println("Account Type : "
                + customer.getAccountType());

        System.out.println("Balance : ₹"
                + customer.getAccount().getBalance());


        System.out.println("\n----- TRANSACTIONS -----");

        customer.getAccount()
                .showTransactions();


        System.out.println("====================================");
    }
}