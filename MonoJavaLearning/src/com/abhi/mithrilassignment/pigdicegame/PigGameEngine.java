package com.abhi.mithrilassignment.pigdicegame;

import java.util.Locale;
import java.util.Scanner;

public class PigGameEngine {

    private static final String ROLL = "r";
    private static final String HOLD = "h";

    private final GameSettings settings;
    private final DiceRoller dice;
    private final Scanner scanner;

    private int totalScore;
    private int turnCount;

    public PigGameEngine(GameSettings settings) {

        if (settings == null) {
            throw new IllegalArgumentException("Game settings cannot be null.");
        }

        this.settings = settings;
        this.dice = new DiceRoller();
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {

        printWelcomeMessage();

        while (!hasPlayerWon()) {
            playTurn();
        }

        printGameOverMessage();
        scanner.close();
    }

    private void printWelcomeMessage() {

        System.out.println("=== PIG Dice Game ===");
        System.out.println("Reach " + settings.getTargetScore() + " points to win.");
        System.out.println("* Turn ends when you hold or roll a 1.");
        System.out.println("* If you roll a 1, you lose all points for the turn.");
        System.out.println("* If you hold, you save all points for the turn.");
        System.out.println();
    }

    private void playTurn() {

        turnCount++;
        int turnScore = 0;
        boolean turnActive = true;

        System.out.println("TURN " + turnCount);

        while (turnActive) {

            String choice = getPlayerChoice();

            if (ROLL.equals(choice)) {

                int roll = dice.roll();
                System.out.println("Dice rolled: " + roll);

                if (roll == 1) {

                    System.out.println("Turn over. No score.");
                    turnScore = 0;
                    turnActive = false;

                } else {

                    turnScore += roll;
                    System.out.println("Turn score: " + turnScore);
                }

            } else {

                totalScore += turnScore;

                System.out.println("Score this turn: " + turnScore);
                System.out.println("Total score: " + totalScore);

                turnActive = false;
            }

            if (hasPlayerWon()) {
                turnActive = false;
            }
        }

        System.out.println();
    }

    private String getPlayerChoice() {

        while (true) {

            System.out.print("Roll or hold? (r/h): ");

            String input = scanner.nextLine().trim().toLowerCase(Locale.ROOT);

            if (ROLL.equals(input) || HOLD.equals(input)) {
                return input;
            }

            System.out.println("Invalid input. Enter 'r' or 'h'.");
        }
    }

    private boolean hasPlayerWon() {
        return totalScore >= settings.getTargetScore();
    }

    private void printGameOverMessage() {

        System.out.println("You finished in " + turnCount + " turns!");
        System.out.println("Game over!");
    }
}
