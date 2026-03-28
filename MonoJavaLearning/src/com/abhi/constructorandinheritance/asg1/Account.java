package com.abhi.constructorandinheritance.asg1;

class Account {

    private int accountNumber;
    private String accountHolderName;
    private double accountBalance;

    public Account() {
        this(0, "Unknown", 0.0);
    }

    public Account(int accountNumber, String accountHolderName, double accountBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;

        if (accountBalance >= 0) {
            this.accountBalance = accountBalance;
        } else {
            this.accountBalance = 0;
        }
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void deposit(double depositAmount) {
        if (depositAmount > 0) {
            accountBalance += depositAmount;
        }
    }

    public void withdraw(double withdrawAmount) {
        if (withdrawAmount > 0 && withdrawAmount <= accountBalance) {
            accountBalance -= withdrawAmount;
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public void displayDetails() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Balance        : " + accountBalance);
    }
}