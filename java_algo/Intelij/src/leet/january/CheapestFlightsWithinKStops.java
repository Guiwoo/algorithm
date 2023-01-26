package leet.january;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] arr = {{0,1,5},{1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}};
        int cheapestPrice = s.findCheapestPrice(5, arr, 0, 2, 2);
        System.out.println("cheapestPrice = " + cheapestPrice);
    }

    static class Solution {
        List<List<int[]>> graph = new ArrayList<>();
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            init(n, flights);
            PriorityQueue<int[]> q = new PriorityQueue<>(
                    (x,y)-> x[1] - y[1]
            );
            int[] dp = new int[n];
            boolean[] visit = new boolean[n];
            Arrays.fill(dp,-1);
            dp[src] = 0;
            // start,distance,move point
            q.offer(new int[]{src,0,0});
            while(!q.isEmpty()){
                int[] poll = q.poll();
                int cur = poll[0],dist = poll[1],move = poll[2];

                for (int i = 0; i < graph.get(cur).size(); i++) {
                    int[] arr = graph.get(cur).get(i);
                    int next = arr[0],price = arr[1];
                    int total = price + dist;
                    if(dp[next] == -1 || total < dp[next]){
                        dp[next] = total;
                    }
                    if( k != move){
                        q.offer(new int[]{next,total,move+1});
                    }
                }
            }
            return dp[dst];
        }

        private void init(int n, int[][] flights) {
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for(int[] flight : flights){
                graph.get(flight[0]).add(new int[]{flight[1],flight[2]});
            }
        }
    }
}
