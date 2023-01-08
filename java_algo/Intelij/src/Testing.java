import java.io.IOException;
import java.util.*;
import java.util.stream.DoubleStream;

public class Testing{
    public static void main(String[] args) throws IOException {
       Solution s = new Solution();
        long l = s.maxKelements(new int[]{1, 10, 3, 3, 3}, 3);
        System.out.println(l);
    }
    static class Solution {
        public long maxKelements(int[] nums, int k) {
            long total = 0;
            PriorityQueue<Integer> q = new PriorityQueue(Collections.reverseOrder());
            for(int i=0;i<nums.length;i++){
                q.offer(nums[i]);
            }
            while(k>0){
                int x = q.poll();
                total += x;
                q.offer((int) Math.ceil((double) x/3));
                k--;
            }
            return total;
        }
        public int findIdx(int[] arr){
            int idx = 0;
            for(int i=1;i<arr.length;i++){
                if(arr[idx] < arr[i]) idx = i;
            }
            return idx;
        }
    }
}


