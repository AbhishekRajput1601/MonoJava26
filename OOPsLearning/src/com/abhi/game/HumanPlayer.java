package com.abhi.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner scanner;

    public HumanPlayer(char symbol) {
        super(symbol);
        scanner = new Scanner(System.in);
    }

    @Override
    public int makeMove(Board board) {

        while (true) {
            try {
                System.out.print("Player " + symbol + ", enter position (1-9): ");
                int position = scanner.nextInt() - 1;

                board.makeMove(position, symbol);
                return position;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine();

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid position! Choose between 1 and 9.");

            } catch (IllegalStateException e) {
                System.out.println("Position already occupied! Try another.");
            }
        }
    }
}