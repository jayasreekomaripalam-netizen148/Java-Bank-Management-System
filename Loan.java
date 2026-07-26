import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;

    private String loanId;
    private double loanAmount;
    private double remainingAmount;
    private double interestRate;
    private int tenure;
    private double emi;
    private int paidEMI;
    private int totalEMI;
    private String status;
    private String date;


    public Loan(String loanId,
                double loanAmount,
                double interestRate,
                int tenure) {


        this.loanId = loanId;
        this.loanAmount = loanAmount;

        // Remaining principal amount
        this.remainingAmount = loanAmount;

        this.interestRate = interestRate;
        this.tenure = tenure;

        this.totalEMI = tenure * 12;

        this.emi = calculateEMI();

        this.paidEMI = 0;

        this.status = "Approved";

        this.date = LocalDate.now().toString();

    }



    // Compound EMI Calculation
    public double calculateEMI() {


        double monthlyRate =
                interestRate / 12 / 100;


        int months = tenure * 12;


        if(monthlyRate == 0) {
            return loanAmount / months;
        }


        return (loanAmount *
                monthlyRate *
                Math.pow(1 + monthlyRate, months))
                /
                (Math.pow(1 + monthlyRate, months) - 1);

    }




    // Pay EMI
    public void payEMI() {


        if(status.equals("Completed")) {

            System.out.println("Loan already completed.");
            return;
        }


        paidEMI++;


        remainingAmount -= emi;


        if(paidEMI >= totalEMI ||
                remainingAmount <= 0) {


            remainingAmount = 0;

            status = "Completed";


            System.out.println(
                "Loan Completed Successfully!"
            );

        }


        System.out.println(
            "EMI Paid Successfully."
        );


        System.out.println(
            "EMI Number : "
            + paidEMI
            + "/"
            + totalEMI
        );


        System.out.println(
            "Remaining Loan Amount: ₹"
            + remainingAmount
        );

    }




    public void displayLoan() {


        System.out.println("----------------------------");

        System.out.println(
            "Loan ID        : "
            + loanId
        );


        System.out.println(
            "Loan Amount    : ₹"
            + loanAmount
        );


        System.out.println(
            "Interest Rate  : "
            + interestRate
            + "%"
        );


        System.out.println(
            "Tenure         : "
            + tenure
            + " Years"
        );


        System.out.println(
            "Monthly EMI    : ₹"
            + String.format("%.2f", emi)
        );


        System.out.println(
            "EMI Paid       : "
            + paidEMI
            + "/"
            + totalEMI
        );


        System.out.println(
            "Remaining      : ₹"
            + String.format("%.2f", remainingAmount)
        );


        System.out.println(
            "Status         : "
            + status
        );


        System.out.println(
            "Date           : "
            + date
        );


        System.out.println("----------------------------");

    }




    public boolean isCompleted() {

        return status.equals("Completed");

    }


    public double getRemainingAmount() {

        return remainingAmount;

    }


    public double getEMI() {

        return emi;

    }

}