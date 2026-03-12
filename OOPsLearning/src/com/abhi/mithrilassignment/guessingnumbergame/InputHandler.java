package com.abhi.mithrilassignment.guessingnumbergame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;

    private final Scanner scanner = new Scanner(System.in);

    public int chooseDifficulty() {

        System.out.println("\nChoose Difficulty Level :");
        System.out.println("1. Easy (10 attempts)");
        System.out.println("2. Medium (7 attempts)");
        System.out.println("3. Hard (5 attempts)");

        while (true) {
            try {
                System.out.print("Enter Choice : ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        return 10;
                    case 2:
                        return 7;
                    case 3:
                        return 5;
                    default:
                        System.out.println("Invalid choice. Select 1, 2, or 3.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a number.");
                scanner.next();
            }
        }
    }

    public int getValidatedGuess() {

        while (true) {
            try {

                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();

                if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
                    System.out.println("Enter a number between "
                            + MIN_NUMBER + " and " + MAX_NUMBER);
                    continue;
                }

                return guess;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter an integer.");
                scanner.next();
            }
        }
    }

    public boolean askToPlayAgain() {

        while (true) {

            System.out.print("Play again? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();

            if (response.equalsIgnoreCase("yes")) {
                return true;
            } else if (response.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Type yes or no.");
            }
        }
    }
}