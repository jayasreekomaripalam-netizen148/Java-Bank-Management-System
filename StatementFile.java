import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class StatementFile {

    public static void save(Customer customer) {

        String fileName =
        "Statement_" + customer.getAccountNumber() + ".txt";

        try {

            FileWriter writer =
            new FileWriter(fileName);


            writer.write("========== BANK STATEMENT ==========\n\n");

            writer.write("Date : "
                    + LocalDateTime.now()
                    + "\n\n");

            writer.write("Account Number : "
                    + customer.getAccountNumber()
                    + "\n");

            writer.write("Name : "
                    + customer.getName()
                    + "\n");

            writer.write("Account Type : "
                    + customer.getAccountType()
                    + "\n");

            writer.write("Balance : ₹"
                    + customer.getAccount().getBalance()
                    + "\n\n");


            writer.write("====== TRANSACTIONS ======\n");


            writer.close();


            System.out.println(
            "Statement Saved Successfully");

            System.out.println(
            "File: " + fileName);


        } catch(IOException e) {

            System.out.println(
            "File Error: " + e.getMessage());
        }
    }
}