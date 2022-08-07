import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Thing{
    public static void main(String[] args) {
//        Solution33 s = new Solution33();
//        s.maximumGroups(new int[]{2,31,41,31,36,46,33,45,47,8,31,6,12,12,15,35});
        Solution34 s = new Solution34();
        int[] edges = {3,3,4,2,3};
        s.longestCycle(edges);
    }
}

class Solution34 {
    ArrayList<ArrayList<int[]>> list = new ArrayList<>();
    public void init(int[] edges){
        for (int i = 0; i < edges.length; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            if(edges[i] == -1) continue;
            list.get(i).add(new int[]{edges[i],1});
        }
    }
    public int longestCycle(int[] edges) {
        ArrayList<int[]> l = new ArrayList<>();
        init(edges);
        int[] dp = bellmanFord(edges.length);
        System.out.println(Arrays.toString(dp));
        return 0;
    }
    public int[] bellmanFord(int n){
        int[] dp = new int[n];
        boolean[] visit = new boolean[n];
        Arrays.fill(dp,1<<30);
        boolean isMinusCycle = false;
        dp[0] = 0;
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < list.size(); j++) {
                ArrayList<int[]> nexts = list.get(j);
                for(int[] next:nexts){
                    if(dp[j] != 1<<30 && dp[next[0]] > dp[j]+next[1]){
                        dp[next[0]] = dp[j]+next[1];

                        if(i == n){
                            isMinusCycle = true;
                        }
                    }
                }
            }
        }
        if(isMinusCycle){
            return new int[]{};
        }
        return dp;
    }
}