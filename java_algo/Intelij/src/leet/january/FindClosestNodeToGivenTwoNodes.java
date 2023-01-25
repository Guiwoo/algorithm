package leet.january;

import java.util.*;

public class FindClosestNodeToGivenTwoNodes {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.closestMeetingNode(new int[]{2,0,0},2,0);

    }
    static class Solution {
        List<List<Integer>> graph = new ArrayList<>();
        private void init(int[] edges){
            for (int i = 0; i <= edges.length; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < edges.length; i++) {
                if(edges[i] == -1) continue;
                graph.get(i).add(edges[i]);
//                graph.get(edges[i]).add(i);
            }
        }
        private int[] dijk(int start){
            int[] dp = new int[graph.size()-1];
            boolean[] visit = new boolean[graph.size()-1];
            Arrays.fill(dp,Integer.MAX_VALUE);
            PriorityQueue<int[]> q = new PriorityQueue<>((x,y)->x[1]-y[1]);
            dp[start] = 0;
            q.add(new int[]{start,0});
            while(!q.isEmpty()){
                int[] poll = q.poll();
                if(visit[poll[0]]) continue;
                visit[poll[0]] = true;
                for (int i = 0; i < graph.get(poll[0]).size(); i++) {
                    int next = graph.get(poll[0]).get(i);
                    if(visit[next]) continue;
                    if(dp[next] > poll[1] + 1){
                        dp[next] = poll[1] + 1;
                        q.offer(new int[]{next,dp[next]});
                    }
                }
            }
            return dp;
        }
        public int closestMeetingNode(int[] edges, int node1, int node2) {
            init(edges);
            int[] dpA = dijk(node1);
            int[] dpB = dijk(node2);
            System.out.println(Arrays.toString(dpA));
            System.out.println(Arrays.toString(dpB));
            int idx = -1;
            int m = Integer.MAX_VALUE;
            for (int i = dpA.length-1; i >= 0; i--) {
                int target = Math.max(dpA[i],dpB[i]);
                if(m>=target && target != Integer.MAX_VALUE){
                    idx = i;
                    m = target;
                }
            }
            System.out.println(idx);
            return idx;
        }
    }
    static class Solution2{
        private int[] bfs(int start,int[] edges){
            int[] dp = new int[edges.length];
            Arrays.fill(dp,Integer.MAX_VALUE);
            boolean[] visit = new boolean[edges.length];
            Queue<Integer> q = new LinkedList<>();
            q.offer(start);
            dp[0] = 0;
            while(!q.isEmpty()){
                int poll = q.poll();
                if(visit[poll]) continue;
                visit[poll] = true;

                int next = edges[poll];
                if(next != -1 && !visit[next]){
                     dp[next] = 1+dp[poll];
                     q.offer(next);
                }
            }
            return dp;
        }

        public int closestMeetingNode(int[] edges, int node1, int node2) {
            int[] dpA= bfs(node1,edges);
            int[] dpB = bfs(node2,edges);

            int idx = -1, minValue = Integer.MAX_VALUE;
            for (int i = 0; i < dpA.length; i++) {
                if(minValue > Math.max(dpA[i],dpB[i])){
                    idx = i;
                    minValue = Math.max(dpA[i],dpB[i]);
                }
            }
            return idx;
        }
    }
}
