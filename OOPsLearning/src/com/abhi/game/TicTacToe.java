package com.abhi.game;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static char[] gameBoard = new char[9];

    public static void main(String[] args) {

        while (true) {
            initializeBoard();
            displayMenu();
            int menuChoice = getMenuChoice();

            switch (menuChoice) {

                case 1:
                    startHumanVsHuman();
                    break;

                case 2:
                    startHumanVsComputer();
                    break;

                case 3:
                    System.out.println("Thank you for playing. Game Over.");
                    System.exit(0);
                    break;
            }
        }
    }

    public static void displayMenu() {
        System.out.println("\n==== TIC TAC TOE GAME ====");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Computer");
        System.out.println("3. Exit");
    }

    public static void initializeBoard() {
        for (int index = 0; index < 9; index++) {
            gameBoard[index] = ' ';
        }
    }

    public static int getMenuChoice() {

        while (true) {
            System.out.print("Select option (1-3): ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Invalid input. Enter numbers only.");
                continue;
            }

            int choice = scanner.nextInt();

            if (choice < 1 || choice > 3) {
                System.out.println("Invalid choice. Select between 1 and 3.");
                continue;
            }

            return choice;
        }
    }

    public static void startHumanVsHuman() {

        char currentPlayer = 'X';

        while (true) {
            printBoard();
            int position = getPlayerMove(currentPlayer);
            gameBoard[position] = currentPlayer;

            if (hasWinner()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins the game.");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("The game is a draw.");
                break;
            }

            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
        }
    }

    public static void startHumanVsComputer() {

        char humanPlayer = 'X';
        char computerPlayer = 'O';

        while (true) {
            printBoard();
            int humanMove = getPlayerMove(humanPlayer);
            gameBoard[humanMove] = humanPlayer;

            if (hasWinner()) {
                printBoard();
                System.out.println("Player " + humanPlayer + " wins the game.");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("The game is a draw.");
                break;
            }

            int computerMove = getComputerMove();
            gameBoard[computerMove] = computerPlayer;
            System.out.println("Computer selected position: " + (computerMove + 1));

            if (hasWinner()) {
                printBoard();
                System.out.println("Player " + computerPlayer + " wins the game.");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("The game is a draw.");
                break;
            }
        }
    }

    public static int getPlayerMove(char playerSymbol) {

        while (true) {
            System.out.print("Player " + playerSymbol + ", enter position (1-9): ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Invalid input. Enter numbers only.");
                continue;
            }

            int position = scanner.nextInt() - 1;

            if (position < 0 || position > 8) {
                System.out.println("Invalid position. Choose between 1 and 9.");
                continue;
            }

            if (gameBoard[position] != ' ') {
                System.out.println("Position already occupied. Try another.");
                continue;
            }

            return position;
        }
    }

    public static int getComputerMove() {

        while (true) {
            int position = random.nextInt(9);

            if (gameBoard[position] == ' ')
                return position;
        }
    }

    public static void printBoard() {

        System.out.println();
        System.out.println(" " + gameBoard[0] + " | " + gameBoard[1] + " | " + gameBoard[2]);
        System.out.println("---|---|---");
        System.out.println(" " + gameBoard[3] + " | " + gameBoard[4] + " | " + gameBoard[5]);
        System.out.println("---|---|---");
        System.out.println(" " + gameBoard[6] + " | " + gameBoard[7] + " | " + gameBoard[8]);
        System.out.println();
    }

    public static boolean hasWinner() {

        int[][] winningPositions = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for (int[] position : winningPositions) {

            if (gameBoard[position[0]] == ' ')
                continue;

            if (gameBoard[position[0]] == gameBoard[position[1]] &&
                    gameBoard[position[1]] == gameBoard[position[2]])
                return true;
        }

        return false;
    }

    public static boolean isBoardFull() {

        for (char cell : gameBoard) {
            if (cell == ' ')
                return false;
        }

        return true;
    }
}