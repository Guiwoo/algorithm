import java.util.*;
public class Test {

    public static void main(String[] args) {
        int[][] grid = {{}};
        int x = orangesRotting(grid);
        for (int[] g:grid) {
            for(int gr:g){
                System.out.print(gr +" ");
            }
            System.out.println();
        }
        System.out.println(x);
    }
    public static int orangesRotting(int[][] grid){
        int[] dirs = {0,1,0,-1,0};
        int timer = 0;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 2){
                    q.offer(new int[]{i,j});
                }
            }
        }
        while(!q.isEmpty()){
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int[] rottenPos = q.poll();
                for (int i = 1; i < dirs.length; i++) {
                    int nRottenRow = rottenPos[0] + dirs[i-1];
                    int nRottenCol = rottenPos[1] + dirs[i];
                    if(nRottenRow<0 || nRottenCol<0 || nRottenRow>= grid.length || nRottenCol >= grid[0].length ||
                            grid[nRottenRow][nRottenCol] == 0 || grid[nRottenRow][nRottenCol] == 2){
                        continue;
                    }else{
                        if(grid[nRottenRow][nRottenCol] == 1){
                            grid[nRottenRow][nRottenCol] = 2;
                            q.add(new int[]{nRottenRow,nRottenCol});
                        }
                    }
                }
            }
            timer++;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1){
                    timer = -1;
                    break;
                }
            }
        }
        return timer == -1 ? -1 : timer == 0 ? 0 : timer-1;
    }
}