import java.util.*;

public class Testing {
    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 }
        };
        int[][] grid2 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
        int[][] grid3 = { { 0, 1 }, { 1, 1 } };
        System.out.println(maxAreaOfIsland(grid3));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // result = Math.max(result, helperBfs(grid, i, j));
                int get = helperDfs(grid, i, j);
                System.out.print(get + " ");
                result = Math.max(result, get);
            }
            System.out.println();
        }
        return result;
    }

    public static int helperDfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        } else {
            grid[i][j] = -1;
            int a = helperDfs(grid, i, j + 1); // 0 추가 // 0
            int b = helperDfs(grid, i + 1, j); // 1 추가 // 0
            int c = helperDfs(grid, i, j - 1); // 0 추가 // 1추가
            int d = helperDfs(grid, i - 1, j); // 0 추가 // 0추가 토탈 3;
            return 1 + a + b + c + d;
        }
    }

    public static int helperBfs(int[][] grid, int i, int j) {
        int result = 0;
        int[] directions = { 0, 1, 0, -1, 0 };
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { i, j });
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (grid[cur[0]][cur[1]] == 1) {
                result++;
                grid[cur[0]][cur[1]] = -1;
                for (int k = 1; k < directions.length; k++) {
                    int nRow = cur[0] + directions[k - 1];
                    int nCol = cur[1] + directions[k];
                    if (nCol < 0 || nRow < 0 || nCol >= grid[0].length || nRow >= grid.length
                            || grid[nRow][nCol] != 1) {
                        continue;
                    } else {
                        q.add(new int[] { nRow, nCol });
                    }
                }
            } else {
                continue;
            }
        }
        return result;
    }
}