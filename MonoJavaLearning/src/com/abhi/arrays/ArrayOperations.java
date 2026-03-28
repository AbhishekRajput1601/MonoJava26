package com.abhi.arrays;

import java.util.Scanner;

public class ArrayOperations {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int arraySize = readPositiveInteger(scanner, "Enter array size: ");
        int[] numbers = new int[arraySize];

        System.out.println("Enter " + arraySize + " integer elements:");

        for (int index = 0; index < arraySize; index++) {
            numbers[index] = readInteger(scanner, "Element " + (index + 1) + ": ");
        }

        System.out.println("----- Results -----");
        System.out.println("Maximum: " + findMaximum(numbers));
        System.out.println("Sum: " + calculateSum(numbers));
        System.out.println("Second Maximum: " + findSecondMaximum(numbers));

        int targetValue = readInteger(scanner, "Enter number to count occurrences: ");
        System.out.println("Occurrences of " + targetValue + " is" +": " +
                countOccurrences(numbers, targetValue));

        scanner.close();
    }

    private static int readInteger(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
        }
    }

    private static int readPositiveInteger(Scanner scanner, String message) {
        int value;
        while (true) {
            value = readInteger(scanner, message);
            if (value > 0) {
                return value;
            } else {
                System.out.println("Size must be greater than 0.");
            }
        }
    }

    private static int findMaximum(int[] numbers) {
        int maximumValue = numbers[0];
        for (int index = 1; index < numbers.length; index++) {
            if (numbers[index] > maximumValue) {
                maximumValue = numbers[index];
            }
        }
        return maximumValue;
    }

    private static int calculateSum(int[] numbers) {
        int totalSum = 0;
        for (int number : numbers) {
            totalSum += number;
        }
        return totalSum;
    }

    private static int findSecondMaximum(int[] numbers) {
        if (numbers.length < 2) {
            throw new IllegalArgumentException("Array must contain at least two elements.");
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int number : numbers) {
            if (number > largest) {
                secondLargest = largest;
                largest = number;
            } else if (number > secondLargest && number != largest) {
                secondLargest = number;
            }
        }

        if (secondLargest == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("No distinct second maximum found.");
        }

        return secondLargest;
    }

    private static int countOccurrences(int[] numbers, int targetValue) {
        int occurrenceCount = 0;
        for (int number : numbers) {
            if (number == targetValue) {
                occurrenceCount++;
            }
        }
        return occurrenceCount;
    }
}

