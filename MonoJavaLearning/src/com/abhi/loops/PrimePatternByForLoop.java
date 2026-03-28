package com.abhi.loops;

import java.util.Scanner;

public class PrimePatternByForLoop {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();

        if (rows <= 0) {
            System.out.println("Invalid input! Please enter a positive number.");
            return;
        }

        int number = 2;

        for (int i = 1; i <= rows; i++) {

            int count = 0;

            while (count < i) {

                if (isPrime(number)) {
                    System.out.print(number + " ");
                    count++;
                }

                number++;
            }

            System.out.println();
        }

        scanner.close();
    }


    public static boolean isPrime(int num) {

        if (num <= 1)
            return false;

        for (int i = 2; i*i <= num; i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }
}


