
// package leetCodeAlgorithmLearn;
import java.util.*;

public class Testing {
  public static void main(String[] args) throws Exception {
    Solution s = new Solution();
    System.out.println(s.pivotIndex(new int[] { 2, 1, -1 }));
  }
}

class Solution {
  public int pivotIndex(int[] nums) {
    int total = Arrays.stream(nums).sum();
    int sum = 0;
    for (int i = 0; i < nums.length; sum += nums[i++]) {
      if (sum * 2 == total - nums[i])
        return i;
    }
    return -1;
  }
}