package programmers;

import java.util.*;

public class CodingTestThreeDotThree {

    class Solution1 {
        public int[] solution(String[] students) {
            List<int[]> ans = new ArrayList<>();
            for(int i=0;i<students.length;i++){
                int A = 0,L=0,P=0;
                for(char x : students[i].toCharArray()){
                    if(x == 'A') A++;
                    else if(x == 'P')P++;
                    else L++;
                }
                ans.add(new int[]{i+1,cal(A,L,P)});
            }
            ans.sort((o1, o2) -> {
                if (o2[1] == o1[1]) {
                    return o1[0] - o2[0];
                }
                return o2[1] - o1[1];
            });
            for (int[] a : ans) {
                System.out.println(Arrays.toString(a));
            }
            return ans.stream().mapToInt(x->x[0]).toArray();
        }
        public int cal(int a,int l,int p){
            if(p >=3){
                return 0;
            } else if(p+(l/2) >= 3){
                return 0;
            }
            int total = a-(l/2)-p + 10;
            return Math.max(total,0);
        }
    }
    class Solution2 {
        public int[] solution(int[][] orders) {
            //투표횟수, 반장으로 당선된 학생의 출석번호;
            int total = orders.length,half = totalGet(orders),cnt = 0,winner = -1;
            Set<Integer> set = new HashSet<>();
            while(true){
                cnt++;
                //첫번째 투표 를 결과로 반환해주는 함수가 있으면 좋을듯
                Map<Integer, Integer> map = voteResult(orders, total,set);
                //가장 낮은 득표수, 가장 높은 득표수 , 득표수가 과반수가 아니라면 ? 탈락
                int maxIdx = 0,minIdx = 0,maxValue=Integer.MIN_VALUE,minValue = Integer.MAX_VALUE;
                for(Map.Entry<Integer,Integer> c : map.entrySet()){
                    if(set.contains(c.getKey())) continue;
                    //큰값 비교
                    if(maxValue < c.getValue()){
                        maxValue = c.getValue();
                        maxIdx = c.getKey();
                    }else if(maxValue == c.getValue()){
                        maxIdx = Math.max(maxIdx,c.getKey());
                    }
                    //작은값 비교
                    if(minValue > c.getValue()){
                        minValue = c.getValue();
                        minIdx = c.getKey();
                    }else if(minValue == c.getValue()){
                        minIdx = Math.min(minIdx,c.getKey());
                    }
                }

                if(maxValue == half){
                    winner = maxIdx;
                    break;
                }else{
                    set.add(minIdx);
                }
            }
            return new int[]{cnt,winner};
        }
        Map<Integer,Integer> voteResult(int[][] list,int total,Set<Integer> set){
            Map<Integer,Integer> map = new HashMap<>();
            for (int i = 0; i < total; i++) {
                map.put(i,0);
            }
            for(int[] l : list){
                int toVote = -1;
                for (int a : l) {
                    if (!set.contains(a)) {
                        toVote = a;
                        break;
                    }
                }
                map.put(toVote,map.get(toVote)+1);
            }
            return map;
        }
        int totalGet(int[][] arr){
            if(arr.length%2 == 0){
                return arr.length/2;
            }else{
                return (arr.length+1)/2;
            }
        }
    }
    class Solution3 {
        public String[] solution(String[] board, int y, int x) {
            String[] answer = new String[board.length];
            StringBuilder[] sb = new StringBuilder[board.length];
            for (int i = 0; i < board.length; i++) {
                sb[i] = new StringBuilder(board[i]);
            }
            if(sb[y].charAt(x) == 'M'){
                sb[y].setCharAt(x,'X');
                for (int i = 0; i < sb.length; i++) {
                    answer[i]=sb[i].toString();
                }
            }else{
                //지뢰 연쇄작업하면서 나아가기
                bfs(sb,y,x);
                // //마인 다시가려
                for (int i = 0; i < sb.length; i++) {
                    for (int j = 0; j < sb[i].length(); j++) {
                        if(sb[i].charAt(j) == 'M') sb[i].setCharAt(j,'E');
                    }
                    answer[i]=sb[i].toString();
                }
            }
            // for (StringBuilder s : sb) {
            //     System.out.println(s);
            // }
            // System.out.println();
            // for(String s : answer){
            //     System.out.println(s);
            // }
            return answer;
        }
        void bfs(StringBuilder[] sb,int row,int col){
            //8방향 다이렉션 만들자
            int[][] dirs = {
                    {-1,0},{-1,1},//북쪽,북동쪽
                    {0,1},{1,1}, // 동쪽 동남쪽
                    {1,0},{1,-1}, // 남쪽,서남쪽
                    {0,-1},{-1,-1} // 서쪽,서북쪽
            };
            int N = sb.length;
            int M = sb[0].length();
            boolean[][] visit = new boolean[N][M];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{row,col});
            while(!q.isEmpty()){
                int[] cur = q.poll();
                int r = cur[0],c = cur[1];
                if(visit[r][c]) continue;
                sb[r].setCharAt(c,'B');
                visit[r][c] = true;

                for (int i = 0; i < dirs.length; i++) {
                    int newR = r+dirs[i][0];
                    int newC = c+dirs[i][1];
                    if(newR < 0 || newR >= N || newC < 0 || newC >= M || visit[newR][newC] ||
                            sb[newR].charAt(newC) == 'M') continue;
                    // b 가 될수 있는 조건인지 확인 아닌경우에만 넣어주자
                    int mine = 0;
                    for (int j = 0; j < dirs.length; j++) {
                        int nextR = newR+dirs[j][0];
                        int nextC = newC+dirs[j][1];
                        if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) continue;
                        if(sb[nextR].charAt(nextC) == 'M') mine++;
                    }
                    if(mine > 0){
                        visit[newR][newC] = true;
                        sb[newR].setCharAt(newC,(char) ('0'+mine));
                    }else{
                        // 오직 b 인경우만 넣어주는거지
                        q.offer(new int[]{newR,newC});
                    }
                }
            }
        }
    }
}
