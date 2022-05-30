import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreePreOrderTraversal {
    public static void main(String[] args) {
        // TreeNode tn = new TreeNode(1, null, new TreeNode(2, new TreeNode(3, null,
        // null), null));
        // List<Integer> rs = preorderTraversal(tn);
        // System.out.println(rs);
        int[][] flatten = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        int[] flatArr = Arrays.stream(flatten).flatMapToInt(Arrays::stream).toArray();
        for (int i : flatArr) {
            System.out.println(i);
        }
    }

    public List<Integer> preorderTraversal_myAnswer(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur != null) {
                result.add(cur.val);
                q.offer(cur.left);
                q.offer(cur.right);
            }
        }
        return result;
    }

    static public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        Stack<TreeNode> rights = new Stack<TreeNode>();
        while (root != null) {
            list.add(root.val);
            if (root.right != null) {
                rights.push(root.right);
            }
            root = root.left;
            if (root == null && !rights.isEmpty()) {
                root = rights.pop();
            }
        }
        return list;
    }

    static public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                result.add(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return result;
    }
}