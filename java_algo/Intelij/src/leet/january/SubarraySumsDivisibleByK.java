package leet.january;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumsDivisibleByK {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = {4,5,0,-2,-3,1};
        int[] arr2 = {5};
        int i = s.subarraysDivByK(arr2, 9);
        System.out.println(i);
    }
    static class Solution {
        public int subarraysDivByK(int[] nums, int k) {

            Map<Integer,Integer> map = new HashMap<>();
            map.put(0,1);
            int sum = 0;
            int result = 0;
            for(int a : nums){
                sum = (sum+a) % k;
                if(sum < 0) sum += k;
                if(map.containsKey(sum)){
                    result += map.get(sum);
                }
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
            return result;
        }
    }
}
