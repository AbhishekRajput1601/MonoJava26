package com.abhi.pigdicegame;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessingNumberGame {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ATTEMPTS = 5;

    private final Random random;
    private final Scanner scanner;

    public GuessingNumberGame() {
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        GuessingNumberGame game = new GuessingNumberGame();
        game.startGame();
    }

    public void startGame() {

        System.out.println("=== Welcome to the Number Guessing Game ===");

        boolean continuePlaying = true;

        while (continuePlaying) {
            playSingleRound();
            continuePlaying = askToPlayAgain();
        }

        System.out.println("Thank you for playing. Goodbye!");
        scanner.close();
    }

    private void playSingleRound() {

        int targetNumber = generateRandomNumber();
        int attemptCount = 0;
        boolean isGuessedCorrectly = false;

//        System.out.println("Random number generated: " + targetNumber);

        while (attemptCount < MAX_ATTEMPTS && !isGuessedCorrectly) {

            int userGuess = getValidatedGuess();
            attemptCount++;

            if (userGuess < targetNumber) {
                System.out.println("Sorry, Too Low.");
            } else if (userGuess > targetNumber) {
                System.out.println("Sorry, Too High.");
            } else {
                isGuessedCorrectly = true;
                System.out.println("You won in attempt: " + attemptCount);
            }
        }

        if (!isGuessedCorrectly) {
            System.out.println("Maximum possible attempts reached -> " + MAX_ATTEMPTS);
            System.out.println("The correct number was: " + targetNumber);
        }
    }

    private int generateRandomNumber() {
        return random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
    }

    private int getValidatedGuess() {

        while (true) {
            try {
                System.out.print("Guess a number: ");
                int guess = scanner.nextInt();

                if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
                    System.out.println("Invalid input! Please enter a number between "
                            + MIN_NUMBER + " and " + MAX_NUMBER + ".");
                    continue;
                }

                return guess;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); // clear invalid input
            }
        }
    }

    private boolean askToPlayAgain() {

        while (true) {
            System.out.println("Do you want to play the game again? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();

            if (response.equals("yes") || response.equals("y")) {
                return true;
            } else if (response.equals("no") || response.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input! Please type 'yes' or 'no'.");
            }
        }
    }
}
