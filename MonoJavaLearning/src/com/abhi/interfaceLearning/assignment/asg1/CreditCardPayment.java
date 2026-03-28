package com.abhi.interfaceLearning.assignment.asg1;

public class CreditCardPayment implements Payment{

    @Override
    public void processPayment(double amount) {
        System.out.println("This is payment method of Credit card processing Rs. :" + amount);
    }
}
