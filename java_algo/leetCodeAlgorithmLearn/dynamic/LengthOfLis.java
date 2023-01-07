package dynamic;

public class LengthOfLis {
    public static int lengthOfLIS(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        int rs = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[j - 1] < arr[i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            rs = Math.max(dp[i], rs);
        }

        return rs;
    }
}
