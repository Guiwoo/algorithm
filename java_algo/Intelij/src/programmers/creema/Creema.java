package programmers.creema;

import java.util.*;

public class Creema {
    public static void main(String[] args) {
        Sol1 s1 = new Sol1();
//        s1.getFinalString("AWAWSSG");
        Sol2 s2 = new Sol2();
//        long range = s2.findRange(1);
        Sol3 s3 = new Sol3();
//        int i = s3.maxLength(List.of(3, 1, 2, 1), 4);
//        System.out.println(i);
        Sol5 s5 = new Sol5();
        int i = s5.minimumTreePath(5, List.of(List.of(1, 2), List.of(2, 3), List.of(2, 4), List.of(1, 5)), List.of(3, 4));
        System.out.println(i);
    }
}

class Sol1{
    public String getFinalString(String s) {
        // Write your code here
        if(s.length() == 0) return "-1";
        int aws = s.indexOf("AWS");
        if(aws >= 0){
            String nextS = s.substring(0,aws) + s.substring(aws+3, s.length());
            return getFinalString(nextS);
        }
        return s;
    }
}

// 22892 =>11891 ~ 99891
// 32892 => 30890 ~ 39899
// 32892 => 12892 ~ 92892

class Sol2{
    public long findRange(int num) {
        long maxValue = Long.MIN_VALUE,minValue = Long.MAX_VALUE;
        String s = String.valueOf(num);
        for (int i = 0; i < s.length(); i++) {
            //1의 자리수 부터 전부 Loop 태워
            char c = s.charAt(i);
            for(int j=0;j<=9;j++){
                String next = s.replace(c,(char) ('0'+j));
                long nextValue =Long.valueOf(next);
                if(String.valueOf(nextValue).length() != s.length()) continue;
                maxValue = Math.max(maxValue,nextValue);
                minValue = Math.min(minValue,nextValue);
            }
        }
        System.out.println(maxValue+" "+minValue);
        return maxValue-minValue;
    }
}
class Sol3{
    public int maxLength(List<Integer> a, int k) {
        int left = 0;
        int subMax = a.get(0);
        int total = 0;
        for (int i = 1; i < a.size(); i++) {
            subMax += a.get(i);
            while(subMax > k){
                subMax -= a.get(left++);
            }
            total = Math.max(total,i-left+1);
        }
        return total;
    }
}

class Sol4{
    public int longestChain(List<String> words) {
        if(words == null || words.size() == 0) {
            return 0;
        }
        int res = 0;
        Collections.sort(words, (a, b)-> a.length()-b.length());
        HashMap<String, Integer> map = new HashMap();

        for(String w : words) {
            map.put(w, 1);

            for(int i=0; i<w.length(); i++) {

                StringBuilder sb = new StringBuilder(w);
                String next = sb.deleteCharAt(i).toString();

                if(map.containsKey(next) && map.get(next)+1 > map.get(w))
                    map.put(w, map.get(next)+1);
            }
            res = Math.max(res, map.get(w));
        }
        return res;
    }
}

class Sol5{
    class Node{
        int[] cur;
        String visit;

        public Node() {}

        public Node(int[] cur, String visit) {
            this.cur = cur;
            this.visit = visit;
        }
    }
    List<List<Integer>> graph = new ArrayList<>();
    private void init(int n, List<List<Integer>> edges){
        for(int i=0;i<n+1;i++){
            graph.add(new ArrayList<>());
        }
        for(List<Integer> edge : edges){
            graph.get(edge.get(0)).add(edge.get(1));
            graph.get(edge.get(1)).add(edge.get(0));
        }
    }

    public int minimumTreePath(int n, List<List<Integer>> edges, List<Integer> visitNodes) {
        init(n,edges);
        Queue<Node> q = new PriorityQueue<>((x,y)->x.cur[1]-y.cur[1]);
        q.offer(new Node(new int[]{1,0},""));
        while(!q.isEmpty()){
            Node poll = q.poll();
            int[] cur = poll.cur;
            poll.visit+= cur[0];
            boolean isMandatoryVisit = mandatoryVisit(poll, visitNodes);
            if(isMandatoryVisit && poll.visit.contains(String.valueOf(1)) && poll.visit.contains(String.valueOf(n))) return cur[1];
            for (int i = 0; i < graph.get(cur[0]).size(); i++) {
                int nextNode = graph.get(cur[0]).get(i);
                Node next = new Node(new int[]{nextNode,cur[1]+1},poll.visit);
                if(next.visit.length() > 4){
                    String substring = next.visit.substring(next.visit.length() - 4);
                    if(substring.charAt(0) == substring.charAt(2)
                            && substring.charAt(1) == substring.charAt(3)) continue;
                }
                if(next.cur[1] >= n*2 || (isMandatoryVisit && nextNode != n)) continue;
                q.offer(next);
            }
        }
        return 0;
    }
    private static boolean mandatoryVisit(Node poll,List<Integer> visitNodes){
        for(Integer visitNode : visitNodes){
            if(!poll.visit.contains(String.valueOf(visitNode))) return  false;
        }
        return true;
    }
}