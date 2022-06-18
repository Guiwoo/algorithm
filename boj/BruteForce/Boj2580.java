package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2580 {
    static int[][] grid;

    public static void main(String[] args) throws IOException {
        grid = new int[9][9];
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < grid.length; i++) {
            String[] inputs = bf.readLine().split(" ");
            for (int j = 0; j < inputs.length; j++) {
                grid[i][j] = Integer.parseInt(inputs[j]);
            }
        }
        dfs(0, 0);
        bf.close();
    }

    public static void dfs(int row, int col) {
        if (col == 9) {
            dfs(row + 1, 0);
            return;
        }
        if (row == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(grid[i][j]).append(' ');
                }
                sb.append('\n');
            }
            System.out.println(sb.toString());
            System.exit(0);
        }

        if (grid[row][col] == 0) {
            for (int i = 1; i < 10; i++) {
                if (checker(row, col, i)) {
                    grid[row][col] = i;
                    dfs(row, col + 1);
                }
            }
            grid[row][col] = 0;
            return;
        }
        dfs(row, col + 1);
    }

    public static boolean checker(int row, int col, int val) {
        // Column check
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] == val)
                return false;
        }
        // row check
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == val)
                return false;
        }
        // 3*3 check
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (grid[i][j] == val)
                    return false;
            }
        }
        return true;
    }
}