package com.abhi.interfaceLearning.assignment.asg1;

class DebitCardPayment implements Payment {

    @Override
    public void processPayment(double amount) {
        System.out.println("This is payment method of Debit card processing Rs. :" + amount);
    }
}
