package com.abhi.sukodugame;

class ColumnValidator extends AbstractValidator {

    public boolean validate(int[][] grid) {

        for (int col = 0; col < 9; col++) {

            int[] column = new int[9];

            for (int row = 0; row < 9; row++) {
                column[row] = grid[row][col];
            }

            if (!SudokuUtils.isValidSet(column)) {
                System.out.println("Duplicate detected in column " + (col + 1));
                return false;
            }
        }

        return true;
    }
}