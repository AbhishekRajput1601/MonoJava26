package com.abhi.interfaceLearning.assignment.asg1;

public class UPIPayment implements Payment{

    @Override
    public void processPayment(double amount) {
        System.out.println("This in payment method of UPI Payment processing Rs. :" + amount);
    }
}
