import java.util.*;

public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        TreeNode tn = new TreeNode(1, null, new TreeNode(2, new TreeNode(3, null,
                null), null));
        System.out.println(inorderTraversal(tn));
    }

    static public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    static public void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            helper(root.left, res);
            res.add(root.val);
            helper(root.right, res);
        }
    }

    public List<Integer> inorderTraversal_ver2(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }

    public List<Integer> inorderTraversal_ver3(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left == null && cur.right == null) {
                list.add(cur.val);
                continue;
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
            stack.push(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            cur.left = null;
            cur.right = null;
        }
        return list;
    }
}
