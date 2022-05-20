import java.util.Arrays;

public class SortedSquares {
    public int[] sortedSquares_other(int[] nums) {
        return Arrays.stream(nums).boxed().mapToInt((i) -> i * i).sorted().toArray();
    }

    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0)
            return new int[0];
        int[] result = new int[nums.length];
        int l = 0, r = nums.length - 1, cnt = nums.length - 1;
        while (l <= r) {
            int Ra = nums[r] * nums[r];
            int La = nums[l] * nums[l];

            if (Ra > La) {
                result[cnt] = Ra;
                r--;
            } else {
                result[cnt] = La;
                l++;
            }
            cnt--;
        }
        return result;
    }
}
