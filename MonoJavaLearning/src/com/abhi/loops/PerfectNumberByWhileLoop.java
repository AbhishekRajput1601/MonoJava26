package com.abhi.loops;

import java.util.Scanner;

public class PerfectNumberByWhileLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();

        int sum = 0;
        int i = 1;

        while (i < num) {
            if (num % i == 0) {
                sum += i;
            }
            i++;
        }

        if (sum == num) {
            System.out.println("Perfect Number");
        } else {
            System.out.println("Not a Perfect Number");
        }

        scanner.close();
    }
}