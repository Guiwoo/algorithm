import java.lang.reflect.Array;
import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args){
        Lt283 a = new Lt283();
        
    }
}
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
class Lt283{
    // solve again;
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