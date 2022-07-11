import java.util.Arrays;

public class Testing {
  public static void main(String[] args) throws Exception {
    int[] arr = new int[] { 0, 1, 0, 3, 2, 3 };
    System.out.println(lengthOfLIS(arr));
  }

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