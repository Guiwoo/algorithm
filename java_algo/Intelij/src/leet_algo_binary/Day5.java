package leet_algo_binary;

import java.util.ArrayList;
import java.util.HashSet;

public class Day5 {
    public static void main(String[] args){
        Lt34 lt = new Lt34();
        int[] ranges ={5,7,7,8,8,10};
        int target = 8;
        int[] rs= lt.searchRange(ranges,target);
        for(int r : rs){
            System.out.print(r+" ");
        }
    }
    class Lt278{
        public boolean isBadVersion(int n){
            return n>100 ? true:false;
        }
        public int firstBadVersion(int n) {
            int start = 1;
            int end = n;
            while(start<=end){
                int mid = start+(end-start)/2;
                if(isBadVersion(mid)){
                    end = mid-1;
                }else{
                    start = mid+1;
                }
            }
            return start;
        }
    }
    static class Lt34{
        public int[] searchRange(int[] nums, int target) {
            if(nums.length == 0) return new int[]{-1,-1};
            if(nums.length == 1) return nums[0] == target ? new int[]{0,0} :new int[]{-1,-1};
            HashSet<Integer> result = new HashSet<>();

            int left = 0;
            int right = nums.length-1;
            int dir = 0;
            while(left<=right){
                int mid = left+(right-left)/2;
                if(nums[mid] == target){
                    result.add(mid);
                    if(dir == 0){

                    }else if(dir == 1){
                        left++;
                    }else{
                        right--;
                    }
                }else if(nums[mid]<target){
                    left = mid+1;
                    dir = 1;
                }else{
                    right = mid-1;
                    dir = -1;
                }
            }
            int[] rs= result.stream().mapToInt(x->Integer.valueOf(x)).toArray();
            return rs.length == 0 ? new int[]{-1,-1} : rs;
        }
        public int[] searchRange_ver2(int[] a, int target){

            int[] result = {-1, -1};

            if (a == null || a.length == 0)
                return result;

            result[0] = findStartPosition(a, target);
            result[1] = findEndPosition(a, target);

            return result;
        }
        public int findStartPosition(int[] a, int target){

            int left = 0;
            int right = a.length-1;
            int start =-1;

            while(left<= right){

                int mid = left+(right-left)/2;

                if (a[mid] == target){
                    start = mid; // this is start
                    right = mid-1; // lets see if there one more on the left
                }else if (target > a[mid]){
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }

            return start;
        }
        public int findEndPosition(int[] a, int target){
            int left = 0;
            int right = a.length-1;
            int end = -1;

            while(left <= right){
                int mid = left + (right-left)/2;

                if (a[mid] == target){
                    end = mid;	  // this is the end
                    left = mid+1; // lets see if there is one more on the right
                }else if (target > a[mid])
                    left = mid +1;
                else
                    right = mid -1;

            }

            return end;
        }
    }
}
