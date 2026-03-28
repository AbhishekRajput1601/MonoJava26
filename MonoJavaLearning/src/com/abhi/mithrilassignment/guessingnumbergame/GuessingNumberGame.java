package com.abhi.mithrilassignment.guessingnumbergame;

public class GuessingNumberGame {

    private int wins = 0;
    private int losses = 0;

    private final InputHandler inputHandler;
    private final NumberGenerator numberGenerator;

    public GuessingNumberGame() {
        inputHandler = new InputHandler();
        numberGenerator = new NumberGenerator();
    }

    public static void main(String[] args) {
        GuessingNumberGame game = new GuessingNumberGame();
        game.startGame();
    }

    public void startGame() {

        System.out.println("\n=== Welcome to the Number Guessing Game ===");

        boolean continuePlaying = true;

        while (continuePlaying) {
            playSingleRound();
            continuePlaying = inputHandler.askToPlayAgain();
        }

        System.out.println("Final Score -> Wins: " + wins + " | Losses: " + losses);
    }

    private void playSingleRound() {

        final int maxAttempts = inputHandler.chooseDifficulty();
        final int targetNumber = numberGenerator.generateNumber();

        int attemptsLeft = maxAttempts;
        boolean isGuessedCorrectly = false;

        System.out.println("Guess a number between 1 and 100");

        while (attemptsLeft > 0 && !isGuessedCorrectly) {

            int guess = inputHandler.getValidatedGuess();

            if (guess == targetNumber) {
                isGuessedCorrectly = true;
                wins++;
                System.out.println("Correct! Congratulation You guessed the number.");
                break;
            }

            giveHint(guess, targetNumber);

            attemptsLeft--;

            if (attemptsLeft > 0) {
                System.out.println("Remaining attempts: " + attemptsLeft);
            }
        }

        if (!isGuessedCorrectly) {
            losses++;
            System.out.println("You lost! The number was: " + targetNumber);
        }

        System.out.println("Score -> Wins: " + wins + " | Losses: " + losses);
    }

    private void giveHint(int guess, int target) {

        int difference = Math.abs(guess - target);

        if (guess < target) {
            if (difference <= 3) {
                System.out.println("Low, but very close!");
            } else {
                System.out.println("Too Low.");
            }
        } else {
            if (difference <= 3) {
                System.out.println("High, but very close!");
            } else {
                System.out.println("Too High.");
            }
        }
    }
}