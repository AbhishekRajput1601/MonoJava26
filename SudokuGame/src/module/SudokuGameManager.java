package module;

import java.util.Scanner;

public class SudokuGameManager {

    private final Scanner scanner = new Scanner(System.in);
    private final SudokuValidator validator = new SudokuValidator();

    public void playBlankGame() {
        int[][] grid = SudokuGenerator.generateBlankGrid();
        startGame(grid);
    }

    public void playPuzzleGame(int difficulty) {
        int[][] grid = SudokuGenerator.generatePuzzle(difficulty);
        startGame(grid);
    }

    private void startGame(int[][] grid) {

        System.out.println("\nGame started. Enter -1 at any time to return to menu.");

        while (true) {

            SudokuUtils.printGrid(grid);

            int r, c, v;

            while (true) {
                System.out.print("\nEnter Row (1-9 or -1 to exit): ");
                r = Integer.parseInt(scanner.nextLine());
                if (r == -1) return;
                r--;

                if (r < 0 || r > 8) {
                    System.out.println("Row must be between 1 and 9.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Enter Column (1-9 or -1 to exit): ");
                c = Integer.parseInt(scanner.nextLine());
                if (c == -1) return;
                c--;

                if (c < 0 || c > 8) {
                    System.out.println("Column must be between 1 and 9.");
                    continue;
                }

                if (grid[r][c] != 0) {
                    System.out.println("Cell already filled. Choose another cell.");
                    continue;
                }

                break;
            }

            while (true) {
                System.out.print("Enter Value (1-9 or -1 to exit): ");
                v = Integer.parseInt(scanner.nextLine());
                if (v == -1) return;

                if (v < 1 || v > 9) {
                    System.out.println("Value must be between 1 and 9.");
                    continue;
                }
                break;
            }

            grid[r][c] = v;

            if (!validator.validate(grid)) {
                System.out.println("Invalid move. Duplicate found. Try again.");
                grid[r][c] = 0;
            }

            if (isGridFull(grid)) {
                SudokuUtils.printGrid(grid);
                System.out.println("\nCongratulations. You completed the Sudoku.");
                showGameEndMenu();
                return;
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
                    System.out.println("Thank you for playing.");
                    System.exit(0);

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}