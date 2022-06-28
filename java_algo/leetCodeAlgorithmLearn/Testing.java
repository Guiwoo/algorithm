import java.util.*;

public class Testing {
  public static void main(String[] args) {
    Solution s = new Solution();
    ListNode n = new ListNode(3);
    ListNode n1 = new ListNode(2);
    ListNode n2 = new ListNode(0);
    ListNode n3 = new ListNode(-4);
  }
}

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}

class Solution {
  public boolean hasCycle(ListNode head) {
    Map<ListNode, Integer> map = new HashMap<>();
    ListNode cur = head;
    int idx = 0;
    if (cur == null)
      return false;
    while (!map.containsKey(cur) && cur != null) {
      map.put(cur, idx);
      idx++;
      cur = cur.next;
    }
    if (map.containsKey(cur))
      return true;
    return false;
  }
}