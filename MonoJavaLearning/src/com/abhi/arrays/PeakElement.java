package com.abhi.arrays;

import java.util.Scanner;

public class PeakElement {

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

        System.out.println("Enter elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = readInteger(sc, "arr[" + i + "] = ");
        }

        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            boolean left = (i == 0) || (arr[i] > arr[i - 1]);
            boolean right = (i == arr.length - 1) || (arr[i] > arr[i + 1]);

            if (left && right) {
                System.out.println("Peak Element: " + arr[i] + " at index " + i);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No peak element found.");
        }

        sc.close();
    }
}