public class Rotate {
    class Solution {
        public void rotate1(int[] nums, int k) {
            k %= nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }

        public void reverse(int[] nums, int start, int end) {
            while (start <= end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }

        public void rotate2(int[] nums, int k) {
            int n = nums.length;
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                result[(i + k) % n] = nums[i];
            }
            System.arraycopy(result, 0, nums, 0, nums.length);
        }
    }

}
