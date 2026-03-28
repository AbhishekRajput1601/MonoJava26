package com.abhi.sukodugame;

import java.util.Scanner;

public class SudokuGame {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        SudokuGameManager manager = new SudokuGameManager();

        while (true) {

            System.out.println("\n===== SUDOKU GAME =====");
            System.out.println("1. Start Blank Sudoku");
            System.out.println("2. Start Puzzle Sudoku");
            System.out.println("3. Exit");

            int choice = getNumberInput("Select option: ");

            switch (choice) {

                case 1:
                    manager.playBlankGame();
                    break;

                case 2:

                    System.out.println("\nSelect Difficulty");
                    System.out.println("1. Easy");
                    System.out.println("2. Medium");
                    System.out.println("3. Hard");

                    int diff = getNumberInput("Choose difficulty: ");

                    if (diff >= 1 && diff <= 3)
                        manager.playPuzzleGame(diff);
                    else
                        System.out.println("Invalid difficulty.");

                    break;

                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static int getNumberInput(String msg) {

        while (true) {

            System.out.print(msg);

            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }
}