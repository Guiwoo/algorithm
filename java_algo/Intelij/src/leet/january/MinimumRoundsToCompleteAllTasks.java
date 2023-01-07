package leet.january;

import java.util.*;

public class MinimumRoundsToCompleteAllTasks {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] task = {2,2,3,3,2,4,4,4,4,4};
        int i = s.minimumRounds(task);
        System.out.println(i);
    }

    static class Solution {
        public int minimumRounds(int[] tasks) {
            Arrays.sort(tasks);
            int round = 0;
            int cnt = 1;

            for(int i=1;i<tasks.length;i++){
                if(tasks[i-1] != tasks[i]){
                    if(cnt < 2) return -1;
                    round += calculate(cnt);
                    cnt =1;
                }else{
                    cnt++;
                }
            }
            if(cnt > 2){
                round += calculate(cnt);
            }

            return round;
        }

        private int calculate(Integer value) {
            if(value%3 == 0) return value/3;
            return (value/3)+1;
        }
    }

}
