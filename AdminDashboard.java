public class AdminDashboard {

    public static void show(Bank bank) {

        int customers = bank.getCustomers().size();

        double totalBalance = 0;
        int activeCards = 0;
        int blockedCards = 0;
        int loans = 0;
        int fd = 0;


        for(Customer c : bank.getCustomers()) {


            totalBalance += 
            c.getAccount().getBalance();


            if(c.getATMCard() != null) {

                if(c.getATMCard().isBlocked()) {
                    blockedCards++;
                } else {
                    activeCards++;
                }
            }


            if(c.getLoan() != null) {
                loans++;
            }


            if(c.getFixedDeposit() != null) {
                fd++;
            }
        }



        System.out.println("\n========== ADMIN DASHBOARD ==========");

        System.out.println(
        "Total Customers : " + customers);

        System.out.println(
        "Total Bank Balance : ₹" + totalBalance);

        System.out.println(
        "Active ATM Cards : " + activeCards);

        System.out.println(
        "Blocked ATM Cards : " + blockedCards);

        System.out.println(
        "Total Loans : " + loans);

        System.out.println(
        "Total Fixed Deposits : " + fd);


        System.out.println(
        "====================================");
    }
}