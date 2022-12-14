package leet.december;

public class HouseRobber {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.rob(new int[]{2,1,1,2});
    }
    static class  Solution {
        public int rob(int[] nums) {
            int[] dp = new int[nums.length+2];
            dp[0] = 0;
            dp[1] = 0;
            dp[2] = nums[0];
            for(int i=3;i<dp.length;i++){
                dp[i] = nums[i-2] + Math.max(dp[i-2],dp[i-3]);
            }
            return Math.max(dp[dp.length-1],dp[dp.length-2]);
        }
    }
}
