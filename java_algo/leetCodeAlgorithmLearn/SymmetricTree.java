public class SymmetricTree {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        System.out.println(helper(node.left, node.right));
    }

    public static boolean helper(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val) {
            return false;
        }
        return helper(left.left, right.right) && helper(left.right, right.left);
    }
}
