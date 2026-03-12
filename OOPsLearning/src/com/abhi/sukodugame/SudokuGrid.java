package com.abhi.sukodugame;

class SudokuGrid {

    private int[][] grid;

    public SudokuGrid(int[][] grid) {
        this.grid = grid;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setValue(int r, int c, int value) {
        grid[r][c] = value;
    }

}
