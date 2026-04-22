package com.java.org.example;

public class ExceptionExample {
    public double withdraw(double balance, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Amount exceeds balance");
        }
        return balance - amount;
    }
}

