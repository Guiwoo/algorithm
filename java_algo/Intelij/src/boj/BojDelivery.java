package boj;

import java.util.LinkedList;
import java.util.Queue;

public class BojDelivery {
    class Solution2222 {
        class DeliveryMan{
            int row;
            int col;
            int tip;
            int time;
            public DeliveryMan(int row, int col, int tip, int time) {
                this.row = row;
                this.col = col;
                this.tip = tip;
                this.time = time;
            }
        }
        public int Solution(int r,int[][] delivery){
            int[] dirs = {0,1,0,-1,0};
            Queue<DeliveryMan> q = new LinkedList<>();
            boolean[][][] visit = new boolean[r][r][2];
            int highest = 0;
            q.offer(new DeliveryMan(0,0,0,0));

            while(!q.isEmpty()){
                DeliveryMan poll = q.poll();
                int idx = poll.row * r + (poll.col % r);
                if(poll.time > r*r) continue;

                if(poll.time <= delivery[idx][0] && !visit[poll.row][poll.col][0]){
                    poll.tip+= delivery[idx][1];
                    visit[poll.row][poll.col][0] = true;
                }
                highest = Math.max(poll.tip, highest);

                for (int i = 1; i < dirs.length; i++) {
                    int nRow = dirs[i-1];
                    int nCol = dirs[i];

                    if(nRow < 0 || nCol < 0 || nRow >= r || nCol >= r){
                        continue;
                    }
                    q.offer(new DeliveryMan(nRow,nCol,poll.tip,poll.time+1));
                }
            }
            return highest;
        }

    }

}
