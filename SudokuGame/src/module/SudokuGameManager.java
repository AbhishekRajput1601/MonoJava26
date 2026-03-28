package module;

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

            int r, c, v;

            while (true) {
                try {
                    System.out.print("\nEnter Row (1-9): ");
                    r = Integer.parseInt(scanner.nextLine()) - 1;

                    if (r < 0 || r > 8) {
                        System.out.println("Row must be between 1 and 9.");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid row input.");
                }
            }


            while (true) {
                try {
                    System.out.print("Enter Column (1-9): ");
                    c = Integer.parseInt(scanner.nextLine()) - 1;

                    if (c < 0 || c > 8) {
                        System.out.println("Column must be between 1 and 9.");
                        continue;
                    }

                    if (grid[r][c] != 0) {
                        System.out.println("Cell already filled.");
                        continue;
                    }

                    break;
                } catch (Exception e) {
                    System.out.println("Invalid column input.");
                }
            }


            while (true) {
                try {
                    System.out.print("Enter Value (1-9): ");
                    v = Integer.parseInt(scanner.nextLine());

                    if (v < 1 || v > 9) {
                        System.out.println("Value must be between 1 and 9.");
                        continue;
                    }
                    break;

                } catch (Exception e) {
                    System.out.println("Invalid value input.");
                }
            }

            grid[r][c] = v;

            if (!validator.validate(grid)) {

                System.out.println("Invalid move! Duplicate detected.");
                grid[r][c] = 0;
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