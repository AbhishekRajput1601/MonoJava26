package com.abhi.abstractclass.assignment.asg1;

public abstract class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract void processPayment();

    public void generateReceipt(double originalAmount, double finalAmount){
        System.out.println("----- Payment Receipt -----");
        System.out.println("Original Amount: " + originalAmount);
        System.out.println("Final Amount Paid: " + finalAmount);
        System.out.println("---------------------------");
    }
}
