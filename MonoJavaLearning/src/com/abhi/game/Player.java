package com.abhi.game;

public abstract class Player {

    protected char symbol;
    protected String name;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public abstract int makeMove(Board board);
}