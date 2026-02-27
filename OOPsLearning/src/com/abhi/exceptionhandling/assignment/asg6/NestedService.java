package com.abhi.exceptionhandling.assignment.asg6;

import java.util.Scanner;

public class NestedService {

    public void execute() {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter number to divide 10 by: ");
            int number = Integer.parseInt(scanner.nextLine());

            int result = 10 / number;
            System.out.println("Division Result: " + result);

            try {

                int[] numbers = new int[2];
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

        }
    }
}
