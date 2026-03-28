package com.abhi.pigdicegame;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Scanner;

public class PigDiceGame {
    public static void main(String[] args) {
        GameConfig config = new GameConfig(20);
        PigGame game = new PigGame(config);
        game.start();
    }
}

class GameConfig {
    private final int targetScore;

    public GameConfig(int targetScore) {
        if (targetScore <= 0) {
            throw new IllegalArgumentException("Target score must be greater than zero.");
        }
        this.targetScore = targetScore;
    }

    public int getTargetScore() {
        return targetScore;
    }
}

class PigGame {

    private static final String ROLL_COMMAND = "r";
    private static final String HOLD_COMMAND = "h";

    private final GameConfig config;
    private final Dice dice;
    private final Scanner scanner;

    private int totalScore;
    private int turnCount;

    public PigGame(GameConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Game configuration cannot be null.");
        }

        this.config = config;
        this.dice = new Dice();
        this.scanner = new Scanner(System.in);
        this.totalScore = 0;
        this.turnCount = 0;
    }

    public void start() {
        printWelcomeMessage();

        while (!hasPlayerWon()) {
            playSingleTurn();
        }

        printGameOverMessage();
        closeResources();
    }

    private void printWelcomeMessage() {
        System.out.println("Let's Play PIG!");
        System.out.println("* See how many turns it takes you to get to " + config.getTargetScore() + ".");
        System.out.println("* Turn ends when you hold or roll a 1.");
        System.out.println("* If you roll a 1, you lose all points for the turn.");
        System.out.println("* If you hold, you save all points for the turn.");
        System.out.println();
    }

    private void playSingleTurn() {
        turnCount++;
        int turnScore = 0;
        boolean turnActive = true;

        System.out.println("TURN " + turnCount);

        while (turnActive) {
            String playerChoice = promptPlayerChoice();

            if (ROLL_COMMAND.equals(playerChoice)) {
                int rollResult = dice.roll();
                System.out.println("Die: " + rollResult);

                if (rollResult == 1) {
                    turnScore = 0;
                    System.out.println("Turn over. No score.");
                    turnActive = false;
                } else {
                    turnScore += rollResult;
                }
            } else if (HOLD_COMMAND.equals(playerChoice)) {
                totalScore += turnScore;
                System.out.println("Score for this turn is : " + turnScore);
                System.out.println("Total score is : " + totalScore);
                turnActive = false;
            }

            if (totalScore >= config.getTargetScore()) {
                turnActive = false;
            }
        }

        System.out.println();
    }

    private String promptPlayerChoice() {
        while (true) {
            System.out.print("Roll or hold? (r/h): ");

            if (!scanner.hasNextLine()) {
                System.out.println("Input stream closed unexpectedly. Exiting game.");
                System.exit(1);
            }

            String input = scanner.nextLine();

            if (input == null) {
                continue;
            }

            input = input.trim().toLowerCase(Locale.ROOT);

            if (ROLL_COMMAND.equals(input) || HOLD_COMMAND.equals(input)) {
                return input;
            }

            System.out.println("Invalid input. Please enter 'r' to roll or 'h' to hold.");
        }
    }

    private boolean hasPlayerWon() {
        return totalScore >= config.getTargetScore();
    }

    private void printGameOverMessage() {
        System.out.println("You finished in " + turnCount + " turns!");
        System.out.println();
        System.out.println("Game over!");
    }

    private void closeResources() {
        scanner.close();
    }
}

class Dice {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 6;

    private final SecureRandom randomGenerator;

    public Dice() {
        this.randomGenerator = new SecureRandom();
    }

    public int roll() {
        return randomGenerator.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
    }
}
