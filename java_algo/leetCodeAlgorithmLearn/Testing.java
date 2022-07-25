
// package leetCodeAlgorithmLearn;
import java.util.*;

public class Testing {
  public static void main(String[] args) throws Exception {
    Solution s = new Solution();
    System.out.println(s.pivotIndex(new int[] { 1, 7, 3, 6, 5, 6 }));
  }
}

class Solution {
  public int pivotIndex(int[] nums) {
    int total = Arrays.stream(nums).sum();
    int cur = 0;
    for (int i = 0; i < nums.length; i++) {
      if (cur == total - cur - nums[i])
        return i;
      cur += nums[i];
    }
    return -1;
  }
}