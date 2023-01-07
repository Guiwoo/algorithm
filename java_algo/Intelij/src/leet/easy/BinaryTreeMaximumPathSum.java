package leet.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode node1 = new TreeNode(1, new TreeNode(-2), new TreeNode(3));
        TreeNode node2 = new TreeNode(-10,new TreeNode(9),
                new TreeNode(20,new TreeNode(15),new TreeNode(7)));
        TreeNode node3 = new TreeNode(-3);
        TreeNode node4 = new TreeNode(-2,new TreeNode(-1),null);
        int i = s.maxPathSum(node1);
        System.out.println("i = " + i);
        System.out.println();
        int i1 = s.maxPathSum(node2);
        System.out.println("i1 = " + i1);
        System.out.println();
        int i2 = s.maxPathSum(node3);
        System.out.println("i2 = " + i2);
        System.out.println();
        int i3 = s.maxPathSum(node4);
        System.out.println("i3 = " + i3);
    }

    static class Solution {
        int max;
        public int maxPathSum(TreeNode root) {
            max = Integer.MIN_VALUE;
            helper(root);
            return max;
        }

        public int helper(TreeNode node){
            if(node == null) return 0;
            int left = helper(node.left);
            int right = helper(node.right);

            int rightOrLeft = Math.max(left+node.val,right+node.val);
            int nodeLeftRight = node.val + left + right;
            int nodeAlone = node.val;
            int maxAll = Math.max(rightOrLeft,Math.max(nodeLeftRight,nodeAlone));

            max = Math.max(max,maxAll);
            return Math.max(nodeAlone, rightOrLeft);
        }
    }
}