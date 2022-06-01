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
        ArrayList<Integer> arr = new ArrayList<>();
        helper(root, 0, arr);
        System.out.println(arr);
        return arr.contains(targetSum);
    }

    public static void helper(TreeNode root, int curSum, ArrayList<Integer> arr) {
        if (root == null) {
            arr.add(curSum);
            return;
        }
        helper(root.left, curSum + root.val, arr);
        helper(root.right, curSum + root.val, arr);
        return;
    }
}
