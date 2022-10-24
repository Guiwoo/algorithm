package codingTest_week_20;

import java.util.*;

public class CodingTest_week_20 {
    class Solution1 {
        Map<String,String> graph = new HashMap<>();
        Map<String, ArrayList<String>> graphReverse = new HashMap<>();
        void init(String[][] similarWords){
            for(String[] s : similarWords){
                //정방향
                graph.put(s[0],s[1]);
                //역방향
                if(!graphReverse.containsKey(s[1])){
                    graphReverse.put(s[1],new ArrayList<>());
                }
                graphReverse.get(s[1]).add(s[0]);
            }
        }
        public int solution(String s, String t, String[][] similarWords) {
            init(similarWords);
            String[] ss = s.split(" ");
            String[] tt = t.split(" ");
            int total = 0;
            for (int i = 0; i < ss.length; i++) {
                List<String> cur = new ArrayList<>();
                String s1 = graph.get(ss[i]);
                while(true){
                    if(s1 == null) break;
                    cur.add(s1);
                    s1 = graph.get(s1);
                }

                ArrayList<String> strings = graphReverse.get(tt[i]);

            }

            return total;
        }
    }
    class Solution2 {
        public int solution(int[][] x, int[][] y) {
            Queue<int[]> xQ = new LinkedList<>(Arrays.asList(x));
            Queue<int[]> yQ = new LinkedList<>(Arrays.asList(y));
            int total = 0;
            while(!xQ.isEmpty() && !yQ.isEmpty()){
                int xPeek = xQ.peek()[0];
                int yPeek = yQ.peek()[0];
                if(xPeek == yPeek){
                    var xPoll = xQ.poll();
                    var yPoll = yQ.poll();
                    total += xPoll[1]*yPoll[1];
                }else if(xPeek > yPeek){
                    yQ.poll();
                }else{
                    xQ.poll();
                }
            }
            return total;
        }
    }
    class Solution3 {
        public int solution(int[][] activity) {
            Queue<int[]> q = new PriorityQueue<>((x,y) -> x[0]-y[0]);
            for (int[] x : activity) {
                q.offer(x);
            }
            int total = 0;
            while(!q.isEmpty()){
                Queue<int[]> w = new PriorityQueue<>((x,y) -> x[0]-y[0]);
                int cur = 0;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] poll = q.peek();
                    if(cur <= poll[0]) cur = poll[1];
                    else{
                        w.add(poll);
                    }
                }
                total++;
                q=w;
            }
            return total;
        }
    }
    class Solution4 {
        List<String> list = new ArrayList<>(Arrays.asList("0", "1", "8"));
        char[][] pair = { {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'} };
        public String[] solution(int n) {
            List<String> dfs = dfs(n, n);
            Collections.sort(dfs);
            return dfs.toArray(new String[0]);
        }

        public List<String> dfs(int k,int n){
            if (k<=0){
                return new ArrayList<>(List.of(""));
            }
            if(k == 1){
                return list;
            }
            List<String> subList = dfs(k-2,n);
            List<String> result = new ArrayList<>();

            for(String str : subList){
                if( k!= n){
                    result.add("0"+str+"0");
                }
                for(char[] a : pair){
                    result.add(a[0]+str+a[1]);
                }
            }
            return result;
        }
    }
}
