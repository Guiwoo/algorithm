package binaryTreeLearn;

import java.util.*;

public class LeverlOrderTraversal {
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        TreeNode tn = new TreeNode(3, new TreeNode(9, null, null), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        List<List<Integer>> rs = levelOrder(tn);
        System.out.println(rs);
    }

    static public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> rs = new ArrayList<>();
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                TreeNode node = q.poll();
                if (node != null) {
                    rs.add(node.val);
                    if (node.left != null) {
                        q.offer(node.left);
                    }
                    if (node.right != null) {
                        q.offer(node.right);
                    }
                }
            }
            result.add(rs);
        }
        return result;
    }
}
