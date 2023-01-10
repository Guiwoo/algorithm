package leet.january;

import leet.TreeNode;

public class SameTree {
    public static void main(String[] args) {
        Solution s = new Solution();
        boolean sameTree = s.isSameTree(
                new TreeNode(1, new TreeNode(2),new TreeNode(1)),
                new TreeNode(1, new TreeNode(1), new TreeNode(2))
                );
        System.out.println("sameTree = " + sameTree);
    }
    static class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            return helper(p,q);
        }
        public boolean helper(TreeNode p,TreeNode q){
            if(p == null || q == null)
                return  p==q;
            if(p.val != q.val) return false;
            return helper(p.left,q.left) && helper(p.right,q.right);
        }
    }
}
