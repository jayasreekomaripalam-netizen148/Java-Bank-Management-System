import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type;
    private double amount;
    private double balance;
    private String transactionId;
    private String dateTime;

    public Transaction(String type, double amount, double balance) {

        this.type = type;
        this.amount = amount;
        this.balance = balance;
transactionId = "TXN" + System.currentTimeMillis();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.dateTime = LocalDateTime.now().format(formatter);
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {

        return transactionId + " | " +
       type + " | ₹" + amount +
       " | Balance: ₹" + balance +
       " | " + dateTime;
    }
}