import java.io.*;
import java.util.Arrays;

public class Testing {
  public static void main(String[] args) throws Exception {
    Solution s = new Solution();
    int[] arr = { 4, 5, 6, 7, 0, 1, 2 };
    s.search(arr, 0);
  }
}

class Solution {
  public int search(int[] nums, int target) {
    // target exist checking
    int left = 0;
    int right = nums.length - 1;
    int idx = -1;
    while (left <= right) {
      if (nums[left] == target) {
        idx = left;
        break;
      } else {
        left++;
      }
      if (nums[right] == target) {
        idx = right;
        break;
      } else {
        right--;
      }
    }
    return idx;
  }
}