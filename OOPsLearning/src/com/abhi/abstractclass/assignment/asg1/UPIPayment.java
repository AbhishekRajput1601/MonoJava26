package com.abhi.abstractclass.assignment.asg1;

public class UPIPayment extends Payment{

    public UPIPayment(double amount) {
        super(amount);
    }

    @Override
    public void processPayment() {
        System.out.println("Processing UPI Payment...");
        generateReceipt(amount, amount);
    }
}
