package com.abhi.mithrilassignment.pigdicegame;

public class PigGameApplication {

    public static void main(String[] args) {

        GameSettings settings = new GameSettings(20);
        PigGameEngine game = new PigGameEngine(settings);

        game.startGame();
    }
}