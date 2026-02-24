package com.abhi.inheritance.assignment.asg1;
public class Account {

    private static int nextAccountNumber = 1000;

    protected int accountNumber;
    protected String holderName;
    protected double balance;

    public Account(String holderName, double balance) {
        this.accountNumber = nextAccountNumber++;
        this.holderName = holderName;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance = balance + amount;
        System.out.println("Deposit Successful");
        displayDetails();
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance = balance - amount;
            System.out.println("Withdrawal Successful");
            displayDetails();
            return;
        }
        System.out.println("Insufficient Balance");
    }

    public void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: " + balance);
    }
}
