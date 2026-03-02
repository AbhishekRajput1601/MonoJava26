package com.abhi.game;

import java.util.Random;

public class ComputerPlayer extends Player {

    private Random random;

    public ComputerPlayer(char symbol) {
        super(symbol);
        random = new Random();
    }

    @Override
    public int makeMove(Board board) {

        int position;

        while (true) {
            try {
                position = random.nextInt(9);
                board.makeMove(position, symbol);
                System.out.println("Computer chose position: " + (position + 1));
                return position;

            } catch (Exception e) {
                System.out.println("Error" + e);
            }
        }
    }
}