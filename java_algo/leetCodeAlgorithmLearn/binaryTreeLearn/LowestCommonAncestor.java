package binaryTreeLearn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LowestCommonAncestor {
    public static void main(String[] args) {
        int[] rs = rotate(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 3);
        for (int r : rs) {
            System.out.print(r + " ");
        }
    }

    static public int[] rotate(int[] nums, int k) {
        if (nums.length == 1)
            return nums;
        k = k % nums.length;
        System.out.println(k);
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, (nums.length - 1));
        return nums;
    }

    static public void reverse(int[] arr, int start, int end) {
        System.out.println(start + " " + end);
        for (int i = 0; i <= (end - start) / 2; i++) {
            int tmp = arr[i + start];
            arr[i + start] = arr[end - i];
            arr[end - i] = tmp;
        }
    }

}

class Ts {
    public void ts(TreeNode root) {
        int pArrIdx = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> pArr = new ArrayList<>();
        int p = 5;
        int l = 4;
        recur(root, arr, pArr);
        for (int i = 0; i < arr.size(); i++) {
            map.put(arr.get(i), i);
        }
        // 맵에서 루트 벨류 가져와서 그걸 기준으로 왼쪽 오른쪽 판별
        // 저배열들 가지고 찾으면 되는거아님 ?
        while (true) {
            int headValue = pArr.get(pArrIdx++); // 3
            int arrIdx = map.get(headValue);
            int pIdx = map.get(p);
            int lIdx = map.get(l);
            if (pIdx <= arrIdx && lIdx > arrIdx) {
                // 리턴 헤드벨류
                System.out.println(headValue);
                break;
            } else if (pIdx < arrIdx && lIdx < arrIdx) {
                // 왼쪽으로 범위좁혀서 어떻게 ? pArrIdx
                System.out.println("왼쪽에 있다.");
            } else {
                // 오른쪽으로 범위좁혀서
                System.out.println("오른쪽에 있다.");
                pArrIdx = pArrIdx + arrIdx;
            }
        }

    }

    public void recur(TreeNode root, ArrayList<Integer> arr, ArrayList<Integer> pArr) {
        if (root == null) {
            return;
        }
        pArr.add(root.val);
        recur(root.left, arr, pArr);
        arr.add(root.val);
        recur(root.right, arr, pArr);
        return;
    }
}

class Solutions {

    private TreeNode ans;

    public Solutions() {
        // Variable to store LCA node.
        this.ans = null;
    }

    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {

        // If reached the end of a branch, return false.
        if (currentNode == null) {
            return false;
        }

        // Left Recursion. If left recursion returns true, set left = 1 else 0
        int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;

        // Right Recursion
        int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;

        // If the current node is one of p or q
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;

        // If any two of the flags left, right or mid become True
        if (mid + left + right >= 2) {
            this.ans = currentNode;
        }

        // Return true if any one of the three bool values is True.
        return (mid + left + right > 0);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Traverse the tree
        this.recurseTree(root, p, q);
        return this.ans;
    }
}