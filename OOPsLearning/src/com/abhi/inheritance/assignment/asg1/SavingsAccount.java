package com.abhi.inheritance.assignment.asg1;

public class SavingsAccount extends Account {

    private double minimumBalance;

    public SavingsAccount(String holderName, double balance, double minimumBalance) {
        super(holderName, balance);
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < minimumBalance) {
            System.out.println("Cannot Withdraw. Minimum Balance Required: " + minimumBalance);
            return;
        }
        super.withdraw(amount);
    }
}

