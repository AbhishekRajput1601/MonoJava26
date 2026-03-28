package com.abhi.mithrilassignment.pigdicegame;

import java.security.SecureRandom;

public class DiceRoller {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 6;

    private final SecureRandom random = new SecureRandom();

    public int roll() {
        return random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
    }
}