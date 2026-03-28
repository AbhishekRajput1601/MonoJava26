package com.abhi.arrays;

import java.util.Scanner;

public class MaxConsecutiveOnes {

    private static int readInteger(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                System.out.println("Invalid input! Please enter an integer.");
                sc.next();
            }
        }
    }

    private static int readBinary(Scanner sc, String message) {
        int value;
        do {
            value = readInteger(sc, message);
            if (value != 0 && value != 1) {
                System.out.println("Only 0 or 1 allowed.");
            }
        } while (value != 0 && value != 1);
        return value;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int size;
        do {
            size = readInteger(sc, "Enter array size (>0): ");
            if (size <= 0) {
                System.out.println("Size must be greater than 0.");
            }
        } while (size <= 0);

        int[] arr = new int[size];

        System.out.println("Enter elements (only 0 or 1):");
        for (int i = 0; i < size; i++) {
            arr[i] = readBinary(sc, "arr[" + i + "] = ");
        }

        int count = 0, max = 0;

        for (int num : arr) {
            if (num == 1) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }

        System.out.println("Maximum consecutive 1s: " + max);

        sc.close();
    }
}
