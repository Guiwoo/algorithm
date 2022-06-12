import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Combinations {
    static List<List<Integer>> result;

    public static void main(String[] args) {
        combine2(4, 2);
        System.out.println(result);
    }

    public static List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> ans = new LinkedList();
        if (n < k || k == 0)
            return ans;

        ans = combine2(n - 1, k - 1);
        if (ans.isEmpty())
            ans.add(new LinkedList<Integer>());
        for (List<Integer> list : ans)
            list.add(n);

        ans.addAll(combine2(n - 1, k));
        return ans;
    }

    public static List<List<Integer>> combine(int n, int k) {
        result = new ArrayList<>();
        int[] nums = IntStream.range(1, n + 1).toArray();
        int[] out = new int[k];
        boolean[] visit = new boolean[nums.length];
        combination(nums, visit, out, 0, 0);
        return result;
    }

    static void combination(int[] nums, boolean[] visit, int[] out, int start, int depth) {
        if (depth == out.length) {
            ArrayList<Integer> output = new ArrayList<>();
            output.addAll(Arrays.stream(out).boxed().toList());
            result.add(output);
            return;
        } else {
            for (int i = start; i < nums.length; i++) {
                if (visit[i] != true) {
                    visit[i] = true;
                    out[depth] = nums[i];
                    combination(nums, visit, out, i + 1, depth + 1);
                    visit[i] = false;
                }
            }
        }
    }
}
