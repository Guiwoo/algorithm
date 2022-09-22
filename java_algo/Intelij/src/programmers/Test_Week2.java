package programmers;

import java.util.*;

public class Test_Week2{
    public static void main(String[] args) {
        Solution1 s = new Solution1();
        int[] num1 = {-2,3,0,2,-5};
        int[] num2 = {-3,-2,-1,0,1,2,3};
//        System.out.println(s.solution(num2));

        Solution2 s2= new Solution2();
        int[] t1 = {1,2,1,3,1,4,1,2};
        int[] t2 = {1,2,3,1,4};
        s2.solution(t2);
//        System.out.println(s2.solution(t1));

        Solution3 s3 = new Solution3();
        int n = 5;
        int[][] roads = {{1,2},{1,4},{2,4},{2,5},{4,5}};
        int[] sources = {1,3,5};
        int destination = 5;
        s3.solution(n,roads,sources,destination);
    }
}

class Solution1 {
    int answer = 0;
    public int solution(int[] number) {
        int[] out = new int[3];
        boolean[] visit = new boolean[number.length];
        comb(number,out,visit,0,0);
        return answer;
    }

    void comb(int[] nums,int[] out,boolean[] visit,int start,int depth){
        if(depth == out.length){
            if(isZeroCheck(out)){
                answer++;
            }
            return;
        }else{
            for (int i = start; i < nums.length; i++) {
                if(!visit[i]){
                    visit[i] = true;
                    out[depth] = nums[i];
                    comb(nums,out,visit,i,depth+1);
                    visit[i] = false;
                }
            }
        }
    }
    boolean isZeroCheck(int[] out){
        int total = Arrays.stream(out).sum();
        if(total == 0) return true;
        return false;
    }
}
class Solution2 {
    public int solution(int[] topping) {
        int answer = 0;
        HashMap<Integer, Integer> topping1 = new HashMap<>();
        HashMap<Integer, Integer> topping2 = new HashMap<>();

        Arrays.stream(topping).forEach(i -> {
            topping2.merge(i, 1, Integer::sum);
        });

        for (int i = 0; i < topping.length; i++) {
            if(topping1.get(topping[i]) == null) {
                topping1.put(topping[i], 1);
            } else {
                topping1.put(topping[i], topping1.get(topping[i]) + 1);
            }
            if (topping2.get(topping[i]) - 1 == 0) {
                topping2.remove(topping[i]);
            } else {
                topping2.put(topping[i], topping2.get(topping[i]) - 1);
            }

            if(topping1.size() == topping2.size()) answer++;
        }

        return answer;
    }
}
class Solution3 {
    List<ArrayList<int[]>> graph =new ArrayList<>();
    int[] dp;
    void init(int n,int[][] roads){
        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] road:roads){
            graph.get(road[0]).add(new int[]{road[1],1});
            graph.get(road[1]).add(new int[]{road[0],1});
        }
    }
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        init(n,roads);
        dp = new int[n+1];
        Arrays.fill(dp,1<<30);
        djikstra(n,0,destination);
        for (int i = 0; i < sources.length; i++) {
            if(sources[i] == destination){
                answer[i] =0;
                continue;
            }
            answer[i]=dp[sources[i]] == 1<<30 ? -1 : dp[sources[i]];
        }
        return answer;
    }
    int[] djikstra(int n, int from,int dest){
        boolean[] visit = new boolean[n+1];
        Queue<int[]> q = new PriorityQueue<>(
                Comparator.comparingInt(x -> x[1])
        );
        q.add(new int[]{dest,0});
        dp[dest] = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(visit[cur[0]]) continue;
            visit[cur[0]] = true;
            for (int i = 0; i < graph.get(cur[0]).size(); i++) {
                int[] next = graph.get(cur[0]).get(i);
                if(visit[next[0]]) continue;
                int newDist = cur[1] + next[1];
                if(dp[next[0]] > newDist){
                    dp[next[0]] = newDist;
                    int[] target = new int[]{next[0],dp[next[0]]};
                    q.offer(target);
                }
            }
        }
        return dp;
    }
}
