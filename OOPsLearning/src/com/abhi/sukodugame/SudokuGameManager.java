package com.abhi.sukodugame;

import java.util.Scanner;

public class SudokuGameManager {

    private Scanner scanner = new Scanner(System.in);
    private SudokuValidator validator = new SudokuValidator();

    public void playBlankGame() {

        int[][] grid = SudokuGenerator.generateBlankGrid();
        startGame(grid);
    }

    public void playPuzzleGame(int difficulty) {

        int[][] grid = SudokuGenerator.generatePuzzle(difficulty);
        startGame(grid);
    }

    private void startGame(int[][] grid) {

        while (true) {

            SudokuUtils.printGrid(grid);

            System.out.println("\nEnter row column value (1-9)");
            System.out.println("Example: 1 3 5");
            System.out.print("Enter Your Number : ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length != 3) {
                System.out.println("Invalid format.");
                continue;
            }

            try {

                int r = Integer.parseInt(parts[0]) - 1;
                int c = Integer.parseInt(parts[1]) - 1;
                int v = Integer.parseInt(parts[2]);

                if (r < 0 || r > 8 || c < 0 || c > 8 || v < 1 || v > 9) {
                    System.out.println("Numbers must be between 1 and 9.");
                    continue;
                }

                if (grid[r][c] != 0) {
                    System.out.println("Cell already filled.");
                    continue;
                }

                grid[r][c] = v;

                if (!validator.validate(grid)) {

                    System.out.println("Invalid move! Duplicate detected.");
                    grid[r][c] = 0;
                }

            } catch (Exception e) {

                System.out.println("Invalid numbers.");
            }

            if (isGridFull(grid)) {

                SudokuUtils.printGrid(grid);

                System.out.println("\nSudoku Completed!");

                showGameEndMenu();
                break;
            }
        }
    }

    private boolean isGridFull(int[][] grid) {

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (grid[i][j] == 0)
                    return false;

        return true;
    }

    private void showGameEndMenu() {

        while (true) {

            System.out.println("\n1. Play Again");
            System.out.println("2. Back to Menu");
            System.out.println("3. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    playBlankGame();
                    return;

                case 2:
                    return;

                case 3:
                    System.exit(0);

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}