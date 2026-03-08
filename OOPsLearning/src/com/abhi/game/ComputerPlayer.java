package com.abhi.game;

import java.util.Random;

public class ComputerPlayer extends Player {

    private Random random;

    public ComputerPlayer(char symbol) {
        super("Bot", symbol);
        random = new Random();
    }

    @Override
    public int makeMove(Board board) {

        int move = findWinningMove(board, symbol);

        if (move == -1) {
            char opponent = symbol == 'X' ? 'O' : 'X';
            move = findWinningMove(board, opponent);
        }

        if (move == -1) {
            while (true) {
                int pos = random.nextInt(board.getTotalCells());
                if (board.isEmpty(pos)) {
                    move = pos;
                    break;
                }
            }
        }

        board.makeMove(move, symbol);
        System.out.println(name + " chose position: " + (move + 1));
        return move;
    }

    private int findWinningMove(Board board, char sym) {

        for (int i = 0; i < board.getTotalCells(); i++) {

            if (board.isEmpty(i)) {

                board.makeMove(i, sym);

                if (board.hasWinner()) {

                    board.undoMove(i);

                    return i;
                }
                board.undoMove(i);
            }
        }
        return -1;
    }
}