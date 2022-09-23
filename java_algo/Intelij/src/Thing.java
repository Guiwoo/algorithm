import java.util.*;

public class Thing {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.findPeakElement(new int[]{1,2,3,1});
    }
}
class Solution {
    public int findPeakElement(int[] nums) {
        return bs(nums,0,nums.length-1);
    }
    int bs(int[] nums,int l,int r){
        if(l==r) return l;
        int mid = (l+r)/2;
        if(nums[mid] >nums[mid+1]) return bs(nums,l,mid);
        return bs(nums,mid+1,r);
    }
}