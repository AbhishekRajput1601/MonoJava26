package solid.assignment.lsp;

public class BankApplication {
    public static void main(String[] args) {

        SavingsAccount sa = new SavingsAccount();
        FixedDepositAccount fd = new FixedDepositAccount();

        System.out.println("\n==================================");
        System.out.println("       BANKING SYSTEM");
        System.out.println("==================================");

        while (true) {
            System.out.println("\nChoose Operation:");
            System.out.println("1. Deposit in Savings");
            System.out.println("2. Withdraw from Savings");
            System.out.println("3. Deposit in Fixed Deposit");
            System.out.println("4. Exit");

            int choice = (int) InputValidator.getAmount("\nEnter choice: ");

            switch (choice) {
                case 1:
                    double dep = InputValidator.getAmount("Enter deposit amount: ");
                    sa.deposit(dep);
                    break;

                case 2:
                    double wd = InputValidator.getAmount("Enter withdrawal amount: ");
                    sa.withdraw(wd);
                    break;

                case 3:
                    double fdAmt = InputValidator.getAmount("Enter FD amount: ");
                    fd.deposit(fdAmt);
                    break;

                case 4:
                    System.out.println("\n Exiting Banking System...");
                    return;

                default:
                    System.out.println(" Invalid choice!");
            }
        }
    }
}
