package day3;

public class Day3{
    // solve again;
}
class Lt283{
    public void moveZeroes(int[] nums) {
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }

        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

class Lt189{

    public int[] rotate(int[] nums,int k){
        k %= nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
        return nums;
    }
    public void reverse(int[] nums,int start,int end){
        while(start<=end){
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start ++;
            end --;
        }
    }

    public void rotate_2(int[] nums,int k){
        int[] result = new int[nums.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = nums[(i+k)%nums.length];
        }
        System.arraycopy(result,0,nums,0,nums.length);
    }
}
