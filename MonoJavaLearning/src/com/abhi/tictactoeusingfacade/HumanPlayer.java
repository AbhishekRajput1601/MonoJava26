package com.abhi.tictactoeusingfacade;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
    }

    @Override
    public int makeMove(Board board, InputHandler inputHandler) {
        while (true) {
            try {
                int position = inputHandler.readPositiveInt(
                        name + " (" + symbol + ") choose position (1-" + board.getTotalCells() + "): ");

                board.makeMove(position - 1, symbol);
                return position - 1;

            } catch (InvalidMoveException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
