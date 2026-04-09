package com.abhi.tictactoeusingfacade;

public class GameFacade {

    private final GameMenu gameMenu;


    public GameFacade(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
    }

    public GameFacade() {
        this(new GameMenu());
    }

    public void start() {
        gameMenu.start();
    }
}
