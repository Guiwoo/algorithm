import java.util.Arrays;

public class Test {
    public static void main(String[] args){
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        rotate2(nums,k);
        for (int r:nums) {
            System.out.print(r+" ");
        }
    }
    public static void rotate2(int[] nums,int k)  {
        int n = nums.length;
        int[] result = new int[n];
        for(int i=0;i<n;i++){
            result[(i+k)%n] = nums[i];
        }
        System.arraycopy(result,0,nums,0,nums.length);
//        for (int i = 0; i <nums.length ; i++) {
//            nums[i] = result[i];
//        }
    }
    public static void rotate(int[] nums, int k) {
        k %= nums.length; // why ? for the overflow
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while(start<=end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
