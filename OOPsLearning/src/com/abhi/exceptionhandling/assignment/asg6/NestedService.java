package com.abhi.exceptionhandling.assignment.asg6;

import java.util.Scanner;

public class NestedService {

    public void execute() {

        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[2];

        try {
            System.out.println("Enter numbers");
            System.out.print("Enter first number : ");
            int numberOne = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter second number : ");
            int numberTwo = Integer.parseInt(scanner.nextLine());

            int result = numberOne / numberTwo;
            System.out.println("Division Result: " + result);

            try {
                System.out.print("Enter index to access: ");
                int index = Integer.parseInt(scanner.nextLine());
                System.out.println(numbers[index]);

            } catch (ArrayIndexOutOfBoundsException e) {

                System.out.println("Inner tryCatch : Array index error.");

            }

        } catch (ArithmeticException e) {

            System.out.println("Outer tryCatch : Cannot divide by zero.");

        } catch (NumberFormatException e) {

            System.out.println("Invalid numeric input.");

        } finally {
            scanner.close();
        }
    }
}
