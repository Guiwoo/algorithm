package leet.january;

import java.util.ArrayList;
import java.util.List;

public class MinimumTimeToCollectAllApplesInATree {
    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
        List<Boolean> hasApple = List.of(false,false,true,false,true,true,false);
        int n = 7;
        Solution s = new Solution();
        s.minTime(n,edges,hasApple);
    }

    static class Solution {
        private List<List<Integer>> graph = new ArrayList<>();
        private void init(int n,int[][] edges){
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList());
            }
            for (int i = 0; i < edges.length; i++) {
                int from = edges[i][0],to = edges[i][1];

                graph.get(from).add(to);
                graph.get(to).add(from);
            }
        }
        public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
            init(n,edges);
            boolean[] visit = new boolean[n];
            return helper(0, hasApple, visit);
        }
        public int helper(int cur,List<Boolean> hasApple,boolean[] visit){
            visit[cur]  = true;
            int move = 0;

            for(int next : graph.get(cur)){
                if(visit[next]) continue;
                move += helper(next,hasApple,visit);
            }

            if(cur != 0 && (hasApple.get(cur) || move > 0)) move +=2;
            System.out.println(move +" "+  cur);
            return move;
        }
    }
}
