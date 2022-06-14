import java.io.IOException;
import java.util.*;

public class Testing {
    public static void main(String[] args) throws IOException {
        Solution s = new Solution();
        Node testCase = new Node(1, new Node(2, new Node(4), new Node(5), null),
                new Node(3, new Node(6), new Node(7), null), null);
        s.connect(testCase);
        Node.printPreorder(testCase);
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

    public static void printPreorder(Node head) {
        ArrayList<String> result = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(head);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                if (cur != null) {
                    result.add(Integer.toString(cur.val));
                    if (cur.next == null) {
                        result.add("#");
                    } else {
                        result.add(Integer.toString(cur.next.val));
                    }
                    if (cur.left != null)
                        q.add(cur.left);
                    if (cur.right != null)
                        q.add(cur.right);
                }
            }
        }
        System.out.println(result);
    }
};

class Solution {
    public Node connect(Node root) {
        if (root == null)
            return null;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Node prev = null;
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                if (prev == null) {
                    prev = cur;
                } else {
                    prev.next = cur;
                }
                if (cur.left != null)
                    q.add(cur.left);
                if (cur.right != null)
                    q.add(cur.right);
                prev = cur;
            }
        }
        return root;
    }
}