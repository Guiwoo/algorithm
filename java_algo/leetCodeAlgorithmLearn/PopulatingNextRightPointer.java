import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointer {
    public static void main(String[] args) {
        Node n = new Node(1);
        Node left = new Node(2, new Node(4), new Node(5), null);
        Node right = new Node(3, null, new Node(7), null);
        n.left = left;
        n.right = right;
        helper(n);
        // Queue<Node> q = new LinkedList<>();
        // q.offer(get);
        // while (!q.isEmpty()) {
        // Node checker = q.poll();
        // if (checker != null) {
        // q.offer(checker.left);
        // q.offer(checker.right);
        // System.out.print(checker.val + " ");
        // if (checker.next != null) {
        // System.out.print(checker.next.val);
        // } else {
        // System.out.print(checker.next);
        // }
        // }
        // System.out.println();
        // }
    }

    static Node helper(Node root) {
        Queue<Node> q = new LinkedList<>();
        Queue<Node> next = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Node previous = null;
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                if (i != 0)
                    previous.next = node;
                previous = node;
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
        }
        return root;
    }

    public class Solution {
        public void connect(Node root) {
            if (root == null)
                return;
            // link root's child nodes
            link(root);
            // before we recurse to the next level
            // make sure all the child nodes of the nodes at current level are linked
            Node curr = root.next;
            while (curr != null) {
                link(curr);
                curr = curr.next;
            }
            connect(root.left);
            connect(root.right);
        }

        // helper function
        // link root node's left and right nodes
        void link(Node root) {
            if (root == null)
                return;

            if (root.left != null) {
                root.left.next = root.right != null ? root.right : getNext(root);
            }

            if (root.right != null) {
                root.right.next = getNext(root);
            }
        }

        // get the left most node at the next level
        Node getNext(Node node) {
            Node next = node.next;

            while (next != null) {
                if (next.left != null)
                    return next.left;
                if (next.right != null)
                    return next.right;
                next = next.next;
            }

            return null;
        }
    }
}