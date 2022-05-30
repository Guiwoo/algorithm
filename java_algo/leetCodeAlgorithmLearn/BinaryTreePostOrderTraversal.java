import java.util.*;

public class BinaryTreePostOrderTraversal {
    public static void main(String[] args) {

    }
}

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<Integer>();
        Deque<TreeNode> q = new LinkedList<TreeNode>();
        q.push(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.pop();
            if (cur.left == null && cur.right == null) {
                result.add(cur.val);
                continue;
            }
            q.offerLast(cur);
            if (cur.left != null) {
                q.push(cur.left);
                cur.left = null;
            }
            if (cur.right != null) {
                q.push(cur.right);
                cur.right = null;
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal_recur(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(root, list);
        return list;
    }

    public void postOrder(TreeNode node, List<Integer> list) {
        if (node != null) {
            postOrder(node.left, list);
            postOrder(node.right, list);
            list.add(node.val);
        }
    }
}
