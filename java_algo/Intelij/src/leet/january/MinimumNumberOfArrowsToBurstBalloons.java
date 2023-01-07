package leet.january;

import java.util.*;

public class MinimumNumberOfArrowsToBurstBalloons {

    public static void main(String[] args) {
        Solution3 s3 = new Solution3();
        int[][] merge = {{1,100},{11,22},{1,11},{2,12}};
        int intervals = s3.eraseOverlapIntervals(merge);
        System.out.println(intervals);
    }
    static class Solution {
        public int findMinArrowShots(int[][] points) {
            PriorityQueue<int[]> q = new PriorityQueue<>(
                    (a,b) -> a[1]-b[1]
            );
            Arrays.stream(points).forEach(q::offer);

            int cnt = 0;

            while(!q.isEmpty()){
                int[] cur = q.poll();
                while(!q.isEmpty() && q.peek()[0] <= cur[1] &&  cur[1] <= q.peek()[1]){
                    q.poll();
                }
                cnt++;
            }
            return cnt;
        }
    }
    static class Solution2 {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals,(a,b)->a[0]-b[0]);

            List<int[]> answer = new ArrayList<>();

            int min = intervals[0][0];
            int max = intervals[0][1];

            for (int i = 1; i < intervals.length; i++) {
                if(min <= intervals[i][0] && intervals[i][0] <= max){
                    max = Math.max(intervals[i][1],max);
                }else{
                    answer.add(new int[]{min,max});
                    min = intervals[i][0];
                    max = intervals[i][1];
                }
            }
            answer.add(new int[]{min,max});
            return answer.toArray(new int[answer.size()][]);
        }
    }

    static class Solution3 {
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));

            int left = intervals[0][0];
            int right = intervals[0][1];
            int cnt = 0;

            for(int i=1;i<intervals.length;i++){
                if(left == intervals[i][0]) {
                    right = Math.min(intervals[i][1],right);
                    cnt++;
                    continue;
                }
                boolean a = left < intervals[i][0] && intervals[i][0] <right;
                boolean b = left < intervals[i][1] && intervals[i][1] < right;
                if(a || b){
                    cnt++;
                    continue;
                }
                left = intervals[i][0];
                right = intervals[i][1];
            }
            return cnt;
        }
    }
}
