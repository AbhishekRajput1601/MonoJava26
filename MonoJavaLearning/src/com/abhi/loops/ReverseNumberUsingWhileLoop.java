package com.abhi.loops;

import java.util.Scanner;

public class ReverseNumberUsingWhileLoop {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();

        if (number < 0) {
            System.out.println("Invalid input! Please enter a positive number.");
            return;
        }

        int reversedNumber = 0;

        while (number != 0) {
            int digit = number % 10;
            reversedNumber = reversedNumber * 10 + digit;
            number = number / 10;
        }

        System.out.println("Reversed Number: " + reversedNumber);

        scanner.close();
    }
}

