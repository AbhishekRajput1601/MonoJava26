package com.abhi.conditions;

import java.util.Scanner;

public class ArmstrongNumberChecker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a 3-digit number: ");
        int number = scanner.nextInt();

        if (number < 100 || number > 999) {
            System.out.println("Please enter a valid 3-digit number.");
        } else if (isArmstrong(number)) {
            System.out.println(number + " is an Armstrong Number.");
        } else {
            System.out.println(number + " is Not an Armstrong Number.");
        }

        scanner.close();
    }

    private static boolean isArmstrong(int number) {

        int originalNumber = number;
        int sum = 0;

        while (number != 0) {
            int digit = number % 10;
            sum += digit * digit * digit;
            number /= 10;
        }

        return sum == originalNumber;
    }
}

