package leet.january;

import java.util.List;

public class FindTheTownJudge {
    public static void main(String[] args) {
        Solution s1 = new Solution();
        s1.findJudge(3,new int[][]{{1,3},{2,3}});
    }
    static class Solution {
        public int findJudge(int n, int[][] trust) {
            int[] a = new int[n+1];
            int[] b = new int[n+1];

            int maxIdx = 1;
            for(int[] t : trust){
                // 투표수 저장 할것
                b[t[1]]++;
                if(b[maxIdx] < b[t[1]]) maxIdx = t[1];
                // 이 사람이 지지하는 투표수 저장할것
                a[t[0]]++;
            }
            if(b[maxIdx] != n-1) return -1;
            return a[maxIdx] > 0 ? -1 : maxIdx;
        }
    }
}
