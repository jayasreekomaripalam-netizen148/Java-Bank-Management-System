import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;


    public static void send(
            Customer customer,
            String message) {


        System.out.println();

        System.out.println(
        "========== NOTIFICATION ==========");


        System.out.println(
        "Customer : " +
        customer.getName());


        System.out.println(
        "Message : " +
        message);


        System.out.println(
        "Time : " +
        LocalDateTime.now()
        .format(
        DateTimeFormatter.ofPattern(
        "dd-MM-yyyy HH:mm:ss")));


        System.out.println(
        "=================================");
    }
}