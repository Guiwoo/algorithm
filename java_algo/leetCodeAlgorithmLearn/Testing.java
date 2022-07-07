import java.util.*;

public class Testing {
  public static void main(String[] args) throws Exception {
    // Combination c = new Combination();
    // c.combi(4, 2);
    Solution s = new Solution();
    List<List<Integer>> ls = s.combine(4, 2);
    for (List<Integer> x : ls) {
      System.out.println(x);
    }
  }
}

class Solution {
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> ans = new LinkedList();
    if (n < k || k == 0)
      return ans;

    ans = combine(n - 1, k - 1);

    if (ans.isEmpty())
      ans.add(new LinkedList<Integer>());
    for (List<Integer> list : ans) {
      list.add(n);
      System.out.println(list);
    }
    ;

    ans.addAll(combine(n - 1, k));
    for (List<Integer> list : ans) {
      System.out.println("=======");
      System.out.println(list);
    }
    ;
    return ans;
  }
}

class Combination {

  public void combi(int n, int k) {
    int[] out = new int[k];
    helper(n, out, 0, 0);
  }

  public void helper(int n, int[] out, int depth, int start) {
    if (depth == out.length) {
      System.out.println(Arrays.toString(out));
      return;
    } else {
      for (int i = start; i < n; i++) {
        out[depth] = i + 1;
        helper(n, out, depth + 1, i + 1);
      }
    }
  }
}