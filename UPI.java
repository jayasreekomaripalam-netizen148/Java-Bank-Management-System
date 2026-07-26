import java.io.Serializable;
import java.util.ArrayList;
public class UPI implements Serializable {

    private static final long serialVersionUID = 1L;
private ArrayList<String> history = new ArrayList<>();
    private String upiId;
private String upiPin;
   public UPI(String upiId) {
    this.upiId = upiId;
    this.upiPin = "1234";
    this.history = new ArrayList<>();
}

public String getUpiId() {
    return upiId;
}

public void setUpiId(String upiId) {
    this.upiId = upiId;
}
public String getUpiPin() {
    return upiPin;
}

public void setUpiPin(String upiPin) {
    this.upiPin = upiPin;
}

public boolean verifyPin(String pin) {
    return upiPin.equals(pin);
}

    @Override
    public String toString() {
        return upiId;
    }
    public void addHistory(String message) {
    history.add(message);
}

public void showHistory() {

    System.out.println("\n===== UPI PAYMENT HISTORY =====");

    if (history.isEmpty()) {
        System.out.println("No UPI transactions found.");
        return;
    }

    for (String s : history) {
        System.out.println(s);
    }
}
public void changeUpiPin(String oldPin, String newPin) {

    if (upiPin.equals(oldPin)) {

        upiPin = newPin;

        System.out.println("UPI PIN Changed Successfully");

    } else {

        System.out.println("Incorrect Old UPI PIN");
    }
}
}