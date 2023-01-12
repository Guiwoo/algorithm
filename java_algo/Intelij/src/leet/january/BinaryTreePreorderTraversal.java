package leet.january;

import jdk.jfr.MemoryAddress;
import leet.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
//        Solution s = new Solution();
//        TreeNode t = new TreeNode(1,null,new TreeNode(2,new TreeNode(3),null));
//        List<Integer> integers = s.preorderTraversal(t);
//        System.out.println(integers);
        TreeNode treeNode = null;
        System.out.println(Objects.equals(null,null));
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
