package com.abhi.arrays.assignment;

import java.util.Scanner;

public class FraudTransactionDetector {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Enter number of transactions: ");
        int numberOfTransactions = getValidPositiveInt();

        double[] transactions = new double[numberOfTransactions];
        int[] suspiciousIndices = new int[numberOfTransactions];
        int suspiciousCount = 0;

        double totalAmount = 0;
        boolean potentialFraud = false;

        for (int i = 0; i < numberOfTransactions; i++) {

            System.out.print("Enter amount for Transaction " + (i + 1) + ": ");
            transactions[i] = getValidNonNegativeDouble();


            if (transactions[i] > 50000) {
                suspiciousIndices[suspiciousCount] = i + 1;
                suspiciousCount++;
                System.out.println("Transaction " + (i + 1) + " → Suspicious");
            }

            if (i > 0 &&
                    transactions[i] > 50000 &&
                    transactions[i - 1] > 50000) {
                potentialFraud = true;
            }

            totalAmount += transactions[i];
        }

        double average = totalAmount / numberOfTransactions;

        System.out.println("\n===== Summary =====");

        if (potentialFraud) {
            System.out.println("⚠ Potential Fraud Detected!");
        }

        if (average > 40000) {
            System.out.println("High Value Account");
        }

        if (suspiciousCount == 0) {
            System.out.println("No suspicious transactions found.");
        } else {
            System.out.print("Suspicious Transaction Indices: ");
            for (int i = 0; i < suspiciousCount; i++) {
                System.out.print(suspiciousIndices[i] + " ");
            }
            System.out.println();
        }

        System.out.printf("Average Transaction Value: %.2f%n", average);
    }


    private static int getValidPositiveInt() {
        int value;

        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Enter a valid integer: ");
                scanner.next();
                continue;
            }

            value = scanner.nextInt();

            if (value <= 0) {
                System.out.print("Number must be greater than 0. Try again: ");
                continue;
            }

            return value;
        }
    }

    private static double getValidNonNegativeDouble() {
        double value;

        while (true) {
            if (!scanner.hasNextDouble()) {
                System.out.print("Invalid input! Enter a valid amount: ");
                scanner.next();
                continue;
            }

            value = scanner.nextDouble();

            if (value < 0) {
                System.out.print("Transaction amount cannot be negative. Try again: ");
                continue;
            }

            return value;
        }
    }
}
