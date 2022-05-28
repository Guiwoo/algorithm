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
}
