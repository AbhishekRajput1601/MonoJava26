package com.abhi.tictactoeusingfacade;

import java.util.Random;

public class ComputerPlayer extends Player {

    private final Random random;

    public ComputerPlayer(char symbol) {
        super("Bot", symbol);
        this.random = new Random();
    }

    @Override
    public int makeMove(Board board, InputHandler inputHandler) {
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
                boolean winningMove = board.hasWinner();
                board.undoMove(i);

                if (winningMove) {
                    return i;
                }
            }
        }
        return -1;
    }
}
