package com.abhi.arrays.assignment;

import java.util.*;

public class InsuranceRiskPortfolioAnalyzer {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Enter number of customers: ");
        int n = getValidPositiveInt();

        int[] ages = new int[n];
        int[] riskScores = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Customer " + (i + 1));

            System.out.print("Age: ");
            ages[i] = getValidNonNegativeInt();

            System.out.print("Risk Score (0–100): ");
            riskScores[i] = getValidIntInRange(0, 100);
        }

        int highRiskYouth = 0, seniorRisk = 0, veryHighRisk = 0, normalRisk = 0;
        int highestRiskIndex = 0;
        double totalRisk = 0;

        for (int i = 0; i < n; i++) {
            String category;

            if (ages[i] < 25 && riskScores[i] > 70) {
                category = "High Risk Youth";
                highRiskYouth++;
            } else if (ages[i] >= 60) {
                category = "Senior Risk";
                seniorRisk++;
            } else if (riskScores[i] >= 85) {
                category = "Very High Risk";
                veryHighRisk++;
            } else {
                category = "Normal Risk";
                normalRisk++;
            }

            if (riskScores[i] > riskScores[highestRiskIndex]) {
                highestRiskIndex = i;
            }

            totalRisk += riskScores[i];
            System.out.println("Customer " + i + " → " + category);
        }

        System.out.println("--- Summary ---");
        System.out.println("High Risk Youth: " + highRiskYouth);
        System.out.println("Senior Risk: " + seniorRisk);
        System.out.println("Very High Risk: " + veryHighRisk);
        System.out.println("Normal Risk: " + normalRisk);
        System.out.println("Average Risk Score: " + (totalRisk / n));
        System.out.println("Highest Risk Customer Index: " + highestRiskIndex);
    }

    private static int getValidPositiveInt() {
        while (true) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value > 0) return value;
            } else {
                scanner.next();
            }
            System.out.print("Enter a positive integer: ");
        }
    }

    private static int getValidNonNegativeInt() {
        while (true) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value >= 0) return value;
            } else {
                scanner.next();
            }
            System.out.print("Enter a non-negative integer: ");
        }
    }

    private static int getValidIntInRange(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value >= min && value <= max) return value;
            } else {
                scanner.next();
            }
            System.out.print("Enter value between " + min + " and " + max + ": ");
        }
    }
}

