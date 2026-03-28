package com.abhi.interfaceLearning.assignment.asg1;

import java.util.Scanner;

public class PaymentApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double amount = 0;

        while (true) {
            System.out.print("Enter Amount: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                scanner.nextLine();
                if (amount > 0) break;
                else System.out.println("Amount must be greater than 0.");
            } else {
                System.out.println("Invalid input! Please enter numeric value.");
                scanner.nextLine();
            }
        }

        Payment payment = null;

        while (true) {
            System.out.println("\nChoose Payment Method:");
            System.out.println("1. Credit Card");
            System.out.println("2. Debit Card");
            System.out.println("3. UPI");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter numeric value.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    payment = new CreditCardPayment();
                    break;
                case 2:
                    payment = new DebitCardPayment();
                    break;
                case 3:
                    payment = new UPIPayment();
                    break;
                case 4:
                    System.out.println("Exiting Payment System...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Choose 1 to 4.");
                    continue;
            }

            payment.processPayment(amount);
        }
    }
}