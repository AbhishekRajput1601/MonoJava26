package com.abhi.constructorandinheritance.asg1;

class CurrentAccount extends Account {

    private double overdraftLimit;

    public CurrentAccount() {
        this(0, "Unknown", 0.0, 0.0);
    }

    public CurrentAccount(int accountNumber, String accountHolderName,
                          double accountBalance, double overdraftLimit) {

        super(accountNumber, accountHolderName, accountBalance);
        this.overdraftLimit = overdraftLimit;
    }

    public void checkOverdraft(double withdrawAmount) {

        if (withdrawAmount <= getAccountBalance() + overdraftLimit) {
            withdraw(withdrawAmount);
        } else {
            System.out.println("Overdraft limit exceeded.");
        }
    }

    @Override
    public void displayDetails() {

        System.out.println("\n--- Current Account ---");
        super.displayDetails();
        System.out.println("Overdraft Limit : " + overdraftLimit);
    }
}
