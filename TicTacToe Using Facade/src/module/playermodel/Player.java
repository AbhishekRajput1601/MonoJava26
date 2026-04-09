package module.playermodel;

import module.gamemodel.Board;
import module.handler.InputHandler;

public abstract class Player {

    protected final char symbol;
    protected final String name;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }


    public String getName() {
        return name;
    }

    public abstract int makeMove(Board board, InputHandler inputHandler);
}
