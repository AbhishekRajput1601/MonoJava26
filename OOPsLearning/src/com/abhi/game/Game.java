package com.abhi.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    private Board board;
    private Player player1;
    private Player player2;

    public Game(Player p1, Player p2) {
        board = new Board();
        this.player1 = p1;
        this.player2 = p2;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            board.initializeBoard();
            Player currentPlayer = player1;

            while (true) {

                board.displayBoard();
                currentPlayer.makeMove(board);

                if (board.hasWinner()) {
                    board.displayBoard();
                    System.out.println("Player " + currentPlayer.getSymbol() + " wins!");
                    return;
                }

                if (board.isFull()) {
                    board.displayBoard();
                    System.out.println("Game is a draw!");

                    while (true) {
                        System.out.println("1. Play Again");
                        System.out.println("2. Return to Menu");
                        System.out.print("Select option: ");

                        try {
                            int choice = scanner.nextInt();

                            if (choice == 1) {
                                break;
                            } else if (choice == 2) {
                                return;
                            } else {
                                System.out.println("Invalid choice! Enter 1 or 2.");
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Please enter numbers only.");
                            scanner.nextLine();
                        }
                    }

                    break;
                }

                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            }
        }
    }
}