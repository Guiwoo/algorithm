package leet.leet_algo_crack;
public class Day1{}
class Lt704{
    public int search(int[] nums, int target) {
        int answer = -1;
        int start= 0;
        int end = nums.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(nums[mid]<target){
                start = mid+1;
            }else if(nums[mid] == target){
                answer = mid;
                break;
            }else{
                end = mid-1;
            }
        }
        return answer;
    }
    public int recur(int[] nums,int start,int end, int target){
        if(start>end){
            return -1;
        }
        int mid = (start+end)/2;
        if(nums[mid]<target){
            return recur(nums,mid+1,end,target);
        }else if (nums[mid] == target){
            return mid;
        }else{
            return recur(nums,start,mid-1,target);
        }
    }
}
class Lt278{
    static int bad = 4;
    static boolean isBadVersion(int x){
        if(x<bad){
            return false;
        }
        return true;
    }
    public int recur(int n,int low,int high){
        if(low>high){
            return low;
        }
        int mid = low +(high-low)/2;
        if(isBadVersion(mid)){
            return recur(n,low,mid-1);
        }else{
            return recur(n,mid+1,high);
        }
    }
    public int firstBadVersion(int n){
        int low = 1;
        int high = n;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(isBadVersion(mid)){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return low;
    }
}
