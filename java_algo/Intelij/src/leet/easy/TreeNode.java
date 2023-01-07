package leet.easy;

class TreeNode {
    int val;
    leet.easy.TreeNode left;
    leet.easy.TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, leet.easy.TreeNode left, leet.easy.TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
