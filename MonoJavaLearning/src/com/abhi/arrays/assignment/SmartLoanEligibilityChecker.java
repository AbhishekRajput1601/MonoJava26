package com.abhi.arrays.assignment;

import java.util.*;

public class SmartLoanEligibilityChecker {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Enter number of applicants: ");
        int n = getValidPositiveInt();

        int approvals = 0;
        int rejections = 0;
        int bestApplicantIndex = -1;
        int bestCreditScore = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("\nApplicant " + (i + 1));

            System.out.print("Credit Score: ");
            int creditScore = getValidNonNegativeInt();

            System.out.print("Monthly Income: ");
            int income = getValidNonNegativeInt();

            System.out.print("Existing Loans: ");
            int loans = getValidNonNegativeInt();

            if (creditScore < 600 || income < 25000 || loans >= 3) {
                System.out.println("Result → Rejected");
                rejections++;
            } else {
                approvals++;

                if (creditScore >= 800 && income > 100000) {
                    System.out.println("Result → Instant Approval");
                } else {
                    System.out.println("Result → Standard Review");
                }

                if (creditScore > bestCreditScore) {
                    bestCreditScore = creditScore;
                    bestApplicantIndex = i;
                }
            }
        }

        System.out.println("Total Approvals: " + approvals);
        System.out.println("Total Rejections: " + rejections);
        System.out.println("Best Applicant Index: " + bestApplicantIndex);
    }

    private static int getValidPositiveInt() {
        int value;

        while (true) {

            if (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Please enter a valid integer: ");
                scanner.next();
                continue;
            }

            value = scanner.nextInt();

            if (value <= 0) {
                System.out.print("Number must be greater than 0. Please try again: ");
                continue;
            }

            return value;
        }
    }

    private static int getValidNonNegativeInt() {
        int value;

        while (true) {

            if (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Please enter a valid integer: ");
                scanner.next();
                continue;
            }

            value = scanner.nextInt();

            if (value < 0) {
                System.out.print("Number cannot be negative. Please try again: ");
                continue;
            }

            return value;
        }
    }

}

