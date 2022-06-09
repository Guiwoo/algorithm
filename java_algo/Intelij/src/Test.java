import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
        int[] nums = {1,2};
        int[][] opeartions = {{1,3},{2,1},{3,2}};
        int[] arr=arrayChange(nums,opeartions);
        System.out.println(Arrays.toString(arr));
    }
    public static int[] arrayChange(int[] nums, int[][] operations) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        for(int[] x : operations){
            int idx = map.get(x[0]);
            nums[idx] = x[1];
            map.put(x[1],idx);
        }
        return nums;
    }
}