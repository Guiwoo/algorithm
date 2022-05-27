import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreOrderTraversal {
    public static void main(String[] args) {
        TreeNode tn = new TreeNode(1, null, new TreeNode(2, new TreeNode(3, null, null), null));
        List<Integer> rs = preorderTraversal(tn);
        System.out.println(rs);
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