package com.abhi.mithrilassignment.guessingnumbergame;

import java.util.Random;

public class NumberGenerator {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;

    private final Random random = new Random();

    public int generateNumber() {
        return random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
    }
}