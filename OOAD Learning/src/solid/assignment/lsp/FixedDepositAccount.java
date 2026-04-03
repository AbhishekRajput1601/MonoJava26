package solid.assignment.lsp;

public class FixedDepositAccount implements DepositAccount {
    private double balance;

    public void deposit(double amount) {
        balance += amount;
        System.out.println("FD Deposit: " + amount);
    }
}
