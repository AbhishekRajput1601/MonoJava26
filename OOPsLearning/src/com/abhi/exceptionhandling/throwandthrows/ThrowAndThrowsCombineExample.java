package com.abhi.exceptionhandling.throwandthrows;

import java.util.Scanner;

public class ThrowAndThrowsCombineExample {

    static double balance = 5000;

    static void withdraw(double amount) throws IllegalArgumentException {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        balance -= amount;
        System.out.println("Withdrawal successful.");
        System.out.println("Remaining Balance: " + balance);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            try {

                System.out.println("\n1. Withdraw");
                System.out.println("2. Check Balance");
                System.out.println("3. Exit");
                System.out.print("Choose option: ");

                if (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Invalid input. Enter numbers only.");
                    continue;
                }

                int choice = scanner.nextInt();

                switch (choice) {

                    case 1:
                        System.out.print("Enter amount: ");

                        if (!scanner.hasNextDouble()) {
                            scanner.next();
                            System.out.println("Invalid amount.");
                            continue;
                        }

                        double amount = scanner.nextDouble();
                        withdraw(amount);
                        break;

                    case 2:
                        System.out.println("Current Balance: " + balance);
                        break;

                    case 3:
                        System.out.println("Exiting... Thank you.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}