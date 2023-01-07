package LeetCode75;

public class Day1 {
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    public int pivotIndex(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i + 1] = dp[i] + nums[i];
        }
        for (int i = 1; i < dp.length; i++) {
            if (dp[i - 1] == dp[nums.length] - dp[i]) {
                return i - 1;
            }
        }
        return -1;
    }

}
