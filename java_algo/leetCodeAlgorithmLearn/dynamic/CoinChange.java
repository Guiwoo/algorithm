package dynamic;

import java.util.Arrays;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < dp.length; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }

    public static int coinChange2(int[] coins, int amount) {
        var dp = new int[amount + 1];
        Arrays.fill(dp, 1, dp.length, amount + 1);
        Arrays.sort(coins);

        for (var currAmt = 1; currAmt <= amount; currAmt++)
            for (var i = 0; i < coins.length && coins[i] <= currAmt; i++)
                dp[currAmt] = Math.min(dp[currAmt], 1 + dp[currAmt - coins[i]]);

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChange3(int[] coins, int amount) {
        if (amount < 1)
            return 0;
        return helper(coins, amount, new int[amount]);
    }

    public int helper(int[] coins, int rem, int[] dp) {
        if (rem < 0)
            return -1;
        if (rem == 0)
            return 0;
        if (dp[rem - 1] != 0)
            return dp[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = helper(coins, rem - coin, dp);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        dp[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return dp[rem - 1];
    }
}
