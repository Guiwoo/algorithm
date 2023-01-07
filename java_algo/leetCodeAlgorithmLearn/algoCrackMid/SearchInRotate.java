package algoCrackMid;

public class SearchInRotate {

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
