package leet.january;

import java.util.*;

public class NumberOfNodesInTheSubTreeWithTheSameLabel {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 7;
        int[][] edges = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
        String labels = "abaedcd";

        s.countSubTrees(n,edges,labels);
    }

    static class Solution {

        private List<List<Integer>> graph = new ArrayList<>();
        private boolean[] visit;
        private int[] result;

        public int[] countSubTrees(int n, int[][] edges, String labels) {
            init(n, edges);
            helper(0,labels);
            return result;
        }
        private int[] helper(int node,String labels) {
            visit[node] = true;
            int[] count = new int[26];
            for(int next : graph.get(node)){
                if(!visit[next]){
                    int[] adj = helper(next,labels);
                    for (int i = 0; i < 26; i++) {
                        count[i] += adj[i];
                    }
                }
            }
            char c = labels.charAt(node);
            count[c -'a']++;
            result[node] = count [c-'a'];
            return count;
        }

        private void init(int n, int[][] edges) {
            visit = new boolean[n];
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
        }

    }
}
