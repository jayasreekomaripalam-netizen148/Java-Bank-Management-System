import java.io.Serializable;
import java.time.LocalDate;

public class FixedDeposit implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fdNumber;
    private double amount;
    private double interestRate;
    private int years;
    private String date;


    public FixedDeposit(String fdNumber,
                        double amount,
                        double interestRate,
                        int years) {

        this.fdNumber = fdNumber;
        this.amount = amount;
        this.interestRate = interestRate;
        this.years = years;
        this.date = LocalDate.now().toString();
    }


    public String getFdNumber() {
        return fdNumber;
    }


    public double getAmount() {
        return amount;
    }


    // Compound Interest Maturity Calculation
    public double calculateMaturity() {

        return amount *
                Math.pow(
                (1 + interestRate / 100),
                years);
    }


    public void displayFD() {

        System.out.println("----------------------------");
        System.out.println("FD Number       : " + fdNumber);
        System.out.println("Amount          : ₹" + amount);
        System.out.println("Interest Rate   : " + interestRate + "%");
        System.out.println("Duration        : " + years + " years");
        System.out.println("Created Date    : " + date);
        System.out.println("Maturity Amount : ₹" +
                calculateMaturity());
        System.out.println("----------------------------");
    }
}