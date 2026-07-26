import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static Bank bank = new Bank();

    static ATMSystem atmSystem = new ATMSystem();


    public static void main(String[] args) {

        bank.loadData();


        while(true) {

            System.out.println("\n=================================");
            System.out.println("     BANK MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Customer Registration");
            System.out.println("2. Customer Login");
            System.out.println("3. Admin Login");
            System.out.println("4. ATM Login");
            System.out.println("5. Save Data");
            System.out.println("6. Exit");


            System.out.print("Enter choice: ");

            int choice =
            Integer.parseInt(sc.nextLine());


            switch(choice) {


                case 1:

                    bank.registerCustomer(sc);

                    break;



                case 2:

                    customerMenu();

                    break;



                case 3:

                    adminMenu();

                    break;



                case 4:

                    atmSystem.atmLogin(bank, sc);

                    break;



                case 5:

                    bank.saveData();

                    break;



                case 6:

                    bank.saveData();

                    System.out.println(
                    "Thank you for using our Bank.");

                    System.exit(0);



                default:

                    System.out.println(
                    "Invalid Choice");
            }
        }
    }



    static void customerMenu() {


        Customer customer =
        bank.customerLogin(sc);



        if(customer == null)

            return;



        while(true) {


            System.out.println(
            "\n========== CUSTOMER MENU ==========");


            System.out.println(
            "Welcome " + customer.getName());

System.out.println("1. Deposit");
System.out.println("2. Withdraw");
System.out.println("3. Transfer Money");
System.out.println("4. Check Balance");
System.out.println("5. Change PIN");
System.out.println("6. Transaction History");
System.out.println("7. Mini Statement");
System.out.println("8. Compound Interest");
System.out.println("9. Create Fixed Deposit");
System.out.println("10. View Fixed Deposit");
System.out.println("11. Apply Loan");
System.out.println("12. View Loan");
System.out.println("13. Pay EMI");
System.out.println("14. UPI Transfer");
System.out.println("15. Change UPI ID");
System.out.println("16. View UPI History");
System.out.println("17. Change UPI PIN");
System.out.println("18. Generate Bank Statement");
System.out.println("19. Export Statement");
System.out.println("20. Logout");

            int ch =
            Integer.parseInt(sc.nextLine());


            switch(ch) {


                case 1:

                    bank.deposit(customer,sc);

                    break;


                case 2:

                    bank.withdraw(customer,sc);

                    break;


                case 3:

                    bank.transfer(customer,sc);

                    break;


                case 4:

                    System.out.println(
                    "Balance : ₹" +
                    customer.getAccount()
                    .getBalance());

                    break;


                case 5:

                    bank.changePin(customer,sc);

                    break;


                case 6:

                    customer.getAccount()
                    .showTransactions();

                    break;case 7:

                    customer.getAccount()
                    .miniStatement();

                    break;


                case 8:

                    bank.calculateInterest(customer,sc);

                    break;


                case 9:

                    bank.createFD(customer,sc);

                    bank.saveData();

                    break;


                case 10:

                    bank.viewFD(customer);

                    break;


                case 11:

                    bank.applyLoan(customer,sc);

                    bank.saveData();

                    break;


                case 12:

                    bank.viewLoan(customer);

                    break;


               case 13:
    bank.payLoanEMI(customer);
    break;

case 14:
    bank.upiTransfer(customer, sc);
    break;
case 15:
    bank.changeUPI(customer, sc);
    break;
case 16:

    if (customer.getUPI() != null) {
        customer.getUPI().showHistory();
    } else {
        System.out.println("UPI is not activated.");
    }

    break;

case 17:

    if(customer.getUPI() != null) {

        System.out.print("Enter Old UPI PIN: ");
        String oldPin = sc.nextLine();

        System.out.print("Enter New UPI PIN: ");
        String newPin = sc.nextLine();

        customer.getUPI()
                .changeUpiPin(oldPin, newPin);

        bank.saveData();

    } else {

        System.out.println("UPI Not Activated");
    }

    break;


case 18:

    BankStatement.generate(customer);

    break;


case 19:

    StatementFile.save(customer);

    break;


case 20:

    return;
default:
    System.out.println("Invalid Choice");
            }
        }
    }



    static void adminMenu() {


        if(!bank.adminLogin(sc))

            return;



        while(true) {


            System.out.println(
            "\n========== ADMIN MENU ==========");


            System.out.println("1. View Customers");
            System.out.println("2. Search Customer");
            System.out.println("3. Delete Customer");
            System.out.println("4. Total Customers");
            System.out.println("5. Save Data");
            System.out.println("6. Block ATM Card");
            System.out.println("7. Unblock ATM Card");
            System.out.println("8. Dashboard");
System.out.println("8. Dashboard");
System.out.println("9. Search Transaction");
System.out.println("10. Logout");


            System.out.print("Choice: ");


            int ch =
            Integer.parseInt(sc.nextLine());



            switch(ch) {


                case 1:

                    bank.listCustomers();

                    break;



                case 2:

                    bank.searchCustomer(sc);

                    break;



                case 3:

                    bank.deleteCustomer(sc);

                    break;



                case 4:

                    System.out.println(
                    "Total Customers : "
                    + bank.totalCustomers());

                    break;



                case 5:

                    bank.saveData();

                    break;



                case 6:

                    blockATMCard();

                    break;



                case 7:

                    unblockATMCard();

                    break;
case 8:

    AdminDashboard.show(bank);

    break;


case 9:

    bank.searchTransaction(sc);

    break;


case 10:

    freezeAccount();

    break;


case 11:

    unfreezeAccount();

    break;


case 12:

    return;

                default:

                    System.out.println(
                    "Invalid Choice");
            }
        }
    }



    static void blockATMCard() {


        System.out.print(
        "Enter Account Number: ");


        String acc =
        sc.nextLine();



        Customer customer =
        bank.findCustomer(acc);



        if(customer == null) {


            System.out.println(
            "Customer Not Found");

            return;
        }



        if(customer.getATMCard()!=null) {


            customer.getATMCard()
            .blockCard();


            bank.saveData();


            System.out.println(
            "ATM Card Blocked Successfully");


        } else {


            System.out.println(
            "ATM Card Not Available");
        }
    }
    static void unblockATMCard() {


        System.out.print(
        "Enter Account Number: ");


        String acc =
        sc.nextLine();



        Customer customer =
        bank.findCustomer(acc);



        if(customer == null) {


            System.out.println(
            "Customer Not Found");


            return;
        }



        if(customer.getATMCard()!=null) {


            customer.getATMCard()
            .unblockCard();



            bank.saveData();



            System.out.println(
            "ATM Card Unblocked Successfully");


        } else {


            System.out.println(
            "ATM Card Not Available");
        }
    }
static void freezeAccount() {

    System.out.print("Enter Account Number: ");

    String acc = sc.nextLine();

    Customer customer = bank.findCustomer(acc);


    if(customer == null) {

        System.out.println("Customer Not Found");
        return;
    }


    customer.freezeAccount();

    bank.saveData();

    System.out.println("Account Frozen Successfully");
}



static void unfreezeAccount() {

    System.out.print("Enter Account Number: ");

    String acc = sc.nextLine();

    Customer customer = bank.findCustomer(acc);


    if(customer == null) {

        System.out.println("Customer Not Found");
        return;
    }


    customer.unfreezeAccount();

    bank.saveData();

    System.out.println("Account Unfrozen Successfully");
}
}