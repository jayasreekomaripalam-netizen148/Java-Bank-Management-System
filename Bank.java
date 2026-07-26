import java.util.*;
import java.io.*;

public class Bank{

    private ArrayList<Customer> customers;
    private final String FILE_NAME = "bank.dat";

    private String ADMIN_USER = "admin";
    private String ADMIN_PASS = "admin123";

    public Bank() {
        customers = new ArrayList<>();
    }

  // Customer Registration with Account Type
public void registerCustomer(Scanner sc) {

    System.out.println("\n===== CUSTOMER REGISTRATION =====");

    System.out.print("Enter Name: ");
    String name = sc.nextLine();

    System.out.print("Enter Phone: ");
    String phone = sc.nextLine();

    System.out.print("Enter Address: ");
    String address = sc.nextLine();

    System.out.println("\nSelect Account Type:");
    System.out.println("1. Savings Account");
    System.out.println("2. Current Account");
    System.out.print("Choice: ");

    int typeChoice = Integer.parseInt(sc.nextLine());

    String accountType;

    if (typeChoice == 1) {
        accountType = "Savings";
    } 
    else if (typeChoice == 2) {
        accountType = "Current";
    } 
    else {
        System.out.println("Invalid Account Type");
        return;
    }


    System.out.print("Create 4-digit PIN: ");
    String pin = sc.nextLine();


    System.out.print("Initial Deposit: ");
    double amount = Double.parseDouble(sc.nextLine());


    String accNo = generateAccountNumber();


    Customer customer = new Customer(
            accNo,
            name,
            phone,
            address,
            pin,
            accountType,
            amount
    );


    customers.add(customer);


    System.out.println("\nRegistration Successful");
    System.out.println("Account Number: " + accNo);
    System.out.println("Account Type: " + accountType);
}

    // Generate Account Number
    private String generateAccountNumber() {

        Random random = new Random();

        while (true) {

            String acc = "10" + (100000 + random.nextInt(900000));

            boolean exists = false;

            for (Customer c : customers) {
                if (c.getAccountNumber().equals(acc)) {
                    exists = true;
                    break;
                }
            }

            if (!exists)
                return acc;
        }
    }
// Customer Login with ATM PIN Security
public Customer customerLogin(Scanner sc) {

    System.out.println("\n===== CUSTOMER LOGIN =====");

    System.out.print("Account Number: ");
    String acc = sc.nextLine();


    Customer customer = null;

    for (Customer c : customers) {

        if (c.getAccountNumber().equals(acc)) {
            customer = c;
            break;
        }
    }


    if (customer == null) {

        System.out.println("Account Not Found");
        return null;
    }


    ATM atm = new ATM();


    for (int i = 0; i < 3; i++) {

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();


        if (atm.checkPIN(customer, pin)) {

            System.out.println("Login Successful");
            return customer;

        }
    }


    System.out.println("Login Failed");
    return null;
}

