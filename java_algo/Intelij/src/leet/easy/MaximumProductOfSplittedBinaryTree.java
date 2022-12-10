package leet.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximumProductOfSplittedBinaryTree {
    public static void main(String[] args) {
        Solution s = new Solution();
        int i = s.maxProduct(new TreeNode(1, new TreeNode(1), null));
        System.out.println(i);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    int total = 0;
    int module = (int) Math.pow(10,9)+7;
    public int maxProduct(TreeNode root) {
        List<Integer> sum = new ArrayList<>();
        int total = getTotalSum(root,sum);
        int max = 0;
        for (Integer target : sum) {
            int t = (target * (total - target))%module;
            max = Math.max(max,t);
        }
        return max;
    }

    public int getTotalSum(TreeNode node, List<Integer> sum){
        if(node == null) return 0;
        int left = getTotalSum(node.left,sum);
        int right = getTotalSum(node.right,sum);
        int total = (left + right)%module;
        sum.add(total);
        return total;
    }
}