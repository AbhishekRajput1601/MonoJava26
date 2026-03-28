package com.abhi.arrays;

import java.util.Scanner;

public class ReadAndDisplayArray {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int arraySize = readPositiveInteger(scanner, "Enter array size: ");
        int[] numbers = new int[arraySize];

        System.out.println("Enter " + arraySize + " integers:");

        for (int index = 0; index < arraySize; index++) {
            numbers[index] = readInteger(scanner, "Element " + (index + 1) + ": ");
        }

        System.out.println("Array elements are:");
        for (int number : numbers) {
            System.out.print(number + " ");
        }

        scanner.close();
    }

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
                System.out.println("Size must be positive.");
            }
        } while (value <= 0);
        return value;
    }


//    private static int readInteger(Scanner scanner, String message) {
//        boolean valid = false;
//        int value = 0;
//
//        while (!valid) {
//            System.out.print(message);
//
//            if (scanner.hasNextInt()) {
//                value = scanner.nextInt();
//                valid = true;
//            } else {
//                System.out.println("Invalid input! Please enter an integer.");
//                scanner.next();
//            }
//        }
//        return value;
//    }
//
//    private static int readPositiveInteger(Scanner scanner, String message) {
//        int value = readInteger(scanner, message);
//
//        while (value <= 0) {
//            System.out.println("Size must be positive.");
//            value = readInteger(scanner, message);
//        }
//
//        return value;
//    }

}
