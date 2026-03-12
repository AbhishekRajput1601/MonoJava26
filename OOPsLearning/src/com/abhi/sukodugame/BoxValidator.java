package com.abhi.sukodugame;

class BoxValidator extends AbstractValidator {

    public boolean validate(int[][] grid) {

        for (int row = 0; row < 9; row += 3) {

            for (int col = 0; col < 9; col += 3) {

                int[] box = new int[9];
                int k = 0;

                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        box[k++] = grid[row + i][col + j];

                if (!SudokuUtils.isValidSet(box)) {
                    System.out.println("Duplicate in 3x3 box at (" + (row + 1) + "," + (col + 1) + ")");
                    return false;
                }
            }
        }

        return true;
    }
}