public class ATM {

    private int attempts = 3;

    public boolean checkPIN(Customer customer, String enteredPin) {

        if (customer.isLocked()) {

            System.out.println("Account is locked!");
            return false;
        }


        if (customer.getPin().equals(enteredPin)) {

            attempts = 3;
            return true;

        } else {

            attempts--;

            System.out.println("Wrong PIN!");
            System.out.println("Attempts Remaining: " + attempts);


            if (attempts == 0) {

                customer.setLocked(true);

                System.out.println("Account Locked!");
            }

            return false;
        }
    }


    public void resetAttempts() {
        attempts = 3;
    }
}