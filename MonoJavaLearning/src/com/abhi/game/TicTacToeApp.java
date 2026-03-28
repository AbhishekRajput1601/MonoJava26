package com.abhi.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToeApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            try {
                System.out.println("\n==== TIC TAC TOE GAME ====");
                System.out.println("1. Human vs Human");
                System.out.println("2. Human vs Computer");
                System.out.println("3. Exit");
                System.out.print("Select option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 3) {
                    System.out.println("Thank you for playing!");
                    System.exit(0);
                }

                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice! Select between 1 and 3.");
                    continue;
                }

                System.out.print("Enter board size (eg. 3 for 3x3 and 4 for 4x4) : ");
                int size = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {

                    System.out.print("Enter Player 1 name: ");
                    String p1 = scanner.nextLine();

                    System.out.print("Enter Player 2 name: ");
                    String p2 = scanner.nextLine();

                    new Game(
                            new HumanPlayer(p1, 'X'),
                            new HumanPlayer(p2, 'O'),
                            size
                    ).start();

                } else if (choice == 2) {

                    System.out.print("Enter your name: ");
                    String player = scanner.nextLine();

                    new Game(
                            new HumanPlayer(player, 'X'),
                            new ComputerPlayer('O'),
                            size
                    ).start();
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
    }

}