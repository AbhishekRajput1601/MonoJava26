package com.abhi.exceptionhandling.throwandthrows;

import java.util.Scanner;
class ThrowExample {

    static void checkAge(int age) {

        if (age < 18) {
            throw new IllegalArgumentException("You are not eligible for vote. Age must be 18 or above.");
        }

        System.out.println("You are eligible for vote.");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();
            checkAge(age);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter numbers only.");
        }finally {
            System.out.println("This is Throw Example");
        }

        scanner.close();
    }
}