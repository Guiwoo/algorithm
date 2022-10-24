import java.util.LinkedList;
import java.util.Queue;

public class Testing{
    public static void main(String[] args) {
        Solution2222 s2 = new Solution2222();
        int[][] d = {{1, 5}, {8, 3}, {4, 2}, {2, 3}, {3,1},{3,2},{4,2},{5, 2}, {4, 1}};
        int solution = s2.Solution(3, d);
        System.out.println(solution);

    }
}

class Solution{
    public int[] solution(int[] numbers,int[] start,int[] finish){
        int len = start.length;
        int[] answer = new int[len];
        int[] prefix = new int[numbers.length];
        prefix[0] = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            prefix[i] = numbers[i]+prefix[i-1];
        }

        for(int i=0; i< len;i++){
            int left = start[i];
            int right = finish[i];
            if(left == 0 || right == 0){
                answer[i] = prefix[0];
                continue;
            }
            answer[i] = prefix[right] - (prefix[left-1]);
        }
        return answer;
    }
}

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


