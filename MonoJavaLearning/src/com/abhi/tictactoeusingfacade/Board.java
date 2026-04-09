package com.abhi.tictactoeusingfacade;

import java.util.Arrays;

public class Board {

    private final char[] board;
    private final int size;

    public Board(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Board size must be greater than zero.");
        }

        this.size = size;
        this.board = new char[size * size];
        initializeBoard();
    }

    public void initializeBoard() {
        Arrays.fill(board, ' ');
    }

    public void displayBoard() {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') {
                System.out.printf(" %2d ", i + 1);
            } else {
                System.out.printf(" %2c ", board[i]);
            }

            if ((i + 1) % size != 0) {
                System.out.print("|");
            }

            if ((i + 1) % size == 0 && i != board.length - 1) {
                System.out.println();
                for (int j = 0; j < size; j++) {
                    System.out.print("----");
                }
                System.out.println();
            }
        }
        System.out.println("\n");
    }

    public void makeMove(int position, char symbol) {
        validatePosition(position);

        if (board[position] != ' ') {
            throw new InvalidMoveException("Position already occupied!");
        }

        board[position] = symbol;
    }

    public void undoMove(int position) {
        validatePosition(position);
        board[position] = ' ';
    }

    public boolean isEmpty(int position) {
        validatePosition(position);
        return board[position] == ' ';
    }

    public boolean hasWinner() {
        for (int r = 0; r < size; r++) {
            char first = board[r * size];
            if (first == ' ') {
                continue;
            }

            boolean win = true;
            for (int c = 1; c < size; c++) {
                if (board[r * size + c] != first) {
                    win = false;
                    break;
                }
            }

            if (win) {
                return true;
            }
        }

        for (int c = 0; c < size; c++) {
            char first = board[c];
            if (first == ' ') {
                continue;
            }

            boolean win = true;
            for (int r = 1; r < size; r++) {
                if (board[r * size + c] != first) {
                    win = false;
                    break;
                }
            }

            if (win) {
                return true;
            }
        }

        char first = board[0];
        if (first != ' ') {
            boolean win = true;
            for (int i = 1; i < size; i++) {
                if (board[i * size + i] != first) {
                    win = false;
                    break;
                }
            }

            if (win) {
                return true;
            }
        }

        first = board[size - 1];
        if (first != ' ') {
            boolean win = true;
            for (int i = 1; i < size; i++) {
                if (board[i * size + (size - i - 1)] != first) {
                    win = false;
                    break;
                }
            }

            if (win) {
                return true;
            }
        }

        return false;
    }

    public boolean isFull() {
        for (char c : board) {
            if (c == ' ') {
                return false;
            }
        }
        return true;
    }

    public int getTotalCells() {
        return board.length;
    }


    private void validatePosition(int position) {
        if (position < 0 || position >= board.length) {
            throw new InvalidMoveException("Invalid position!");
        }
    }
}
