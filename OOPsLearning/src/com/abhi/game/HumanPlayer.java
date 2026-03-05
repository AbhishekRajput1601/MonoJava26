package com.abhi.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner scanner;

    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
        scanner = new Scanner(System.in);
    }

    @Override
    public int makeMove(Board board) {

        while (true) {
            try {
                System.out.print(name + " (" + symbol + ") choose position (1-" + board.getTotalCells() + "): ");
                int position = scanner.nextInt() - 1;

                board.makeMove(position, symbol);
                return position;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine();

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid position!");

            } catch (IllegalStateException e) {
                System.out.println("Position already occupied!");
            }
        }
    }
}