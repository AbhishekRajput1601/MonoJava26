package com.abhi.sukodugame;

import java.util.Random;

class SudokuGenerator {

    private static final int[][] SOLUTION = {
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
    };

    public static int[][] generateBlankGrid() {

        return new int[9][9];
    }

    public static int[][] generatePuzzle(int difficulty) {

        int[][] puzzle = new int[9][9];

        for (int i = 0; i < 9; i++)
            puzzle[i] = SOLUTION[i].clone();

        int blanks;

        if (difficulty == 1)
            blanks = 30;
        else if (difficulty == 2)
            blanks = 40;
        else
            blanks = 50;

        Random rand = new Random();

        while (blanks > 0) {

            int r = rand.nextInt(9);
            int c = rand.nextInt(9);

            if (puzzle[r][c] != 0) {

                puzzle[r][c] = 0;
                blanks--;
            }
        }

        return puzzle;
    }
}