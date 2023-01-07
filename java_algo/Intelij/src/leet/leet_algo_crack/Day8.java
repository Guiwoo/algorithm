package leet.leet_algo_crack;

import java.util.LinkedList;
import java.util.Queue;

public class Day8 {
    public static void main(String[] args){

    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    class Lt617{
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if(t1 == null) return t2;
            if(t2 == null) return t1;
            TreeNode result = new TreeNode(t1.val+t2.val);
            result.left = mergeTrees(t1.left,t2.left);
            result.right =mergeTrees(t1.right,t2.right);
            return result;
        }
    }
    class Lt116{
        public Node connect(Node root) {
            Queue<Node> curr = new LinkedList<Node>();
            Queue<Node> next = new LinkedList<Node>();
            curr.add(root);
            while(!curr.isEmpty()){
                Node node = curr.poll();
                if(node != null && node.left != null)next.add(node.left);
                if(node != null && node.right != null)next.add(node.right);
                if(curr.isEmpty()){
                    if(node != null)node.next = null;
                    curr = next;
                    next = new LinkedList<Node>();
                }else{
                    node.next = curr.peek();
                }
            }
            return root;
        }
    }
    class Node{
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}