package zerobase.codingTest_week_23;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class CodingTest_week_23 {
    class Solution {
        public int solution(int L, int U, int D) {
            if(U >= L){
                return 1;
            }
            int cnt =1;
            int step = U-D;
            while(true){
                cnt++;
                if((step*cnt)+U >= L){
                    if(cnt == 2) return  cnt;
                    return cnt+1;
                }
            }
        }
    }

    class Solution2 {
        public int solution(String s, int N, int M) {
            String[] input = s.split(" ");
            int[] arr = new int[input.length];
            for (int i = 0; i < input.length; i++) {
                arr[i] = input[i].length();
            }

            for (int i = 1; i < arr.length; i++) {
                arr[i]+=arr[i-1];
            }

            int cnt = 0;
            int marker = 0;
            for (int i = 0; i < arr.length; i++) {
                int i1 = (marker == 0 ? arr[i] : arr[i] - arr[marker])+i;
                if(i1 > M){
                    cnt++;
                    marker = i;
                }
            }
            return N/cnt;
        }
    }

    class Solution3 {
        public int solution(int[] nums) {
            int oneCnt = 0;
            for (int num : nums) {
                if(num == 1) oneCnt++;
            }
            int sliding = 0;
            for (int i = 1; i <= nums.length; i++) {
                if(oneCnt*2 < i) continue;
                int i1 = sliding(i, nums) % 10007;
                sliding+=i1;
            }
            System.out.println(sliding);
            return sliding;
        }
        int sliding(int len,int[] nums){
            int oneCnt = 0;
            int zeroCnt = 0;
            int left = 0;
            int right = len;

            for (int i = 0; i < right; i++) {
                if(nums[i] == 0){
                    zeroCnt++;
                }else{
                    oneCnt++;
                }
            }
            int cnt = 0;
            while(right < nums.length && left <nums.length){
                if(oneCnt > zeroCnt){
                    cnt++;
                }

                int num = nums[left++];
                if(num == 0){
                    zeroCnt--;
                }else{
                    oneCnt--;
                }
                int num2 = nums[right++];
                if(num2 == 0){
                    zeroCnt++;
                }else{
                    oneCnt++;
                }
            }
            if(oneCnt > zeroCnt){
                cnt++;
            }
            return cnt;
        }
    }

    class Solution4 {
        public int solution(int[][] image, int x, int y) {
            int cnt = 0;
            for (int[] ints : image) {
                for (int anInt : ints) {
                    if(anInt == 1) cnt ++;
                }
            }

            int left =1001,right=0,top=1001,bottom=0;
            int[] dirs = {0,1,0,-1,0};
            int N = image.length;
            int M = image[0].length;

            if(cnt+M >= N) return N*M;
            boolean[][] visit = new boolean[N][M];


            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{x,y});
            while(!q.isEmpty()){
                int[] poll = q.poll();
                int row = poll[0],col = poll[1];
                if(visit[row][col]) continue;
                if(row < top) top = row;
                if(row > bottom) bottom = row;
                if(col < left) left = col;
                if(col >right) right = col;
                visit[row][col] = true;

                for(int i=1;i<dirs.length;i++){
                    int nRow = row + dirs[i];
                    int nCol = col + dirs[i-1];

                    if(nRow<0 || nCol < 0 || nRow >= N || nCol >= M || visit[nRow][nCol] || image[nRow][nCol] == 0) continue;

                    q.offer(new int[]{nRow,nCol});
                }
            }
            return (right - left + 1) * (bottom - top + 1);
        }

    }

    class Solution5 {
        public int solution(int hungry, int price, int[] plastic, int[] chain) {
            Queue<int[]> q = new PriorityQueue<>((x, y) ->y[0]*y[1] - x[0]*x[1]);
            for (int i = 0; i < chain.length; i++) {
                q.offer(new int[]{plastic[i],chain[i]});
            }
            int total = 0;
            while(!q.isEmpty()){
                int[] poll = q.poll();
                if(poll[0] < hungry){

                }
            }
            return 0;
        }
    }
}
