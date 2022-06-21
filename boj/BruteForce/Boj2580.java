package BruteForce;

/**
0 4 0 0 0 0 2 0 0
0 6 0 0 0 5 0 0 0
2 0 5 0 8 0 0 0 7
0 0 6 0 0 0 0 0 0
5 0 7 0 0 1 9 0 0
0 0 0 0 4 0 0 1 0
0 0 0 3 0 0 0 0 8
0 2 0 0 0 0 0 0 0
9 0 1 0 0 4 7 0 0
 * 
0 0 0 0 0 0 0 0 0
7 8 2 1 3 5 6 4 9
4 6 9 2 7 8 1 3 5
3 2 1 5 4 6 8 9 7
0 0 0 0 0 0 0 0 0
5 9 6 8 2 7 4 1 3
9 1 7 6 5 2 3 8 4
6 4 3 7 8 1 9 5 2
0 0 0 0 0 0 0 0 0
 * 
 * 
0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
 */

import java.io.*;
import java.util.*;

//596ms
public class Boj2580 {
    static int[][] grid;

    public static void main(String[] args) throws IOException {
        // grid = new int[9][9];
        // BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // for (int i = 0; i < grid.length; i++) {
        // String[] inputs = bf.readLine().split(" ");
        // for (int j = 0; j < inputs.length; j++) {
        // grid[i][j] = Integer.parseInt(inputs[j]);
        // }
        // }
        // dfs(0, 0);
        // bf.close();
        // Boj2580_02 s = new Boj2580_02();
        // s.main();
        // Boj2580_03 s = new Boj2580_03();
        Boj258_04 s = new Boj258_04();
        s.main();
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

// 392ms
class Boj2580_02 {
    static int[][] grid;

    public void main() throws IOException {
        grid = new int[9][9];
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < grid.length; i++) {
            String[] inputs = bf.readLine().split(" ");
            for (int j = 0; j < inputs.length; j++) {
                grid[i][j] = Integer.parseInt(inputs[j]);
            }
        }
        solve(0);
        System.out.println("done");
        StringBuffer sb = new StringBuffer();
        for (int[] g : grid) {
            for (int x : g) {
                sb.append(x + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public boolean solve(int cur) {
        if (cur == 81)
            return true;
        int row = cur / 9;
        int col = cur % 9;
        if (grid[row][col] != 0)
            return solve(cur + 1);
        boolean[] flag = new boolean[10];
        validCheck(row, col, flag);
        for (int i = 1; i < 10; i++) {
            if (flag[i]) {
                grid[row][col] = i;
                if (solve(cur + 1)) {
                    return true;
                }
            }
        }
        grid[row][col] = 0;
        return false;
    }

    public void validCheck(int row, int col, boolean[] flag) {
        Arrays.fill(flag, true);
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] != 0)
                flag[grid[row][i]] = false;
            if (grid[i][col] != 0)
                flag[grid[i][col]] = false;
            int r = (row / 3) * 3 + i / 3;
            int c = (col / 3) * 3 + i % 3;
            if (grid[r][c] != 0)
                flag[grid[r][c]] = false;
        }
    }
}

// 500ms
class Boj2580_03 {
    static int[][] grid;

    public void main() throws IOException {
        grid = new int[9][9];
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < grid.length; i++) {
            String[] inputs = bf.readLine().split(" ");
            for (int j = 0; j < inputs.length; j++) {
                grid[i][j] = Integer.parseInt(inputs[j]);
            }
        }
        solve(0);

        System.out.println("done");

        StringBuffer sb = new StringBuffer();
        for (int[] g : grid) {
            for (int x : g) {
                sb.append(x + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public boolean solve(int n) {
        if (n == 81)
            return true;
        int row = n / 9;
        int col = n % 9;
        if (grid[row][col] != 0) {
            return solve(n + 1);
        } else {
            for (int i = 1; i < 10; i++) {
                if (isValid(row, col, i)) {
                    grid[row][col] = i;
                    if (solve(n + 1))
                        return true;
                    grid[row][col] = 0;
                }
            }
            return false;
        }
    }

    public boolean isValid(int row, int col, int val) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == val ||
                    grid[i][col] == val ||
                    grid[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == val) {
                return false;
            }
        }
        return true;
    }
}

// 488ms
class Boj258_04 {
    static int[][] grid;

    public void main() throws IOException {
        grid = new int[9][9];
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < grid.length; i++) {
            String[] inputs = bf.readLine().split(" ");
            for (int j = 0; j < inputs.length; j++) {
                grid[i][j] = Integer.parseInt(inputs[j]);
            }
        }
        solve(0, 0);

        System.out.println("done");

        StringBuffer sb = new StringBuffer();
        for (int[] g : grid) {
            for (int x : g) {
                sb.append(x + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public boolean solve(int row, int col) {
        if (col >= 9) {
            col = 0;
            row += 1;
        }
        if (row == 9) {
            return true;
        }
        if (grid[row][col] != 0)
            return solve(row, col + 1);
        for (int i = 1; i < 10; i++) {
            if (validCheck(row, col, i)) {
                grid[row][col] = i;
                if (solve(row, col + 1)) {
                    return true;
                } else {
                    grid[row][col] = 0;
                }
            }
        }
        return false;
    }

    public boolean validCheck(int row, int col, int val) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == val ||
                    grid[i][col] == val ||
                    grid[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == val)
                return false;
        }
        return true;
    }
}