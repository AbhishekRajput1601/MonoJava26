package com.java.org.example;

public class Bank {
    private double balance;

    public Bank() {
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("deposit amount must be greater than zero");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("withdraw amount must be greater than zero");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("insufficient balance");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

