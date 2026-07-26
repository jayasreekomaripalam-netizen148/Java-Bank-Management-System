import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(double balance) {
        this.balance = balance;
        this.transactions = new ArrayList<>();
        addTransaction("Account Opened", balance);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction("Deposit", amount);
    }

    public boolean withdraw(double amount) {

        if (amount > balance) {
            return false;
        }

        balance -= amount;
        addTransaction("Withdraw", amount);
        return true;
    }

    public boolean transfer(Account receiver, double amount) {

        if (amount > balance) {
            return false;
        }

        balance -= amount;
        receiver.balance += amount;

        addTransaction("Transfer Sent", amount);
        receiver.addTransaction("Transfer Received", amount);

        return true;
    }

    public void addTransaction(String type, double amount) {
        transactions.add(new Transaction(type, amount, balance));
    }

    public void showTransactions() {

        System.out.println("\n========== TRANSACTION HISTORY ==========");

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public void miniStatement() {

        System.out.println("\n========== MINI STATEMENT ==========");

        int start = Math.max(0, transactions.size() - 5);

        for (int i = start; i < transactions.size(); i++) {
            System.out.println(transactions.get(i));
        }

        System.out.println("------------------------------------");
        System.out.println("Current Balance : ₹" + balance);
    }
    public boolean atmWithdraw(double amount) {

    if (amount <= 0 || amount > balance) {
        return false;
    }

    if (withdraw(amount)) {
        return true;
    }

    return false;
}
}
