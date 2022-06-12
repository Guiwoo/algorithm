import java.util.*;

public class Permutations {
    static List<List<Integer>> result;

    public static void main(String[] args) {
        permute(new int[] { 1, 2, 3 });
        System.out.println(result);
    }

    public static List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        boolean[] visit = new boolean[nums.length];
        int[] out = new int[nums.length];
        permutation(nums, visit, out, 0);
        return result;
    }

    static void permutation(int[] nums, boolean[] visit, int[] out, int depth) {
        if (depth == out.length) {
            result.add(new ArrayList(Arrays.stream(out).boxed().toList()));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visit[i] != true) {
                visit[i] = true;
                out[depth] = nums[i];
                permutation(nums, visit, out, depth + 1);
                visit[i] = false;
            }
        }
    }
}
