package com.abhi.accessmodifiers;

class BankAccount {

    private final int accountNumber;
    private String holderName;
    private double balance;


    BankAccount() {
        accountNumber = 0;
        holderName = "Unknown";
        balance = 0.0;
    }


    BankAccount(int accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }


    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }


    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    public void display() {
        System.out.println("Account No: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: " + balance);
    }
}

public class GetterSetterDemo {
    public static void main(String[] args) {


        BankAccount acc = new BankAccount(101, "Rahul", 5000);

        acc.deposit(2000);
        acc.withdraw(1000);

        acc.display();


        System.out.println("Current Balance: " + acc.getBalance());
    }
}

