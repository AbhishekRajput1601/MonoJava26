package module;

class ColumnValidator extends AbstractValidator {

    public boolean validate(int[][] grid) {

        for (int i = 0; i < 9; i++) {

            int[] col = new int[9];

            for (int j = 0; j < 9; j++)
                col[j] = grid[j][i];

            if (!SudokuUtils.isValidSet(col)) {

                System.out.println("Duplicate detected in column " + (i + 1));
                return false;
            }
        }

        return true;
    }
}
