import java.util.Scanner;

public class ATMSystem {


    public void atmLogin(Bank bank, Scanner sc) {


        System.out.println("\n===== ATM MACHINE =====");


        System.out.print("Enter ATM Card Number: ");

        String card =
        sc.nextLine();



        Customer customer = null;



        for(Customer c : bank.getCustomers()) {


            if(c.getATMCard() != null &&
            c.getATMCard()
            .getCardNumber()
            .equals(card)) {


                customer = c;

                break;
            }
        }



        if(customer == null) {


            System.out.println(
            "Invalid ATM Card");


            return;
        }



        if(customer.getATMCard()
        .isBlocked()) {


            System.out.println(
            "ATM Card is Blocked");


            return;
        }



        int attempts = 3;



        while(attempts > 0) {


            System.out.print(
            "Enter PIN: ");


            String pin =
            sc.nextLine();



            if(customer.getATMCard()
            .checkPIN(pin)) {

Notification.send(
    customer,
    "ATM Login Successful"
);
             
                atmMenu(customer,sc);


                return;
            }



            attempts--;

customer.increaseFailedLoginAttempts();

System.out.println("Wrong PIN");

System.out.println(
"Failed Attempts: " +
customer.getFailedLoginAttempts());


if(customer.getFailedLoginAttempts() >= 3) {

    System.out.println(
    "Account Locked Due to Multiple Wrong PIN Attempts");

    customer.getATMCard().blockCard();

    bank.saveData();

    return;
}


            System.out.println(
            "Attempts Remaining: "
            + attempts);



            if(attempts == 0) {


                System.out.println(
                "ATM Card Blocked");


                customer.getATMCard()
                .blockCard();


                bank.saveData();
            }
        }
    }





    private void atmMenu(Customer customer,
                         Scanner sc) {


        while(true) {


            System.out.println("\n========== ATM MENU ==========");

System.out.println("1. Withdraw Cash");
System.out.println("2. Cash Deposit");
System.out.println("3. Fast Cash");
System.out.println("4. Check Balance");
System.out.println("5. Mini Statement");
System.out.println("6. Change PIN");
System.out.println("7. View ATM Card");
System.out.println("8. Exit");
     System.out.print(
            "Choice: ");



            int ch =
            Integer.parseInt(sc.nextLine());



            switch(ch) {


            case 1:

    System.out.print(
    "Enter Amount: ");


    double amount =
    Double.parseDouble(
    sc.nextLine());


    if(customer.getAccount()
    .withdraw(amount)) {


        Receipt.printReceipt(
            customer,
            "Cash Withdrawal",
            amount
        );


        Notification.send(
            customer,
            "Cash Withdrawal of ₹" + amount + " Successful"
        );


        System.out.println(
        "Balance: ₹" +
        customer.getAccount()
        .getBalance());


    } else {


        System.out.println(
        "Insufficient Balance");
    }


    break;
 case 2:

    System.out.print(
    "Enter Deposit Amount: ");


    double deposit =
    Double.parseDouble(
    sc.nextLine());


    if(deposit <= 0) {

        System.out.println(
        "Invalid Amount");

        break;
    }


    customer.getAccount()
    .deposit(deposit);



    Receipt.printReceipt(
        customer,
        "Cash Deposit",
        deposit
    );


    Notification.send(
        customer,
        "Cash Deposit of ₹" 
        + deposit 
        + " Successful"
    );


    System.out.println(
    "Deposit Successful");


    System.out.println(
    "Balance: ₹" +
    customer.getAccount()
    .getBalance());


    break;
        
    case 3:

    System.out.println("\nFAST CASH");
    System.out.println("1. ₹500");
    System.out.println("2. ₹1000");
    System.out.println("3. ₹2000");
    System.out.println("4. ₹5000");

    System.out.print("Choose: ");

    int fc = Integer.parseInt(sc.nextLine());

    double fastAmount = 0;

    switch(fc) {

        case 1:
            amount = 500;
            break;

        case 2:
            amount = 1000;
            break;

        case 3:
            amount = 2000;
            break;

        case 4:
            amount = 5000;
            break;

        default:
            System.out.println("Invalid Choice");
            continue;
    }

    if(customer.getAccount().atmWithdraw(amount)) {

    System.out.println("Please Collect Your Cash");
    System.out.println("Balance : ₹" +
            customer.getAccount().getBalance());

    Receipt.printReceipt(
        customer,
        "ATM Cash Withdrawal",
        amount
    );

} else {

    System.out.println("Transaction Failed");
}

break;

           case 4:

    System.out.println("Balance : ₹" +
            customer.getAccount().getBalance());

    break;



                case 5:

    customer.getAccount().miniStatement();

    break;
case 6:

    System.out.print("Enter New PIN: ");

    String newPin = sc.nextLine();

    customer.getATMCard().changePIN(newPin);

    System.out.println("PIN Changed Successfully");

    break;

case 7:

    customer.getATMCard().displayCard();

    break;
    case 8:

    return;

                default:

                    System.out.println(
                    "Invalid Choice");
            }
        }
    }
}