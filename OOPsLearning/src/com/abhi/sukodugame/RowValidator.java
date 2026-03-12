package com.abhi.sukodugame;

class RowValidator extends AbstractValidator {

    public boolean validate(int[][] grid) {

        for (int i = 0; i < 9; i++) {

            int[] row = new int[9];

            for (int j = 0; j < 9; j++) {
                row[j] = grid[i][j];
            }

            if (!SudokuUtils.isValidSet(row)) {
                System.out.println("Duplicate detected in row " + (i + 1));
                return false;
            }
        }

        return true;
    }
}