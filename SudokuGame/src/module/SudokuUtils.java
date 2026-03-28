package module;


class SudokuUtils {

    public static boolean isValidSet(int[] arr) {

        boolean[] visited = new boolean[10];

        for (int num : arr) {

            if (num == 0) continue;

            if (visited[num]) return false;

            visited[num] = true;
        }

        return true;
    }

    public static void printGrid(int[][] grid) {

        System.out.println("\nCurrent Sudoku Board:");

        for (int i = 0; i < 9; i++) {

            if (i % 3 == 0)
                System.out.println("+-------+-------+-------+");

            for (int j = 0; j < 9; j++) {

                if (j % 3 == 0)
                    System.out.print("| ");

                if (grid[i][j] == 0)
                    System.out.print(". ");
                else
                    System.out.print(grid[i][j] + " ");
            }

            System.out.println("|");
        }

        System.out.println("+-------+-------+-------+");
    }
}