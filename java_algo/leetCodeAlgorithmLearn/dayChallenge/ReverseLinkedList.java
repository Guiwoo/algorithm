package dayChallenge;

public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode result = reverList(head);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    static ListNode reverList(ListNode head) {
        ListNode newList = new ListNode();
        return helper(head, newList);
    }

    static ListNode helper(ListNode head, ListNode cur) {
        if (head == null)
            return cur;
        ListNode curVal = new ListNode(head.val);
        curVal.next = cur;
        return helper(head.next, curVal);
    }
}