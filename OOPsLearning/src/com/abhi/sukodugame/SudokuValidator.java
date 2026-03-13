package com.abhi.sukodugame;

class SudokuValidator {

    public boolean validate(int[][] grid) {

        AbstractValidator row = new RowValidator();
        AbstractValidator col = new ColumnValidator();
        AbstractValidator box = new BoxValidator();

        return row.validate(grid) && col.validate(grid) && box.validate(grid);
    }
}
