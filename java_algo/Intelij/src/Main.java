import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.util.*;
/// 야 이 문제 보면 어떤 풀이 방법이 떠오름 ?\
// 그냥 완탐 가지치기 흠 bfs dfs 큰 차이는 없을 것도 같음
// 메모라이즈를 해도 되긴 할텐데 좀 까다롭긴하다
// 지금 범위가 50 정도 밖에 안되니간 완탐 ?
// 이게 bfs 로 푼사람도 있고 그리디로 푼사라돔 있고, dfs 도 있어서 궁금해서
// 시발 어디서 흰트를 얻어서 풀어야 할지를 모르겠음
//dfs 가지치기하다가 걍 오버플로 계속 떠가지고 멘탈나감

public class Main {
    public static void main(String[] args) throws IOException {
        Solution s = new Solution();
        int[][] maze ={
                {0,0,0,0},
                {1,1,1,0},
                {1,0,0,0},
                {0,0,0,0},
                {0,1,1,1},
                {0,1,0,0},
        };
        int[][] maze2 = {
                {0,0,0},
                {1,1,0},
                {0,0,0},
                {0,1,1},
                {0,1,0}
        };
        int[] height = {2,1,5,6,2,3};
        System.out.println(s.solution(height));
    }
}
class Solution {
    int[] heights;
    int Max_H = 10000;
    public int solution(int[] heights) {
        this.heights = heights;
        return helper(0,heights.length-1);
    }
    int helper(int left,int right){
        if(left == right) return heights[left];
        int mid = left+(right-left)/2;
        int ans = Math.max(helper(left,mid),helper(mid+1,right));

        int lpos = mid,rpos = mid+1;
        int minH = Max_H+1;
        while(left<=lpos && rpos<=right){
            minH = Math.min(minH,Math.min(heights[lpos],heights[rpos]));
            ans =  Math.max(ans,(rpos-lpos+1)*minH);
            if(left == lpos)rpos++;
            else if(right == rpos) lpos--;
            else if(heights[lpos-1] <= heights[rpos+1]) rpos++;
            else lpos--;
        }
        System.out.println(ans);
        System.out.println("=====");
        return ans;
    }

    int stakcWay(int[] heights){
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                int h = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i-1-stack.peek();
                maxArea = Math.max(maxArea , width*h);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int h = heights[stack.pop()];
            int width = stack.isEmpty() ? heights.length : heights.length-1-stack.peek();
            maxArea = Math.max(maxArea , width*h);
        }
        return maxArea;
    }
}
class Solution33{
    class Node{
        int count;
        int[] status;
        public Node(int count,int[] status){
            this.count = count;
            this.status = status;
        }
    }
    public int[][] getAdj(int[] s){
        int N = s.length;
        int[][] ret = new int[N][];
        for (int i = 0; i < N; i++) {
            int[] t = new int[N];
            System.arraycopy(s,0,t,0,N);

            int l = -1,r = 1;
            if( i == 0) l = 0;
            if( i == N-1) r = 0;

            for(int k=l;k<=r;k++){
                t[i+k] = s[i+k] == 0 ? 1 : 0;
            }
            ret[i] = t;
        }
        return ret;
    }
   public int solution(int[] status) {
        Set<String> visited = new HashSet<>();
        visited.add(Arrays.toString(status));

        if(Arrays.stream(status).min().getAsInt() == 1)return 0;

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,status));
        while(!q.isEmpty()){
            Node cur = q.poll();
            int cnt = cur.count;
            int[] stat = cur.status;

            for(int[] nextStat : getAdj(stat)){
                String newStaus = Arrays.toString(nextStat);
                if(visited.contains(newStaus)) continue;
                if(Arrays.stream(nextStat).min().getAsInt() == 1)return cnt+1;
                visited.add(newStaus);
                q.offer(new Node(cnt+1,nextStat));
            }
        }
        return -1;
    }
}