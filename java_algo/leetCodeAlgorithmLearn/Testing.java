import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class Testing {
    public static void main(String[] args) {
        IntersectionOfTwoArrays i = new IntersectionOfTwoArrays();
        int[] nums1 = { 4, 9, 5 };
        int[] nums2 = { 9, 4, 9, 8, 4 };
        i.intersect(nums1, nums2);
    }
}

class IntersectionOfTwoArrays {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList();
        Map<Integer, Integer> map = new HashMap<>();
        for (int r : nums1) {
            map.put(r, map.getOrDefault(r, 0) + 1);
        }

        for (int i = 0; i < nums2.length; i++) {
            int target = nums2[i];
            if (map.containsKey(target) && map.get(target) > 0) {
                map.put(target, map.get(target) - 1);
                result.add(target);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
