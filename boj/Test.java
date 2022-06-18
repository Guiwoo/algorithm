
import java.util.*;
import java.io.*;

public class Test {
    static int[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        grid = new int[10][10];
        for (int i = 1; i < grid.length; i++) {
            String[] line = bf.readLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                grid[i][j + 1] = Integer.parseInt(line[j]);
            }
        }
        dfs(0, 0);

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

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
            System.out.println(sb);

            System.exit(0);
        }
        if (grid[row][col] == 0) {
            for (int i = 1; i < 10; i++) {
                if (checkNUm(row, col, i)) {
                    grid[row][col] = i;
                    dfs(row, col + 1);
                }
            }
            grid[row][col] = 0;
            return;
        }
        dfs(row, col + 1);
    }

    public static boolean checkNUm(int row, int col, int val) {
        // column check
        for (int i = 1; i < 10; i++) {
            if (grid[i][col] == val)
                return false;
        }
        // rwo check
        for (int i = 1; i < 10; i++) {
            if (grid[row][i] == val)
                return false;
        }
        // 3*3 check
        int startRow = row < 4 ? 1 : row < 7 ? 4 : 7;
        int startCol = col < 4 ? 1 : col < 7 ? 4 : 7;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (grid[i][j] == val)
                    return false;
            }
        }
        return true;
    }
}

class BojMissed {
    int minAnswer;

    public void sol(int[] arr) {
        minAnswer = Integer.MIN_VALUE;
        boolean[] visit = new boolean[arr.length];
        int[] out = new int[arr.length];
        perMutation(arr, visit, out, 0);
        System.out.println(minAnswer);
    }

    public void perMutation(int[] arr, boolean[] visit, int[] out, int depth) {
        if (depth == out.length) {
            minAnswer = Math.max(minAnswer, cal(out));
        }
        for (int i = 0; i < arr.length; i++) {
            if (visit[i] != true) {
                visit[i] = true;
                out[depth] = arr[i];
                perMutation(arr, visit, out, depth + 1);
                visit[i] = false;
            }
        }

    }

    public int cal(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            result += Math.abs(arr[i] - arr[i + 1]);
        }
        return result;
    }

}