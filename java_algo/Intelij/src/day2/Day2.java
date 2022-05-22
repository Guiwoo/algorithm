package day2;

import java.util.Arrays;

public class Day2 {
}
class Lt35{
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(nums[mid]==target){
                start = mid;
                break;
            }else if (nums[mid]<target){
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        return start;
    }
    public int bs(int[] nums,int start,int end,int target){
        if(start>end){
            return start;
        }
        int mid = (start+end)/2;
        if(nums[mid] == target) {
            return mid;
        }else if(nums[mid]<target){
            return bs(nums,mid+1,end,target);
        }else{
            return bs(nums,start,mid+1,target);
        }
    }
}
class Lt977{
    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int cur = 0;

        while(left<=right){
            if(nums[left]*nums[left] < nums[right]*nums[right]){
                result[cur] = nums[right]*nums[right];
                right--;
            }else{
                result[cur] = nums[left]*nums[left];
                left++;
            }
            cur++;
        }

        Arrays.sort(result);
        return result;
    }
}
