import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class BinarySearch {
    public static void main(String[] args) {

    }

    public static int looping(int[] nums, int idx, int target) {
        if (idx < 3) {
            int val = -1;
            for (int i = idx; i > 0; i--) {
                if (nums[i] == target) {
                    val = i;
                    break;
                }
            }
            return val;
        } else if (nums.length - idx < 3) {
            int val = -1;
            for (int i = idx; i < nums.length; i++) {
                if (nums[i] == target) {
                    val = i;
                    break;
                }
            }
            return val;
        }
        if (nums[idx] < target) {
            return looping(nums, (idx + (nums.length)) / 2, target);
        } else if (nums[idx] > target) {
            return looping(nums, idx / 2, target);
        } else {
            return idx;
        }
    }
}
