import java.sql.Array;
import java.util.*;
public class Main {
    public static void main(String[] args) {
//        int[] start = {3, 2, 1};
//        int[] end = {12, 12, 12};
//        int[] price = {100, 200, 399};

        Solution s = new Solution();
//        int[][] maze = {{0,1,0}, {0, 2, 0}, {1, 0,0}};
//        System.out.println(s.solution(maze));
        int[] W = {6, 4, 5, 6, 8, 9, 10, 3};
        int[] V = {10, 4, 6, 8, 2, 8, 5, 6};
        s.solution(8,10,15,W,V);
    }
}

class SolutionBFS {
    int count;
    public int solution(int[][] maze) {
        int count = 0;
        int row = 0,col =0;
        int[] dirs = {0,1,0};
        boolean[][] visit = new boolean[maze.length][maze[0].length];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row,col,keyCheck(row,col,maze)});
        while(!q.isEmpty()){
            int[] pos = q.poll();
            visit[pos[0]][pos[1]] = true;
            System.out.println(Arrays.toString(pos));
            int key = pos[2];
            if(pos[0] == maze.length-1 && pos[1] == maze[0].length-1 && pos[2] != 0){
                count++;
            }
            for (int i = 1; i < dirs.length; i++) {
                int nRow = pos[0]+ dirs[i-1];
                int nCol = pos[1] + dirs[i];
                if(0<= nRow && 0<= nCol && nRow < maze.length && nCol < maze[0].length && maze[nRow][nCol] != 1 && visit[nRow][nCol] == false){
                    if(key == 2){
                        q.add(new int[]{nRow,nCol,key});
                    }else {
                        q.add(new int[]{nRow, nCol, keyCheck(nRow, nCol, maze)});
                    }
                }
            }
        }
        return count%1007;
    }
    public int keyCheck(int row,int col,int[][] maze){
        if(maze[row][col] == 2) return 2;
        return 0;
    }
}

class Solution {
    public int solution(int[] start, int[] end, int[] price) {
        int dp[] = new int[10001];
        int last = 0;
        int first = 0;
        PriorityQueue<Schedlue> q = new PriorityQueue<>();
        for (int i = 0; i < start.length; i++) {
            q.add(new Schedlue(start[i],end[i],price[i]));
        }
        while(!q.isEmpty()){
            Schedlue cur = q.poll();
            last = cur.end;
            for(int i=first;i<=cur.start;i++){
                dp[last] = Math.max(dp[last],dp[i]+cur.price);
            }
            first = cur.start;
        }

        return Math.max(dp[last],dp[last]);
    }
    class Schedlue implements Comparable<Schedlue>{
        int start;
        int end;
        int price;
        Schedlue(int start,int end, int price){
            this.start=start;
            this.end = end;
            this.price = price;
        }
        @Override
        public int compareTo(Schedlue o) {
            return this.start - o.start;
        }

        @Override
        public String toString() {
            return this.start+" "+this.end +" "+this.price;
        }
    }
}