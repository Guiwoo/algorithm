package binaryTreeLearn;

import java.util.ArrayList;

public class PathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode rLeft = new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null);
        TreeNode rRight = new TreeNode(8, new TreeNode(13), new TreeNode(4, null, new TreeNode(1)));
        root.left = rLeft;
        root.right = rRight;
        hasPathSum(new TreeNode(1, new TreeNode(2), null), 0);
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        return helper(root, targetSum);
    }

    public static boolean helper(TreeNode root, int curSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && curSum - root.val == 0)
            return true;
        return helper(root.left, curSum - root.val) || helper(root.right, curSum - root.val);
    }
}
