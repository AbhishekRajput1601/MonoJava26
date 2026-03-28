package com.abhi.arrays;

import java.util.Scanner;

public class ExampleOfTwoDArray {

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

    private static int readPositiveInteger(Scanner sc, String message) {
        int value;
        do {
            value = readInteger(sc, message);
            if (value <= 0) {
                System.out.println("Value must be greater than 0.");
            }
        } while (value <= 0);
        return value;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int m = readPositiveInteger(sc, "Enter rows : ");
        int n = readPositiveInteger(sc, "Enter columns : ");

        int[][] arr = new int[m][n];

        System.out.println("Enter elements:");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = readInteger(sc, "arr[" + i + "][" + j + "] = ");
            }
        }

        System.out.println("Matrix:");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}
