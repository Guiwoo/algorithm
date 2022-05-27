package leet_algo_crack;

import java.util.*;

public class Day9 {
    public static void main(String[] args){
        Queue<Integer> q = new LinkedList();
        q.offer(2);
        q.add(3);
        q.offer(4);
        System.out.println(q);
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
}
