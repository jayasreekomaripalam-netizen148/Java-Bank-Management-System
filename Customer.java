import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountNumber;
    private String name;
    private String phone;
    private String address;
    private double todayWithdrawal = 0;

private String withdrawalDate = "";
    private int failedLoginAttempts = 0;
private String lastLoginTime = "Never";
    private String pin;
    private boolean accountFrozen = false;
private UPI upi;
    private Account account;
    private String accountType;
    // ATM Security
    private boolean locked = false;

    // ATM Card Details
    private ATMCard atmCard;


    // Fixed Deposit
    private FixedDeposit fixedDeposit;

    // Loan
    private Loan loan;



    public Customer(String accountNumber,
                    String name,
                    String phone,
                    String address,
                    String pin,
                    String accountType,
                    double balance) {


        this.accountNumber = accountNumber;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.pin = pin;
        this.accountType = accountType;

        this.account = new Account(balance);

        this.atmCard = new ATMCard(pin);
    }



    public String getAccountNumber() {
        return accountNumber;
    }


    public String getName() {
        return name;
    }


    public String getPhone() {
        return phone;
    }


    public String getAddress() {
        return address;
    }


    public String getPin() {
        return pin;
    }


    public String getAccountType() {
        return accountType;
    }


    public Account getAccount() {
        return account;
    }



    // ATM CARD

    public ATMCard getATMCard() {

        return atmCard;
    }



    // PIN

    public void setPin(String pin) {

        this.pin = pin;
    }



    // ATM Lock

    public boolean isLocked(){

        return locked;
    }


    public void setLocked(boolean value){

        locked = value;
    }

    // FD

    public void setFixedDeposit(FixedDeposit fd){

        this.fixedDeposit = fd;
    }


    public FixedDeposit getFixedDeposit(){

        return fixedDeposit;
    }

    // LOAN

    public void setLoan(Loan loan){

        this.loan = loan;
    }


    public Loan getLoan(){

        return loan;
    }
    public void display(){


        System.out.println("----------------------------");

        System.out.println("Account Number : "
                + accountNumber);

        System.out.println("Name           : "
                + name);

        System.out.println("Phone          : "
                + phone);

        System.out.println("Address        : "
                + address);

        System.out.println("Account Type   : "
                + accountType);

        System.out.println("Balance        : ₹"
                + account.getBalance());

        System.out.println("ATM Status     : "
                +(locked ? "Blocked":"Active"));


        System.out.println("----------------------------");

    }
    @Override
    public String toString(){

        return accountNumber +
                " | " +
                name +
                " | " +
                accountType +
                " | ₹" +
                account.getBalance();
    }
    public UPI getUPI() {
    return upi;
}

public void setUPI(UPI upi) {
    this.upi = upi;
}
public boolean isAccountFrozen() {
    return accountFrozen;
}


public void freezeAccount() {
    accountFrozen = true;
}


public void unfreezeAccount() {
    accountFrozen = false;
}
public int getFailedLoginAttempts() {

    return failedLoginAttempts;
}


public void increaseFailedLoginAttempts() {

    failedLoginAttempts++;
}


public void resetFailedLoginAttempts() {

    failedLoginAttempts = 0;
}


public String getLastLoginTime() {

    return lastLoginTime;
}


public void updateLastLoginTime() {

    lastLoginTime =
    java.time.LocalDateTime.now().toString();
}
public boolean canWithdraw(double amount) {

    String today =
    java.time.LocalDate.now().toString();


    if(!withdrawalDate.equals(today)) {

        withdrawalDate = today;
        todayWithdrawal = 0;
    }


    return (todayWithdrawal + amount) <= 20000;
}



public void addWithdrawal(double amount) {

    todayWithdrawal += amount;
}



public double getTodayWithdrawal() {

    return todayWithdrawal;
}
public void setPhone(String phone) {

    this.phone = phone;
}


public void setAddress(String address) {

    this.address = address;
}


public String getWithdrawalDate() {

    return withdrawalDate;
}
}