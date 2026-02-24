package com.abhi.encapsulation.calisthenicsrule;
import java.util.Scanner;

public class PrimeNumberCheck {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number : ");
        int number = scanner.nextInt();

        if (isPrime(number)) {
            System.out.println(number + " is a Prime Number");
        } else {
            System.out.println(number + " is NOT a Prime Number");
        }
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        } else {
            for (int divisor = 2; divisor <= Math.sqrt(number); divisor++) {
                if (number % divisor == 0) return false;
            }
            return true;
        }
    }
}


//public class PrimeNumberCheck {
//
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter number : ");
//        int number = scanner.nextInt();
//
//        boolean prime = isPrime(number);
//
//        if (prime) {
//            System.out.println(number + " is a Prime Number");
//        }
//
//        if (!prime) {
//            System.out.println(number + " is NOT a Prime Number");
//        }
//    }
//
//    public static boolean isPrime(int number) {
//
//        if (number <= 1) return false;
//
//        for (int divisor = 2; divisor <= Math.sqrt(number); divisor++) {
//            if (number % divisor == 0) return false;
//        }
//
//        return true;
//    }
//}

