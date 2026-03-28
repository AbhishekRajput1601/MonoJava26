package com.abhi.mithrilassignment.pigdicegame;

public class GameSettings {

    private final int targetScore;

    public GameSettings(int targetScore) {

        if (targetScore <= 0) {
            throw new IllegalArgumentException("Target score must be greater than zero.");
        }

        this.targetScore = targetScore;
    }

    public int getTargetScore() {
        return targetScore;
    }
}
