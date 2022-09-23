import java.util.*;
import java.util.stream.Collectors;

public class Testing{
    public static void main(String[] args) {

    }
}

class Solution1 {
    public int solution(int[] food) {
        int[] sorted = Arrays.stream(food)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        int count = 0;
        int currentFood = sorted[0];
        for (int i = 1; i < sorted.length; i++) {
            int newFood = sorted[i];
            if(newFood < currentFood){
                currentFood -= newFood;
                count += newFood;
            }else{
                count += currentFood;
                currentFood = newFood-currentFood;
            }
        }
        count += currentFood;
        return count;
    }
}
class Solution2{
    public int solution(int n) {
        int second = 0,third = 0,fifth=0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int m2 = 2*dp[second];
            int m3 = 3*dp[third];
            int m5 = 5*dp[fifth];

            dp[i] = Math.min(m2,Math.min(m3,m5));
            if(dp[i] == m2){
                second++;
            }
            if(dp[i] == m3){
                third++;
            }
            if(dp[i] == m5){
                fifth++;
            }
        }
        return dp[n-1];
    }
}
class Solution3{
    public String solution(int[][] points) {
        int x1 = points[0][0], y1 = points[0][1];
        int x2 = points[1][0], y2 = points[1][1];
        int x3 = points[2][0], y3 = points[2][1];

        int val = (x1*y2+x2*y3+x3*y1) - (x2*y1+x3*y2+x1*y3);

        if(val == 0) return "LINE";
        else if (val > 0) return "CCW";
        else return "CW";
    }
}
class Solution4{
    public int solution(int[] x,int[] y,int k){
        Deque<int[]> q = new ArrayDeque<>();
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < x.length; i++) {
            while(!q.isEmpty() && q.peek()[0] + k < x[i]){
                q.remove();
            }

            int diff = y[i] - x[i];
            int plus = y[i] + x[i];

            if(!q.isEmpty()){
                int eq = plus + q.peek()[1];
                ans = Math.max(ans, eq);
            }

            while(!q.isEmpty() && q.getLast()[1] < diff){
                q.removeLast();
            }

            q.add(new int[]{x[i],diff});
        }
        return ans;
    }
}
class Solution5{
    List<List<Integer>> graph;
    int[] dp;
    boolean[] visit;
    public int solution(int N,int[][] stones){
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] stone:stones){
            graph.get(stone[0]).add(stone[1]);
        }
        dp = new int[N];
        Arrays.fill(dp, -1);

        int count = 0;
        for (int i = 0; i < N; i++) {
            visit = new boolean[N];
            count += dfs(i);
        }
        return count;
    }

    int dfs(int node){
        if(visit[node]){
            return 0;
        }
        visit[node] = true;
        for(int i: graph.get(node)){
            if(dp[i] == -1 || dfs(dp[i]) == 1){
                dp[i] = node;
                return 1;
            }
        }
        return 0;
    }
}