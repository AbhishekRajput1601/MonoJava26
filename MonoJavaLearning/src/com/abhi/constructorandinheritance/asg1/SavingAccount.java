package com.abhi.constructorandinheritance.asg1;

class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount() {
        this(0, "Unknown", 0.0, 0.0);
    }

    public SavingsAccount(int accountNumber, String accountHolderName,
                          double accountBalance, double interestRate) {

        super(accountNumber, accountHolderName, accountBalance);
        this.interestRate = interestRate;
    }

    public void calculateInterest() {

        double interestAmount = getAccountBalance() * interestRate / 100;
        deposit(interestAmount);
    }

    @Override
    public void displayDetails() {

        System.out.println("\n--- Savings Account ---");
        super.displayDetails();
        System.out.println("Interest Rate  : " + interestRate + "%");
    }
}
