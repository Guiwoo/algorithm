package dayChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRight {
    public static void main(String[] args) {
        Node n = new Node(1, new Node(2, new Node(4), new Node(5), null), new Node(3, new Node(6), new Node(7), null),
                null);
        helper(n);
    }

    public void connect(Node root) {
        if (root == null)
            return;
        helper(root.left, root.right);
    }

    void helper(Node node1, Node node2) {
        if (node1 == null)
            return;
        node1.next = node2;
        helper(node1.left, node1.right);
        helper(node2.left, node2.right);
        helper(node1.right, node2.left);
    }

    static Node helper(Node n) {
        Queue<Node> curr = new LinkedList<>();
        Queue<Node> next = new LinkedList<>();
        curr.offer(n);
        while (!curr.isEmpty()) {
            Node node = curr.poll();
            if (node != null && node.left != null)
                next.add(node.left);
            if (node != null && node.right != null)
                next.add(node.right);
            if (curr.isEmpty()) {
                if (node != null)
                    node.next = null;
                curr = next;
                next = new LinkedList<>();
            } else {
                node.next = curr.peek();
            }
        }
        return n;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root != null)
            queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            Node previous = null;
            for (int i = 0; i < n; i++) {
                Node node = queue.poll();
                if (i != 0)
                    previous.next = node;
                previous = node;
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
        return root;
    }
};
