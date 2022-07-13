package leetCodeAlgorithmLearn;

import java.util.Arrays;

public class Testing {
  public static void main(String[] args) throws Exception {
    int[] arr = new int[] { 1, 2, 0, 0, 8 };
    System.out.println(rob(arr));
  }

  public static int rob(int[] nums) {
    int preMax = 0, curMax = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      int t = curMax;
      curMax = Math.max(preMax + nums[i], curMax);
      preMax = t;
    }
    return curMax;
  }

  public static int rob2(int[] nums) {
    int preMax = 0, curMax = 0;
    for (int i = 1; i < nums.length; i++) {
      int t = curMax;
      curMax = Math.max(preMax + nums[i], curMax);
      preMax = t;
    }
    return curMax;
  }
}