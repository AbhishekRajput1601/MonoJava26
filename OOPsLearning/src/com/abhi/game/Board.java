package com.abhi.game;

public class Board {

    private char[] board;

    public Board() {
        board = new char[9];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
    }

    public void displayBoard() {
        System.out.println();
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---|---|---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---|---|---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8]);
        System.out.println();
    }

    public void makeMove(int position, char symbol) {

        if (position < 0 || position > 8) {
            throw new IllegalArgumentException("INVALID_POSITION");
        }

        if (board[position] != ' ') {
            throw new IllegalStateException("OCCUPIED_POSITION");
        }

        board[position] = symbol;
    }

    public boolean hasWinner() {

        int[][] winPositions = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for (int[] pos : winPositions) {
            if (board[pos[0]] != ' ' &&
                    board[pos[0]] == board[pos[1]] &&
                    board[pos[1]] == board[pos[2]]) {
                return true;
            }
        }

        return false;
    }

    public boolean isFull() {
        for (char cell : board) {
            if (cell == ' ')
                return false;
        }
        return true;
    }
}