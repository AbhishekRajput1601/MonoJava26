package com.abhi.encapsulation.module;

public class BankAccount {

    private static int accountCounter = 1000;   // Simple auto-increment

    private final int accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountHolderName) {

        if (accountHolderName == null || accountHolderName.isBlank()) {
            System.out.println("Account holder name cannot be empty.");
            accountHolderName = "Unknown";
        }

        this.accountNumber = ++accountCounter;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }

        balance += amount;
        System.out.println("Deposit successful!");
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }

        balance -= amount;
        System.out.println("Withdrawal successful!");
    }

    private void applyInterest(double rate) {

        if (rate <= 0) {
            System.out.println("Interest rate must be positive.");
            return;
        }

        balance += balance * rate;
    }
}
