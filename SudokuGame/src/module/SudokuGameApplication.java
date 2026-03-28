package module;

import java.util.Scanner;

public class SudokuGameApplication{

    static Scanner scanner = new Scanner(System.in);

    public static void start() {

        SudokuGameManager manager = new SudokuGameManager();

        while (true) {

            System.out.println("\n=======================");
            System.out.println("       SUDOKU GAME");
            System.out.println("=======================");
            System.out.println("1. Start Blank Sudoku");
            System.out.println("2. Start Puzzle Sudoku");
            System.out.println("3. Exit");

            int choice = getNumberInput("Select option: ");

            switch (choice) {

                case 1:
                    manager.playBlankGame();
                    break;

                case 2:

                    while (true) {

                        System.out.println("\nSelect Difficulty");
                        System.out.println("1. Easy (30 blanks)");
                        System.out.println("2. Medium (40 blanks)");
                        System.out.println("3. Hard (50 blanks)");
                        System.out.println("4. Back to Main Menu");
                        System.out.println("5. Exit");

                        int diff = getNumberInput("Choose option: ");

                        switch (diff) {

                            case 1:
                                manager.playPuzzleGame(diff);
                                break;
                            case 2:
                                manager.playPuzzleGame(diff);
                                break;
                            case 3:
                                manager.playPuzzleGame(diff);
                                break;

                            case 4:
                                break;

                            case 5:
                                System.out.println("Thank you for playing Sudoku.");
                                System.exit(0);

                            default:
                                System.out.println("Invalid selection. Please try again.");
                                continue;
                        }

                        break;
                    }

                    break;

                case 3:
                    System.out.println("Thank you for playing Sudoku.");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static int getNumberInput(String msg) {

        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }
    }
}
