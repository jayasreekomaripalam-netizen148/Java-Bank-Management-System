import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

public class ATMCard implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cardNumber;
    private String issueDate;
    private boolean blocked;

    private String pin;

    private int wrongAttempts;


    public ATMCard(String pin) {

        Random random = new Random();


        cardNumber =
        "4587" +
        (100000000 + random.nextInt(900000000));


        issueDate =
        LocalDate.now().toString();


        this.pin = pin;


        blocked = false;

        wrongAttempts = 0;
    }



    public String getCardNumber() {

        return cardNumber;
    }



    public String getIssueDate() {

        return issueDate;
    }



    public boolean isBlocked() {

        return blocked;
    }



    public boolean checkPIN(String enteredPin) {


        if(blocked)
            return false;



        if(pin.equals(enteredPin)) {

            wrongAttempts = 0;

            return true;
        }


        wrongAttempts++;


        if(wrongAttempts >= 3) {

            blocked = true;
        }


        return false;
    }




    public void changePIN(String newPin) {

        pin = newPin;

        wrongAttempts = 0;
    }



    public void blockCard() {

        blocked = true;
    }



    public void unblockCard() {

        blocked = false;

        wrongAttempts = 0;
    }



    public int getWrongAttempts() {

        return wrongAttempts;
    }



    public void displayCard() {


        System.out.println("----------------------------");

        System.out.println(
        "ATM Card Number : " + cardNumber);


        System.out.println(
        "Issue Date      : " + issueDate);


        System.out.println(
        "Status          : " +
        (blocked ? "Blocked" : "Active"));


        System.out.println("----------------------------");
    }
}