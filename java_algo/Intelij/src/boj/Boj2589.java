package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2589 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bf.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);

        String[][] map = new String[N][M];
        for(int i = 0; i<N;i++){
            String[] s1 = bf.readLine().split("");
            map[i] = s1;
        }
        int max = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j].equals("L") ){
                    max = Math.max(bfs(i, j, map, N, M),max);
                }
            }
        }
        System.out.println(max);
    }

    static int bfs(int r,int c,String[][] map,int N,int M){
        int[] dirs = {0,1,0,-1,0};

        int[][] visit = new int[N][M];

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c,0});
        int distance = 0;
        while(!q.isEmpty()){
            int[] poll = q.poll();
            int row = poll[0],col = poll[1],time = poll[2];
            if(visit[row][col] != 0) continue;
            if(row == r && col == c && time != 0) continue;
            visit[row][col] = time;
           distance =  Math.max(distance, time);
            for (int i = 1; i < dirs.length; i++) {
                int nRow = row + dirs[i-1];
                int nCol = col + dirs[i];

                if(nRow < 0 || nRow >= N || nCol < 0 || nCol >= M || map[nRow][nCol].equals("W")) continue;

                q.offer(new int[]{nRow,nCol,time+1});
            }
        }

        return distance;
    }
}
