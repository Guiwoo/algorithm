package zerobase.codingTest_week_23;

import java.util.*;

public class CodingTest_week_23 {
    class Solution2 {
        public int[][] solution(int[][] s, int[][] t) {
            TreeMap<Integer,Integer> map = new TreeMap<>();
            List<Integer> answerList = new ArrayList<>();
            Set<Integer> filter = new HashSet<>();

            Deque<int[]> sQ = new ArrayDeque<>();
            for(int[] ss: s) sQ.offer(ss);
            Deque<int[]> tQ = new ArrayDeque<>();
            for(int[] tt:t) tQ.offer(tt);

            while(!sQ.isEmpty()&&!tQ.isEmpty()){
                int[] sPoll = sQ.poll();
                int[] tPoll = tQ.poll();

                int minNum = Math.min(sPoll[1],tPoll[1]);
                if(minNum == 0){
                    if(sPoll[1] != minNum){
                        sQ.offerFirst(sPoll);
                    }
                    if(tPoll[1] != minNum){
                        tQ.offerFirst(tPoll);
                    }
                    continue;
                }
                int multi = sPoll[0]*tPoll[0];
                map.put(multi, map.getOrDefault(multi,0)+minNum);
                if(filter.add(multi)){
                    answerList.add(multi);
                }

                if(sPoll[1] - minNum != 0){
                    sPoll[1]-=minNum;
                    sQ.offerFirst(sPoll);
                }
                if(tPoll[1]-minNum != 0){
                    tPoll[1]-=minNum;
                    tQ.offerFirst(tPoll);
                }
            }
            int[][] answer = new int[map.size()][2];
            int x = 0;
            for(Integer i : answerList){
                answer[x++] = new int[]{i,map.get(i)};
            }
            return answer;
        }
    }
    class Solution3{
        public int[][] solution(int[][] s, int[][] t) {
            int[][] answer = new int[s.length][t[0].length];

            for(int i=0; i<answer.length; i++){
                for(int j=0; j<answer[0].length; j++){
                    int sum=0;
                    for(int k=0; k<s[0].length; k++){
                        sum += s[i][k]*t[k][j];
                    }
                    answer[i][j] = sum;
                }
            }

            return answer;
        }
    }

    class Solution5 {
        char[][] work;
        int total = 0;
        public int solution(String[] words) {
            //words 의 갯수 만큼 배열을 만들어서 다찾아버려
            work = new char[words[0].length()][words[0].length()];
            //for loop depth 를 어케정하지 ? words의 length 에 따라서
            //콤비네이션으로 3개 뽑자.
            String[] out = new String[words[0].length()];
            boolean[] visit = new boolean[words.length];
            combination(words,visit,out,0,0);
            return total;
        }

        void combination(String[] words,boolean[] visit,String[] out,int start,int depth){
            if(depth==out.length){
                validate(out);
                return;
            }else{
                for (int i = start; i < words.length; i++) {
                    if(visit[i] != true){
                        visit[i] = true;
                        out[depth] = words[i];
                        combination(words,visit,out,i+1,depth+1);
                        visit[i]=false;
                    }
                }
            }
        }
        void validate(String[] s){
            String target = s[0];
            int cnt = 0;
            for (int i = 0; i < target.length(); i++) {
                for (int j = 1; j < s.length; j++) {
                    if(s[j].startsWith(String.valueOf(target.charAt(j)))){
                        cnt++;
                        continue;
                    }
                    if(s[j].endsWith(String.valueOf(target.charAt(j)))){
                        cnt++;
                        continue;
                    }
                }
            }
            if(cnt == target.length()){
                total++;
            }
        }
    }
}
