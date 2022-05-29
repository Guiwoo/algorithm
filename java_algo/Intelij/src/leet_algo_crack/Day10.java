package leet_algo_crack;

import java.util.List;

public class Day10 {
    public static void main(String[] args){

    }
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    class Lt21 {
        public ListNode mergeTwoLists_ver2_recursion(ListNode a, ListNode b) {
            if(a == null) return b;
            if(b == null) return a;
            ListNode c = null;
            if(a.val <= b.val){
                c = a;
                c.next = mergeTwoLists_ver2_recursion(a.next, b);
            }
            else{
                c = b;
                c.next = mergeTwoLists_ver2_recursion(a, b.next);
            }
            return c;
        }
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode result = new ListNode(-1);
            ListNode cur = result;
            while(list1 != null && list2 != null){
                if(list1.val <= list2.val){
                    cur.next = list1;
                    list1 = list1.next;
                }else{
                    cur.next = list2;
                    cur = cur.next;
                    list2 = list2.next;
                }
            }
            if (list1 == null){
                cur.next = list2;
            }
            if(list2 == null){
                cur.next = list1;
            }
            return result.next;
        }
    }
    class Lt206{
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode tail = head.next;
            ListNode newHead = reverseList(tail);
            head.next = null;
            tail.next = head;
            return newHead;
        }
        public ListNode reverseList2(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode dummy = new ListNode(0);
            ListNode p = head, temp = null;
            while (p != null) {
                temp = p;
                p = p.next;
                temp.next = dummy.next;
                dummy.next = temp;
            }
            return dummy.next;
        }
    }
}