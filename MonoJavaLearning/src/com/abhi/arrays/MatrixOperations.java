package com.abhi.arrays;

import java.util.Scanner;

public class MatrixOperations {

    private static int readInteger(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input! Please enter an integer.");
                scanner.next();
            }
        }
    }

    private static int readPositiveInteger(Scanner scanner, String message) {
        int value;
        do {
            value = readInteger(scanner, message);
            if (value <= 0) {
                System.out.println("Value must be greater than 0.");
            }
        } while (value <= 0);
        return value;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int rows = readPositiveInteger(scanner, "Enter number of rows (>0): ");
        int cols = readPositiveInteger(scanner, "Enter number of columns (>0): ");

        int[][] A = new int[rows][cols];
        int[][] B = new int[rows][cols];

        System.out.println("Enter elements of Matrix A:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                A[i][j] = readInteger(scanner, "A[" + i + "][" + j + "] = ");
            }
        }

        System.out.println("Enter elements of Matrix B:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                B[i][j] = readInteger(scanner, "B[" + i + "][" + j + "] = ");
            }
        }

        int choice;

        do {
            System.out.println("\nMENU");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Transpose of Matrix A");
            System.out.println("5. Transpose of Matrix B");
            System.out.println("6. Exit");

            choice = readInteger(scanner, "Enter your choice: ");

            switch (choice) {

                case 1:
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            System.out.print((A[i][j] + B[i][j]) + " ");
                        }
                        System.out.println();
                    }
                    break;

                case 2:
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            System.out.print((A[i][j] - B[i][j]) + " ");
                        }
                        System.out.println();
                    }
                    break;

                case 3:
                    if (cols != rows) {
                        System.out.println("Multiplication possible only for square matrices.");
                        break;
                    }

                    int[][] result = new int[rows][cols];

                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            result[i][j] = 0;
                            for (int k = 0; k < cols; k++) {
                                result[i][j] += A[i][k] * B[k][j];
                            }
                            System.out.print(result[i][j] + " ");
                        }
                        System.out.println();
                    }
                    break;

                case 4:
                    for (int i = 0; i < cols; i++) {
                        for (int j = 0; j < rows; j++) {
                            System.out.print(A[j][i] + " ");
                        }
                        System.out.println();
                    }
                    break;

                case 5:
                    for (int i = 0; i < cols; i++) {
                        for (int j = 0; j < rows; j++) {
                            System.out.print(B[j][i] + " ");
                        }
                        System.out.println();
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1-6.");
            }

        } while (choice != 6);

        scanner.close();
    }
}
