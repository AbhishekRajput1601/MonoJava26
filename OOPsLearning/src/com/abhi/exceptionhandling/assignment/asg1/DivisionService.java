package com.abhi.exceptionhandling.assignment.asg1;

import java.util.Scanner;

public class DivisionService {

    public void performDivision() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter first number: ");
            double firstNumber = scanner.nextDouble();

            System.out.print("Enter second number: ");
            double secondNumber = scanner.nextDouble();

            double result = firstNumber / secondNumber;
            System.out.println("Result: " + result);

        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero.");
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Only Integer Allowed");
        }
        scanner.close();
    }
}
