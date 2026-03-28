package module;

class SudokuUtils {

    public static boolean isValidSet(int[] arr) {

        boolean[] visited = new boolean[10];

        for (int num : arr) {

            if (num == 0) continue;

            if (num < 1 || num > 9) return false;

            if (visited[num]) return false;

            visited[num] = true;
        }

        return true;
    }

    public static void printGrid(int[][] grid) {

        System.out.println("\nCurrent Sudoku Board:\n");

        System.out.print("    ");
        for (int i = 1; i <= 9; i++) {
            System.out.printf("%-2d", i);
            if (i % 3 == 0) System.out.print("  ");
        }
        System.out.println();

        for (int i = 0; i < 9; i++) {

            if (i % 3 == 0)
                System.out.println("  +-------+-------+-------+");

            System.out.printf("%-2d| ", (i + 1));

            for (int j = 0; j < 9; j++) {

                if (grid[i][j] == 0)
                    System.out.print(". ");
                else
                    System.out.print(grid[i][j] + " ");

                if ((j + 1) % 3 == 0)
                    System.out.print("| ");
            }

            System.out.println();
        }

        System.out.println("  +-------+-------+-------+");
    }
}