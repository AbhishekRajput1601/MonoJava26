package com.abhi.abstractclass.assignment.asg1;

public class WalletPayment extends Payment {

    public WalletPayment(double amount) {
        super(amount);
    }

    @Override
    public void processPayment() {

        double fee = amount * 0.01;
        double finalAmount = amount + fee;

        System.out.println("Processing Wallet Payment...");
        System.out.println("Processing Fee (1%): " + fee);

        generateReceipt(amount, finalAmount);
    }
}