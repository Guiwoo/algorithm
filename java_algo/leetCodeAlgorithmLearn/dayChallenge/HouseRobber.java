package dayChallenge;

import java.util.Arrays;

public class HouseRobber {
    public static void main(String[] args) {
        Day13_01 d = new Day13_01();
        int[] nums = { 2 };
        System.out.println(d.rob(nums));
    }
}

class Day13_01 {
    public int rob(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }
        int[] arr = new int[nums.length];
        arr[0] = nums[0];
        arr[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 2] + nums[i] > arr[i - 1] ? arr[i - 2] + nums[i] : arr[i - 1];
        }
        System.out.println(Arrays.toString(arr));
        return Arrays.stream(arr).max().getAsInt();
    }

    // recursive way (top-down)
    public int rob2(int[] nums) {
        return rob2(nums, nums.length - 1);
    }

    public int rob2(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        return Math.max(rob2(nums, i - 2) + nums[i], rob2(nums, i - 1));
    }

    // recursive memo(top-down)
    int[] memo;

    public int rob3(int[] nums) {
        memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        return rob3(nums, nums.length - 1);
    }

    public int rob3(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] >= 0) {
            return memo[i];
        }
        int result = Math.max(rob3(nums, i - 2) + nums[i], rob3(nums, i - 1));
        memo[i] = result;
        return result;
    }

    public int rob4(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] memo = new int[nums.length + 1];
        memo[0] = 0;
        memo[1] = nums[0];
        for (int i = 1; i < memo.length; i++) {
            int val = nums[i];
            memo[i + 1] = Math.max(memo[i], memo[i - 1] + val);
        }
        return memo[nums.length];
    }

    public int rob5(int[] nums) {
        if (nums.length == 0)
            return 0;
        int prev1 = 0;
        int prev2 = 0;
        for (int num : nums) {
            int tmp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = tmp;
        }
        return prev1;
    }
}
