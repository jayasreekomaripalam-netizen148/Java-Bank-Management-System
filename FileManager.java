import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static void saveCustomers(ArrayList<Customer> customers,
                                     String fileName) {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(fileName));

            out.writeObject(customers);
            out.close();

            System.out.println("Customers saved successfully.");

        } catch (Exception e) {

            System.out.println("Error while saving.");
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Customer> loadCustomers(String fileName) {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream(fileName));

            ArrayList<Customer> list =
                    (ArrayList<Customer>) in.readObject();

            in.close();

            return list;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }
}