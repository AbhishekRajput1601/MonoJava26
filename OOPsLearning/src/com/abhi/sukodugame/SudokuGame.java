package com.abhi.sukodugame;

import java.util.Scanner;

public class SudokuGame {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== SUDOKU GAME =====");
            System.out.println("1. Play Game");
            System.out.println("2. Exit");

            int choice = getNumberInput("Select option: ");

            if (choice == 2) {
                System.out.println("Goodbye!");
                break;
            }

            if (choice != 1) {
                System.out.println("Invalid option.");
                continue;
            }

            System.out.println("\nSelect Difficulty");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");

            int diff = getNumberInput("Choose difficulty: ");

            if (diff < 1 || diff > 3) {
                System.out.println("Invalid difficulty.");
                continue;
            }

            int[][] puzzle = SudokuGenerator.generatePuzzle(diff);
            SudokuUtils.printGrid(puzzle);

            SudokuValidator validator = new SudokuValidator();

            while (true) {

                System.out.println("\nEnter row column value (1-9)");
                System.out.println("Example: 1 3 5");
                System.out.print("Enter Your Number : ");
                String input = sc.nextLine();
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


                    if (puzzle[r][c] != 0) {
                        System.out.println("Cell already filled. Choose another cell.");
                        continue;
                    }

                    puzzle[r][c] = v;


                    if (!validator.validate(puzzle)) {

                        System.out.println("Invalid move! Duplicate detected.");
                        puzzle[r][c] = 0;
                        continue;

                    }

                    System.out.println("Move accepted.");

                    SudokuUtils.printGrid(puzzle);

                } catch (Exception e) {

                    System.out.println("Invalid numbers entered.");

                }
            }
        }
    }

    public static int getNumberInput(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}

