package com.java.org.example;

public class TestNaming {
    private double balance;

    public TestNaming(double initialBalance) {
        this.balance = initialBalance;
    }

    public double deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
        balance += amount;
        return balance;
    }

    public double getBalance() {
        return balance;
    }
}

