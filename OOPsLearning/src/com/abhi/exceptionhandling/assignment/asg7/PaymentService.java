package com.abhi.exceptionhandling.assignment.asg7;

import java.util.Scanner;

public class PaymentService {

    public void processPayment() {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter payment reference (UPI, Credit Card, Debit Card): ");
            String paymentReference = scanner.nextLine();

            if (paymentReference.isEmpty()) {
                throw new NullPointerException("Payment reference is null.");
            }

            System.out.println("Payment processed successfully.");

        } catch (NullPointerException e) {
            throw new RuntimeException("Payment processing failed.", e);
        }
    }
}