package module;

class BoxValidator extends AbstractValidator {

    public boolean validate(int[][] grid) {

        for (int row = 0; row < 9; row += 3) {

            for (int col = 0; col < 9; col += 3) {

                int[] box = new int[9];
                int index = 0;

                for (int i = row; i < row + 3; i++)
                    for (int j = col; j < col + 3; j++)
                        box[index++] = grid[i][j];

                if (!SudokuUtils.isValidSet(box)) {

                    System.out.println("Duplicate detected in box starting at row "
                            + (row + 1) + ", column " + (col + 1));
                    return false;
                }
            }
        }

        return true;
    }
}