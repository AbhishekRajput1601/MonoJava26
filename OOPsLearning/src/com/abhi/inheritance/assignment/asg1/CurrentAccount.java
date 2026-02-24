package com.abhi.inheritance.assignment.asg1;

public class CurrentAccount extends Account {

    private double overdraftLimit;

    public CurrentAccount(String holderName, double balance, double overdraftLimit) {
        super(holderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < -overdraftLimit) {
            System.out.println("Overdraft Limit Exceeded");
            return;
        }
        balance = balance - amount;
        System.out.println("Withdrawal Successful");
        displayDetails();
    }
}

