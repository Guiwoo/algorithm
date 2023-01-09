package leet.january;

import leet.january.MaximumIceCreamBars.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode t = new TreeNode(1,null,new TreeNode(2,new TreeNode(3),null));
        List<Integer> integers = s.preorderTraversal(t);
        System.out.println(integers);
    }
    static class Solution {
        List<Integer> list = new ArrayList<>();
        public List<Integer> preorderTraversal(TreeNode root) {
            helper(root);
            return list;
        }
        public void helper(TreeNode root){
            if(root == null) return;
            list.add(root.val);
            helper(root.left);
            helper(root.right);
        }
    }
}
