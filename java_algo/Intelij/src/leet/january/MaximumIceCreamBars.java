package leet.january;

import leet.TreeNode;

import java.util.Arrays;

public class MaximumIceCreamBars {
    public static void main(String[] args) {

    }

    class Solution {
        public int maxIceCream(int[] costs, int coins) {
            int answer = 0;
            Arrays.sort(costs);

            if(costs[0] > coins) return answer;

            int idx = 0;
            while(coins > 0 && idx < costs.length){
                if(costs[idx] <= coins){
                    answer++;
                    coins -= costs[idx++];
                }else{
                    break;
                }
            }
            return answer;
        }
    }

    class Solution2 {
        public TreeNode sortedArrayToBST(int[] nums) {
            return helper(nums,0,nums.length-1);
        }
        public TreeNode helper(int[] arr,int low,int high){
            if(low >high) return null;
            int mid = (low+high)/2;
            TreeNode node = new TreeNode(arr[mid]);
            node.left = helper(arr,low,mid-1);
            node.right = helper(arr,mid+1,high);
            return node;
        }
    }
}
