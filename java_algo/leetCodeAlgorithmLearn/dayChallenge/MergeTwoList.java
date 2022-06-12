package dayChallenge;

public class MergeTwoList {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        list1 = null;
        list2 = new ListNode(0);
        ListNode mg = mergeTwoLists(list1, list2);
        while (mg.next != null) {
            System.out.print(mg.val + " ");
            mg = mg.next;
        }
    }

    static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode mergedNode = new ListNode();
        helper(list1, list2, mergedNode);
        return mergedNode.next;
    }

    static ListNode helper(ListNode list1, ListNode list2, ListNode merged) {
        if (list1 == null)
            return merged.next = list2;
        if (list2 == null)
            return merged.next = list1;
        if (list1.val > list2.val) {
            merged.next = list2;
            helper(list1, list2.next, merged.next);
        } else {
            merged.next = list1;
            helper(list1.next, list2, merged.next);
        }
        return merged;
    }
}