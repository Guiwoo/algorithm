package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Boj2178{
    public void main() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = bf.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]),M = Integer.parseInt(inputs[1]);
        int[][] map = new int[N][M];
        // 1 은 이동 가능 하고 , 9은 이동 할수없다.
        // 마지막 칸에 도착하는 최소의 경우의 수를 구하시오
        // 칸을 셀떄 시작 위치와 도착 위치를 포함한다.
        for (int i = 0; i < N; i++) {
            String[] input = bf.readLine().split("");
            for (int j = 0; j < input.length; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        int[][] visit = new int[N][M];
        visit[0][0] = 1;
        int[] dirs = {0,1,0,-1,0};

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r=cur[0],c=cur[1];
            if(r == N-1 && c == M-1){
                break;
            }
            for (int i = 1; i < dirs.length; i++) {
                int row = dirs[i] + r;
                int col = dirs[i-1]+c;
                if(row < 0 || col < 0 || row >= N || col >= M || map[row][col] == 0){
                    continue;
                }
                if(map[row][col] == 1 && visit[row][col] == 0){
                    q.offer(new int[] {row,col});
                    visit[row][col] = visit[r][c]+1;
                }
            }
        }
        System.out.println(visit[N-1][M-1]);
    }

}
