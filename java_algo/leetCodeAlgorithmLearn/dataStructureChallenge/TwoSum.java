package dataStructureChallenge;

import java.util.HashMap;

class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                int idx = map.get(target - nums[i]);
                result[0] = i < idx ? i : idx;
                result[1] = result[0] == i ? idx : i;
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }
}
