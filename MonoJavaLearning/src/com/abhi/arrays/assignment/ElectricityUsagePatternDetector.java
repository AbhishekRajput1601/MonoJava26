package com.abhi.arrays.assignment;

import java.util.*;

public class ElectricityUsagePatternDetector {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int DAYS = 30;

    public static void main(String[] args) {

        int[] usage = new int[DAYS];
        int highCount = 0;
        boolean overload = false;
        double total = 0;

        System.out.println("Enter electricity usage for 30 days:");

        for (int i = 0; i < DAYS; i++) {
            System.out.print("Day " + (i + 1) + ": ");
            usage[i] = getValidNonNegativeInt();
        }

        for (int i = 0; i < DAYS; i++) {

            if (usage[i] > 500) {
                System.out.println("Day " + (i + 1) + " → High Consumption");
                highCount++;
            } else if (usage[i] < 100) {
                System.out.println("Day " + (i + 1) + " → Low Usage Alert");
            }

            if (i >= 2 && usage[i] > 500 &&
                    usage[i - 1] > 500 &&
                    usage[i - 2] > 500) {
                overload = true;
            }

            total += usage[i];
        }

        double average = total / DAYS;

        if (overload) {
            System.out.println("Overload Warning Detected!");
        }

        if (average > 400) {
            System.out.println("Heavy Month");
        }

        System.out.println("Monthly Average: " + average);
        System.out.println("High Consumption Days: " + highCount);
    }

    private static int getValidNonNegativeInt() {
        int value;

        while (true) {

            if (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Please enter a valid integer: ");
                scanner.next(); // clear wrong input
                continue;
            }

            value = scanner.nextInt();

            if (value < 0) {
                System.out.print("Usage cannot be negative. Please try again: ");
                continue;
            }

            return value;
        }
    }

}

