package com.abhi.encapsulation.constructorAssignment.asg1;

public class BankAccount {

    private final String accountNumber;
    private final String accountHolderName;
    private double balance;

    private static double interestRate;

    static {
        interestRate = 0.05;
    }

    public BankAccount(String accountNumber, String accountHolderName) {
        this(accountNumber, accountHolderName, 0.0);
    }


    public BankAccount(String accountNumber, String accountHolderName, double balance) {

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public static double getInterestRate() {
        return interestRate;
    }
}
