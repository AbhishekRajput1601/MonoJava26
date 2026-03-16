package com.abhi.comparableandcomparator.asg5;

public class Transaction {

    int id;
    double amount;

    Transaction(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public String toString() {
        return "ID: " + id + " Amount: " + amount;
    }
}
