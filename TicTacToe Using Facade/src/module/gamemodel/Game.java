package module.gamemodel;

import module.handler.InputHandler;
import module.playermodel.Player;

public class Game {

    private final Board board;
    private final Player player1;
    private final Player player2;
    private final InputHandler inputHandler;

    public Game(Player player1, Player player2, int size, InputHandler inputHandler) {
        this.board = new Board(size);
        this.player1 = player1;
        this.player2 = player2;
        this.inputHandler = inputHandler;
    }

    public void start() {
        board.initializeBoard();
        Player currentPlayer = player1;

        while (true) {
            board.displayBoard();
            currentPlayer.makeMove(board, inputHandler);

            if (board.hasWinner()) {
                board.displayBoard();
                System.out.println(currentPlayer.getName() + " wins!");
                return;
            }

            if (board.isFull()) {
                board.displayBoard();
                System.out.println("Game is a draw!");
                return;
            }

            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }
}

