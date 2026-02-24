package com.abhi.abstractclass.assignment.asg1;

public class CreditCardPayment extends Payment {

    public CreditCardPayment(double amount) {
        super(amount);
    }

    @Override
    public void processPayment() {

        double fee = amount * 0.02;
        double finalAmount = amount + fee;

        System.out.println("Processing Credit Card Payment...");
        System.out.println("Processing Fee (2%): " + fee);

        generateReceipt(amount, finalAmount);
    }
}