    // Admin Login
    public boolean adminLogin(Scanner sc) {

        System.out.println("\n===== ADMIN LOGIN =====");

        System.out.print("Username: ");
        String user = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (ADMIN_USER.equals(user)
                && ADMIN_PASS.equals(pass)) {

            System.out.println("Admin Login Successful");
            return true;
        }

        System.out.println("Invalid Admin Credentials");
        return false;
    }
//deposit
    public void deposit(Customer customer, Scanner sc) {
if(customer.isAccountFrozen()) {

    System.out.println("Account is Frozen. Transaction not allowed.");

    return;
}
    System.out.print("Deposit Amount: ");
    double amount = Double.parseDouble(sc.nextLine());

    if(amount <= 0){
        System.out.println("Invalid Amount");
        return;
    }

    customer.getAccount().deposit(amount);

    saveData();
Receipt.printReceipt(
    customer,
    "Cash Deposit",
    amount
);
    System.out.println("New Balance: ₹" +
            customer.getAccount().getBalance());
}
    // Withdraw
    public void withdraw(Customer customer, Scanner sc){
if(customer.isAccountFrozen()) {

    System.out.println("Account is Frozen. Transaction not allowed.");

    return;
}
    System.out.print("Withdraw Amount: ");
    double amount = Double.parseDouble(sc.nextLine());


    if(amount <= 0){
        System.out.println("Invalid Amount");
        return;
    }


    boolean result =
        customer.getAccount().withdraw(amount);


    if(result){

        saveData();

        System.out.println("Withdrawal Successful");
        System.out.println("Balance: ₹" +
        customer.getAccount().getBalance());

    }
    else{

        System.out.println("Insufficient Balance");

    }

}
        // Money Transfer
    public void transfer(Customer sender, Scanner sc) {
if(sender.isAccountFrozen()) {

    System.out.println("Account is Frozen. Transaction not allowed.");

    return;
}
        System.out.print("Receiver Account Number: ");
        String accNo = sc.nextLine();

        Customer receiver = null;

        for (Customer c : customers) {
            if (c.getAccountNumber().equals(accNo)) {
                receiver = c;
                break;
            }
        }

        if (receiver == null) {
            System.out.println("Receiver account not found.");
            return;
        }

        if (receiver.getAccountNumber().equals(sender.getAccountNumber())) {
            System.out.println("You cannot transfer to your own account.");
            return;
        }

        System.out.print("Enter Amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        boolean success = sender.getAccount().transfer(receiver.getAccount(), amount);

        if (success) {
            System.out.println("Transfer Successful.");
            System.out.println("Current Balance: £" +
                    sender.getAccount().getBalance());
        } else {
            System.out.println("Insufficient Balance.");
        }
    }

    // Change PIN
    public void changePin(Customer customer, Scanner sc) {

        System.out.print("Enter Current PIN: ");
        String oldPin = sc.nextLine();

        if (!customer.getPin().equals(oldPin)) {
            System.out.println("Incorrect PIN.");
            return;
        }

        System.out.print("Enter New PIN: ");
        String newPin = sc.nextLine();

        if (newPin.length() != 4) {
            System.out.println("PIN must contain exactly 4 digits.");
            return;
        }

        customer.setPin(newPin);

        System.out.println("PIN Changed Successfully.");
    }

   // Compound Interest Calculator
public void calculateInterest(Customer customer, Scanner sc) {

    System.out.println("\n===== COMPOUND INTEREST CALCULATOR =====");

    double principal = customer.getAccount().getBalance();

    System.out.println("Current Balance: ₹" + principal);

    System.out.print("Annual Interest Rate (%): ");
    double rate = Double.parseDouble(sc.nextLine());


    System.out.print("Number of Years: ");
    int years = Integer.parseInt(sc.nextLine());


    System.out.println("\nCompounding Frequency:");
    System.out.println("1. Yearly");
    System.out.println("2. Monthly");

    System.out.print("Choice: ");
    int choice = Integer.parseInt(sc.nextLine());


    int frequency;


    if (choice == 1) {

        frequency = 1;

    } else if (choice == 2) {

        frequency = 12;

    } else {

        System.out.println("Invalid Choice");
        return;
    }


    // Compound Interest Formula
    double amount =
            principal *
            Math.pow(
                (1 + (rate / 100) / frequency),
                frequency * years
            );


    double interest = amount - principal;


    System.out.println("\n----------------------------");
    System.out.println("Principal Amount : ₹" + principal);
    System.out.println("Interest Rate    : " + rate + "%");
    System.out.println("Time Period      : " + years + " years");
    System.out.println("Interest Earned  : ₹" + interest);
    System.out.println("Final Amount     : ₹" + amount);
    System.out.println("----------------------------");
}

    // Search Customer
    public void searchCustomer(Scanner sc) {

        System.out.print("Enter Account Number: ");
        String acc = sc.nextLine();

        for (Customer c : customers) {

            if (c.getAccountNumber().equals(acc)) {
                c.display();
                return;
            }
        }

        System.out.println("Customer Not Found.");
    }

    // List All Customers
    public void listCustomers() {

        if (customers.isEmpty()) {
            System.out.println("No Customers Available.");
            return;
        }

        System.out.println("\n========== CUSTOMER LIST ==========");

        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    // Total Customers
    public int totalCustomers() {
        return customers.size();
    }
    // Delete Customer
    public void deleteCustomer(Scanner sc) {

        System.out.print("Enter Account Number to Delete: ");
        String acc = sc.nextLine();

        Iterator<Customer> iterator = customers.iterator();

        while (iterator.hasNext()) {

            Customer c = iterator.next();

            if (c.getAccountNumber().equals(acc)) {

                iterator.remove();

                System.out.println("Customer Deleted Successfully.");
                return;
            }
        }

        System.out.println("Customer Not Found.");
    }

    // Save Data
    public void saveData() {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE_NAME));

            out.writeObject(customers);
            out.close();

            System.out.println("Data Saved Successfully.");

        } catch (Exception e) {

            System.out.println("Error Saving Data.");
        }
    }

    // Load Data
    @SuppressWarnings("unchecked")
    public void loadData() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            customers = new ArrayList<>();
            return;
        }

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream(file));

            customers = (ArrayList<Customer>) in.readObject();

            in.close();

            System.out.println("Data Loaded Successfully.");

        } catch (Exception e) {

            customers = new ArrayList<>();

            System.out.println("Starting with Empty Database.");
        }
    }

    // Find Customer by Account Number
    public Customer findCustomer(String accountNumber) {

        for (Customer c : customers) {

            if (c.getAccountNumber().equals(accountNumber)) {
                return c;
            }
        }
        return null;
    }

    // Getter
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

