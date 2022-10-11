import java.util.*;

public class Thing {
    public static void main(String[] args) {
        Solution232 s3 = new Solution232();
        long beforeTime = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            System.out.println(i+" => " + s3.sol(i));
        }
        long afterTime = System.currentTimeMillis();

        System.out.println("소요사간(m) : " + (afterTime-beforeTime)/1000);
    }
}
class Solution232 {
    private static long answer;
    int[] dp = {6,2,5,5,4,5,5,3,7,5};
    long[] answers = new long[101];
    public long sol(int n){
        for (int i = 2; i <= n; i++) {
            answer = 0;
            go(i,i);
            answers[i] = answer;
        }
        return answer;
    }

    private void go(int remain,int j) {
        if (remain == 0) {
            answer++;
            return;
        }
        for (int i=0; i<dp.length; i++) {
            if (remain >= dp[i]){
                if(remain < j){
                    answer+= answers[remain];
                    return;
                }
                go(remain - dp[i],j);
            }
        }
    }
}