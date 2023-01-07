public class SearchInsert {

    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int last = nums.length - 1;
        while (start <= last) {
            int mid = (start + last) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] == target) {
                start = mid;
                break;
            } else {
                last = mid - 1;
            }
        }
        return start;
    }

    public int bs(int[] nums, int start, int end, int target) {
        if (start > end) {
            return start;
        }
        int mid = (start + end) / 2;
        return nums[mid] < target ? bs(nums, mid + 1, end, target) : bs(nums, start, mid - 1, target);
    }

    public int searchInsert_mv(int[] nums, int target){
        return bs(nums, 0, nums.length - 1, target);
    }
}