// Create Fixed Deposit
public void createFD(Customer customer, Scanner sc){

    System.out.println("\n===== FIXED DEPOSIT =====");
    System.out.print("Enter FD Amount: ");
    double amount =
    Double.parseDouble(sc.nextLine());


    if(amount > customer.getAccount().getBalance()){

        System.out.println("Insufficient Balance");
        return;
    }


    System.out.print("Interest Rate (%): ");
    double rate =
    Double.parseDouble(sc.nextLine());


    System.out.print("Duration (Years): ");
    int years =
    Integer.parseInt(sc.nextLine());



    String fdNumber =
    "FD"+(1000+new Random().nextInt(9000));


    FixedDeposit fd =
    new FixedDeposit(
        fdNumber,
        amount,
        rate,
        years
    );


    customer.setFixedDeposit(fd);


    customer.getAccount()
    .withdraw(amount);


    saveData();


    System.out.println("FD Created Successfully");
    System.out.println("FD Number : "+fdNumber);
}
// View FD
public void viewFD(Customer customer) {

    if(customer.getFixedDeposit() == null) {

        System.out.println("No Fixed Deposit Found.");
        return;
    }


    customer.getFixedDeposit().displayFD();
}
// Apply Loan
public void applyLoan(Customer customer, Scanner sc) {

    System.out.println("\n===== LOAN APPLICATION =====");


    System.out.print("Enter Loan Amount: ");
    double amount = Double.parseDouble(sc.nextLine());


    if(amount <= 0) {
        System.out.println("Invalid Loan Amount");
        return;
    }


    System.out.print("Interest Rate (%): ");
    String rateInput = sc.nextLine();


    if(rateInput.isEmpty()) {
        System.out.println("Interest Rate cannot be empty");
        return;
    }


    double rate = Double.parseDouble(rateInput);



    System.out.print("Loan Duration (Years): ");
    int years = Integer.parseInt(sc.nextLine());


    if(years <= 0) {
        System.out.println("Invalid Duration");
        return;
    }



    String loanId =
            "LN" + (1000 + new Random().nextInt(9000));



    Loan loan =
            new Loan(
                    loanId,
                    amount,
                    rate,
                    years
            );


    customer.setLoan(loan);


    System.out.println("\nLoan Approved Successfully");
    System.out.println("Loan ID: " + loanId);
}
// View Loan
public void viewLoan(Customer customer) {

    if(customer.getLoan() == null) {

        System.out.println("No Loan Found.");
        return;
    }


    customer.getLoan().displayLoan();
}
// Pay Loan EMI
public void payLoanEMI(Customer customer) {


    if(customer.getLoan() == null) {

        System.out.println("No Loan Found.");
        return;
    }


    customer.getLoan().payEMI();

    saveData();

}
public void unlockCustomer(Scanner sc){

    System.out.print("Enter Account Number: ");

    String acc=sc.nextLine();


    Customer c=findCustomer(acc);


    if(c==null){

        System.out.println("Customer Not Found");
        return;
    }


    c.setLocked(false);


    saveData();


    System.out.println(
    "Account Unlocked Successfully");
}
// Total Bank Balance
public void totalBankBalance() {

    double total = 0;

    for(Customer c : customers) {

        total += c.getAccount().getBalance();
    }


    System.out.println("----------------------------");
    System.out.println("Total Bank Balance : ₹" + total);
    System.out.println("----------------------------");
}



