package leet_algo_crack;

public class Day5 {
    class Lt35 {
        public int searchInsert(int[] nums, int target) {
            int left = 0;
            int right = nums.length-1;
            int mid = left + (right-left)/2;
            while(left<=right){
                if(nums[mid]>target){
                    right = mid-1;
                }else if(nums[mid] == target){
                    break;
                }else{
                    left = mid+1;
                }
                mid = left+(right-left)/2;
            }
            return mid;
        }
    }
    class Lt852{
        public void peakIndexInAMountain(){
            int[] arr = {3,5,3,2,0};
            int left = 0;
            int right = arr.length-1;
            int mid = left +(right-left)/2;
            while(left<=right && mid>0){
                if(arr[mid] > arr[mid-1]){
                    if(arr[mid] >arr[mid+1]){
                        break;
                    }else{
                        left = mid+1;
                    }
                }else{
                    if(arr[mid+1]>arr[mid]){
                        break;
                    }else{
                        right = mid-1;
                    }
                }
                mid = left+(right-left)/2;
            }
            System.out.println(mid);
        }
    }
}

