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

                switch (choice) {

                    case 1:
                        new Game(
                                new HumanPlayer('X'),
                                new HumanPlayer('O')
                        ).start();
                        break;

                    case 2:
                        new Game(
                                new HumanPlayer('X'),
                                new ComputerPlayer('O')
                        ).start();
                        break;

                    case 3:
                        System.out.println("Thank you for playing!");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice! Select between 1 and 3.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine();
            }
        }
    }
}