public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = { -1, 0, 3, 5, 9, 12 };
        int target = 9;
        int answer = looping(nums, nums.length / 2, target);
        System.out.println(answer);
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
