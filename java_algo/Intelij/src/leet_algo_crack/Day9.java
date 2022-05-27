package leet_algo_crack;

import java.util.*;

public class Day9 {
    public static void main(String[] args){
        Lt994_Orange lt = new Lt994_Orange();
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(lt.orangesRotting(grid));
    }
    static class Lt542_BFS{
        public int[][] updateMatrix(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;
            boolean[][] visited = new boolean[row][col];

            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if(matrix[i][j] == 0){
                        queue.offer(new int[] {i,j});
                        visited[i][j] = true;
                    }
                }
            }
            int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            while(!queue.isEmpty()){
                int[] cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int theRow = cur[0]+dir[i][0];
                    int theCol = cur[0]+dir[i][1];
                    if (theRow < 0 || theRow >= row || theCol < 0 || theCol >= col || visited[row][col]) {
                        continue;
                    }
                    visited[row][col] = true;
                    matrix[row][col] = matrix[cur[0]][cur[1]] + 1;
                    queue.offer(new int[]{theRow,theCol});
                }
            }
            return matrix;
        }
    }
    static class Lt542_BFS_Ver2{
        public int[][] updateMatrix(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;

            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j] == 0) {
                        queue.offer(new int[] {i, j});
                    }
                    else {
                        matrix[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
            int[][] dirs= {{-1,0},{1,0},{0,1},{0,-1}};
            while(!queue.isEmpty()){
                int[] cell = queue.poll();
                for(int[] d :dirs){
                    int r = cell[0] + d[0];
                    int c = cell[1] + d[1];
                    if(r<0||r>=rows || c<0||c>=cols||
                    matrix[r][c] <= matrix[cell[0]][cell[1]]+1){
                        continue;
                    }
                    queue.add(new int[]{r,c});
                    matrix[r][c] = matrix[cell[0]][cell[1]]+1;
                }
            }
            return matrix;
        }
    }
    static class Lt542_BFS_Ver3{
        int[] DIR = new int[]{0, 1, 0, -1, 0};
        public int[][] updateMatrix(int[][] mat) {
            int rows = mat.length,cols = mat[0].length;
            Queue<int[]> q = new ArrayDeque<>();
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    if(mat[i][j] == 0) q.offer(new int[]{i,j});
                    else mat[i][j] = -1;
                }
            }
            while(!q.isEmpty()){
                int[] curr = q.poll();
                int r = curr[0],c = curr[1];
                for (int i = 0; i < 4; i++) {
                    int nr = r+DIR[i],nc = c +DIR[i+1];
                    if(nr<0||nr>=rows||nc<0||nc== cols||mat[nc][nc]!=-1)continue;;
                    mat[nr][nc] = mat[r][c]+1;
                    q.offer(new int[]{nr,nc});
                }
            }
            return mat;
        }
    }
    static class Lt994_Orange{
        public int orangesRotting(int[][] grid) {
            int rows = grid.length;
            int cols = grid.length;
            Queue<int[]> q = new LinkedList<>();
            int freshCnt=0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if(grid[i][j] == 2){
                        q.offer(new int[]{i,j});
                    }else if(grid[i][j] == 1){
                        freshCnt++;
                    }
                }
            }
            if (freshCnt == 0)return 0;

            int[] dirs = {0,1,0,-1,0};
            int mins = 0;

            while(!q.isEmpty()){
                ++mins;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] curPos = q.poll();
                    for (int j = 0; j < 4; j++) {
                        int nr = curPos[0] +dirs[j];
                        int nc = curPos[1] + dirs[j+1];
                        if(nr < 0 || nc < 0 || nr >= rows || nc >= cols || grid[nr][nc] == 0 || grid[nr][nc] == 2) continue;
                        grid[nr][nc] = 2;
                        q.offer(new int[]{nr, nc});
                        mins--;
                    }
                }
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if(grid[i][j] == 1){
                        freshCnt = 1;
                        break;
                    }
                }
            }
            return freshCnt == 0 ? mins-1 : -1;
        }
        public int orangesRotting_ver1(int[][] grid) {
            if(grid == null || grid.length == 0) return 0;
            int rows = grid.length;
            int cols = grid[0].length;
            Queue<int[]> queue = new LinkedList<>();
            int count_fresh = 0;
            //Put the position of all rotten oranges in queue
            //count the number of fresh oranges
            for(int i = 0 ; i < rows ; i++) {
                for(int j = 0 ; j < cols ; j++) {
                    if(grid[i][j] == 2) {
                        queue.offer(new int[]{i , j});
                    }
                    else if(grid[i][j] == 1) {
                        count_fresh++;
                    }
                }
            }
            //if count of fresh oranges is zero --> return 0
            if(count_fresh == 0) return 0;
            int count = 0;
            int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
            //bfs starting from initially rotten oranges
            while(!queue.isEmpty()) {
                ++count;
                int size = queue.size();
                for(int i = 0 ; i < size ; i++) {
                    int[] point = queue.poll();
                    for(int dir[] : dirs) {
                        int x = point[0] + dir[0];
                        int y = point[1] + dir[1];
                        //if x or y is out of bound
                        //or the orange at (x , y) is already rotten
                        //or the cell at (x , y) is empty
                        //we do nothing
                        if(x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || grid[x][y] == 2) continue;
                        //mark the orange at (x , y) as rotten
                        grid[x][y] = 2;
                        //put the new rotten orange at (x , y) in queue
                        queue.offer(new int[]{x , y});
                        //decrease the count of fresh oranges by 1
                        count_fresh--;
                    }
                }
            }
            return count_fresh == 0 ? count-1 : -1;
        }
        public int orangesRotting_ver2(int[][] grid) {
            Queue<int[]> qu = new LinkedList<>();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 2) {
                        qu.offer(new int[]{i, j});
                    }
                }
            }

            int[][] direction = {{1,0}, {0,1}, {0,-1}, {-1, 0}};

            int level = 0;
            while (!qu.isEmpty()) {
                level++;
                int size = qu.size();
                for (int i = 0; i < size; i++) {
                    int[] rotten = qu.poll();
                    for (int[] dir : direction) {
                        int nx = dir[0] + rotten[0];
                        int ny = dir[1] + rotten[1];

                        if (nx < 0 || ny < 0 || nx >= grid.length || ny >= grid[0].length || grid[nx][ny] != 1)
                            continue;

                        grid[nx][ny] = 2;
                        qu.offer(new int[]{nx, ny});
                    }
                }
            }

            for (int[] x : grid) {
                for (int y : x) {
                    if (y == 1)
                        return -1;
                }
            }

            return level == 0 ? 0 : level - 1;
        }
    }
}
