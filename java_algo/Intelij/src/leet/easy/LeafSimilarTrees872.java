package leet.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LeafSimilarTrees872 {
 static class TreeNode {
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

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> ans1 = new ArrayList();
        List<Integer> ans2 = new ArrayList();

        helper(root1,ans1);
        helper(root2,ans2);
        if(ans1.size() != ans2.size()) return false;
        for(int i=0;i<ans1.size();i++){
            if(!Objects.equals(ans1.get(i), ans2.get(i))) return false;
        }
        return true;
    }
    public void helper(TreeNode node,List<Integer> ans){
        if(node == null) return;
        if(node.left == null && node.right == null){
            ans.add(node.val);
            return;
        }
        helper(node.left,ans);
        helper(node.right,ans);
    }

}