// View All Transactions
public void viewAllTransactions() {


    if(customers.isEmpty()) {

        System.out.println("No Customers Found.");
        return;
    }


    System.out.println("\n====== ALL TRANSACTIONS ======");


    for(Customer c : customers) {


        System.out.println("\nCustomer : "
                + c.getName());

        System.out.println("Account : "
                + c.getAccountNumber());


        c.getAccount().showTransactions();

    }
}



// Block Customer Account
public void blockCustomer(Scanner sc) {


    System.out.print("Enter Account Number: ");

    String acc =
            sc.nextLine();



    Customer customer =
            findCustomer(acc);



    if(customer == null) {

        System.out.println("Customer Not Found.");
        return;
    }



    customer.setLocked(true);


    System.out.println(
        "Customer Account Blocked Successfully."
    );

}
// Change Admin Password
public void changeAdminPassword(Scanner sc) {

    System.out.println("\n===== CHANGE ADMIN PASSWORD =====");


    System.out.print("Enter Current Password: ");
    String oldPass = sc.nextLine();


    if(!ADMIN_PASS.equals(oldPass)) {

        System.out.println("Wrong Current Password");
        return;
    }


    System.out.print("Enter New Password: ");
    String newPass = sc.nextLine();


    if(newPass.length() < 5) {

        System.out.println("Password must contain minimum 5 characters");
        return;
    }


    ADMIN_PASS = newPass;


    System.out.println("Admin Password Changed Successfully");
}
public Customer findCustomerByUPI(String upiId) {

    for (Customer c : customers) {

        if (c.getUPI() != null &&
            c.getUPI().getUpiId().equalsIgnoreCase(upiId)) {

            return c;
        }
    }

    return null;
}
//upi transfer
public void upiTransfer(Customer sender, Scanner sc) {
if(sender.isAccountFrozen()) {

    System.out.println("Account is Frozen. UPI Transfer not allowed.");

    return;
}
    System.out.print("Enter Receiver UPI ID: ");
    String upiId = sc.nextLine();

    Customer receiver = findCustomerByUPI(upiId);

    if (receiver == null) {
        System.out.println("UPI ID Not Found.");
        return;
    }

    if (receiver == sender) {
        System.out.println("Cannot transfer to your own UPI.");
        return;
    }

    System.out.print("Enter Amount: ₹");
    double amount = Double.parseDouble(sc.nextLine());

    if (sender.getAccount().transfer(receiver.getAccount(), amount)) {
Receipt.printReceipt(
    sender,
    "UPI Transfer",
    amount
);

        sender.getAccount().addTransaction(
                "UPI Sent to " + receiver.getUPI().getUpiId(),
                amount);

        receiver.getAccount().addTransaction(
                "UPI Received from " + sender.getUPI().getUpiId(),
                amount);

        saveData();

    } else {

        System.out.println("Insufficient Balance.");
    }
}
public void changeUPI(Customer customer, Scanner sc) {

    System.out.print("Enter New UPI ID: ");
    String newUpi = sc.nextLine();

    for (Customer c : customers) {
        if (c.getUPI() != null &&
            c.getUPI().getUpiId().equalsIgnoreCase(newUpi)) {

            System.out.println("UPI ID already exists.");
            return;
        }
    }

    customer.setUPI(new UPI(newUpi));
    saveData();

    System.out.println("UPI ID updated successfully.");
}
public void searchTransaction(Scanner sc) {

    System.out.print("Enter Account Number: ");
    String acc = sc.nextLine();

    Customer customer = findCustomer(acc);

    if (customer == null) {
        System.out.println("Customer Not Found");
        return;
    }

    System.out.println("\nCustomer Name: "
            + customer.getName());

    System.out.println("Account Number: "
            + customer.getAccountNumber());

    System.out.println("\n===== TRANSACTION DETAILS =====");

    customer.getAccount().showTransactions();
}
public void viewCustomerSecurity(Scanner sc) {

    System.out.print("Enter Account Number: ");

    String acc =
    sc.nextLine();


    Customer customer = findCustomer(acc);


    if(customer == null) {

        System.out.println("Customer Not Found");

        return;
    }


    System.out.println("\n===== SECURITY DETAILS =====");


    System.out.println(
    "Name : " + customer.getName());


    System.out.println(
    "Account Number : " +
    customer.getAccountNumber());


    System.out.println(
    "ATM Card Status : " +
    (customer.getATMCard().isBlocked()
    ? "Blocked" : "Active"));


    System.out.println(
    "Failed Login Attempts : " +
    customer.getFailedLoginAttempts());


    System.out.println(
    "Last Login Time : " +
    customer.getLastLoginTime());


    System.out.println(
    "Account Status : " +
    (customer.isAccountFrozen()
    ? "Frozen" : "Active"));


    System.out.println(
    "============================");
}
public void viewProfile(Customer customer) {

    System.out.println("\n========== CUSTOMER PROFILE ==========");

    System.out.println("Account Number : "
            + customer.getAccountNumber());

    System.out.println("Name           : "
            + customer.getName());

    System.out.println("Phone          : "
            + customer.getPhone());

    System.out.println("Address        : "
            + customer.getAddress());

    System.out.println("Account Type   : "
            + customer.getAccountType());

    System.out.println("Balance        : ₹"
            + customer.getAccount().getBalance());

    System.out.println("UPI ID         : "
            + (customer.getUPI() != null
            ? customer.getUPI().getUpiId()
            : "Not Created"));

    System.out.println("ATM Status     : "
            + (customer.getATMCard().isBlocked()
            ? "Blocked"
            : "Active"));

    System.out.println("Account Status : "
            + (customer.isAccountFrozen()
            ? "Frozen"
            : "Active"));

    System.out.println("====================================");
}
public void updateProfile(Customer customer, Scanner sc) {

    System.out.println("\n===== UPDATE PROFILE =====");

    System.out.println("1. Change Phone");
    System.out.println("2. Change Address");

    System.out.print("Choice: ");

    int choice =
    Integer.parseInt(sc.nextLine());


    switch(choice) {

        case 1:

            System.out.print("Enter New Phone: ");

            String phone =
            sc.nextLine();

            customer.setPhone(phone);

            System.out.println(
            "Phone Updated Successfully");

            break;


        case 2:

            System.out.print("Enter New Address: ");

            String address =
            sc.nextLine();

            customer.setAddress(address);

            System.out.println(
            "Address Updated Successfully");

            break;


        default:

            System.out.println(
            "Invalid Choice");
    }


    saveData();
}

}
