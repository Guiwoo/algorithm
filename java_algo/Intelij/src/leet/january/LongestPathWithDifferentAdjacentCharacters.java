package leet.january;

import java.util.*;

public class LongestPathWithDifferentAdjacentCharacters {

    public static void main(String[] args) {
        Solution s = new Solution();
        int aabc = s.longestPath(new int[]{-1,0,0,0}, "aabc");
        System.out.println(aabc);
    }

    static class Solution {
        class Point{
            int point;
            String path;

            public Point(int point, String path) {
                this.point = point;
                this.path = path;
            }
            public String toString(){
                return this.point +" "+ this.path;
            }
        }
        private List<List<Integer>> graph = new ArrayList<>();
        int answer = 0;
        public int longestPath(int[] parent, String s) {
            init(parent, s);
            boolean[] visit = new boolean[s.length()];
            dfs(new Point(0,""),visit, s);
            return answer;
        }

        private int dfs(Point p,boolean[] visit,String s) {
            if(visit[p.point]) return 1;
            int max = 0, secondMax = 0;
            visit[p.point] = true;
            p.path += s.charAt(p.point);
            for (int i = 0; i < graph.get(p.point).size(); i++) {
                int num = dfs(p, visit,s);

                int next = graph.get(p.point).get(i);
                char c = s.charAt(next);
                char c1 = p.path.charAt(p.path.length() - 1);
                if(visit[next] || c == c1) continue;
                if(num > max){
                    secondMax = max;
                    max = num;
                }else if ( num > secondMax){
                    secondMax = num;
                }
            }
            answer = Math.max(max,secondMax);
            return max+1;
        }

        private void init(int[] parent, String s) {
            for (int i = 0; i < parent.length; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < parent.length; i++) {
                if(parent[i] == -1) continue;
                graph.get(i).add(parent[i]);
                graph.get(parent[i]).add(i);
            }

        }
    }
}
