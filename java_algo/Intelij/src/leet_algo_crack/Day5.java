package leet_algo_crack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Day5 {
    class Lt35 {
        public int searchInsert(int[] nums, int target) {
            int left = 0;
            int right = nums.length-1;
            int mid = left + (right-left)/2;
            while(left<=right){
                if(nums[mid]>target){
                    right = mid-1;
                }else if(nums[mid] == target){
                    break;
                }else{
                    left = mid+1;
                }
                mid = left+(right-left)/2;
            }
            return mid;
        }
    }
    class Lt852{
        public void peakIndexInAMountain(){
            int[] arr = {3,5,3,2,0};
            int left = 0;
            int right = arr.length-1;
            int mid = left +(right-left)/2;
            while(left<=right && mid>0){
                if(arr[mid] > arr[mid-1]){
                    if(arr[mid] >arr[mid+1]){
                        break;
                    }else{
                        left = mid+1;
                    }
                }else{
                    if(arr[mid+1]>arr[mid]){
                        break;
                    }else{
                        right = mid-1;
                    }
                }
                mid = left+(right-left)/2;
            }
            System.out.println(mid);
        }
    }
    class ListNode{
        int val;
        ListNode next;
        ListNode(){};
        ListNode(int val){this.val =val;}
        ListNode(int val, ListNode next){this.val = val;this.next=next;}
    }
    class Lt876{
        public ListNode middleNode(ListNode head) {
            ListNode ln = new ListNode();
            ArrayList<Integer> arr = new ArrayList<Integer>();
            while(head.next != null){
                arr.add(head.val);
                head = head.next;
            }
            arr.add(head.val);
            int mid = arr.size()/2;
            ListNode cur = ln;
            for(int i=mid;i<arr.size()-1;i++){
                ListNode newLn = new ListNode();
                cur.val = arr.get(i);
                cur.next = newLn;
                cur = newLn;
            }
            cur.val = arr.get(arr.size()-1);
            return ln;
    }
    }

     class Lt19{
         public ListNode removeNthFromEnd(ListNode head, int n) {
             ListNode dummy = new ListNode(0);
             dummy.next = head;
             ListNode slow = dummy;
             ListNode fast = head;

             for (int i = 0; i<n; i++){
                 fast= fast.next;
             }
             while(fast != null){
                 fast = fast.next;
                 slow= slow.next;
             }
             slow.next = slow.next.next;

             return dummy.next;
         }
    }
}

