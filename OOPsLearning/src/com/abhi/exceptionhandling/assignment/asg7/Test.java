package com.abhi.exceptionhandling.assignment.asg7;

public class Test {
    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService();

        try {
            paymentService.processPayment();
        } catch (RuntimeException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("Cause: " + e.getCause());
        }
    }
}
