package programmers.wefun;

import java.util.*;

public class WeFun {
    public static void main(String[] args) {
        Solution1 s = new Solution1();
//        int[] solution = s.solution(6, 1);
//        System.out.println(Arrays.toString(solution));
        Solution41 s2 = new Solution41();
        int solution = s2.solution(15);
        System.out.println(solution);
    }

    static class Solution1 {
        public int[] solution(int day, int k) {
            int[] month = {31,28,31,30,31,30,31,31,30,31,30,31};
            int[] answer = new int[12];

            for (int i = 0; i < month.length; i++) {
                //1일 기준 으로 7*
                int standard = 1;
                while(standard < month[i]){
                    if(standard+7 < month[i] && k<=standard+7) break;
                    standard+=7;
                }

                if(1 == standard){
                    answer[i] = day > 4 ? 1 : 0;
                }else{
                    int ans =(day+Math.abs(standard-k))%7;
                    answer[i] = ans > 4 ? 1 : 0;
                }

                //다음 1일 계산해서 넘겨줘
                while(standard <= month[i]){
                    if(standard + 7 > k) break;
                    standard+=7;
                }
                day = (day+(month[i]-standard)+1)%7;
            }

            return answer;
        }

    }

    class Solution2 {
        public String[] solution(String[] s) {
            int max = 0;
            HashMap<String, HashSet<String>> map = new HashMap<>();
            for (int i = 0; i < s.length; i++) {
                String[] arr = s[i].split(" ");
                String name = arr[0];
                HashSet<String> set = map.getOrDefault(name, new HashSet<>());
                for (int j = 1; j < arr.length; j++) {
                    set.add(arr[j]);
                }

                map.put(name, set);
                max = Math.max(max, set.size());
            }

            PriorityQueue<String> pq = new PriorityQueue<>();
            for (Map.Entry<String, HashSet<String >> entry : map.entrySet()) {
                if (max == entry.getValue().size()) {
                    pq.offer(entry.getKey());
                }
            }

            String[] answer = new String[pq.size()];
            int idx = 0;
            while (!pq.isEmpty()) {
                answer[idx++] = pq.poll();
            }

            return answer;
        }
    }

    class Solution3 {
        static boolean[] visited;
        static int answer;
        public static int solution(int[] arr, int k, int t) {
            answer = 0;
            visited = new boolean[arr.length];

            int i = k;
            while (i <= arr.length) {

                combi(arr, 0, i, arr.length, t);
                i += 1;
            }


            return answer;
        }

        public static void combi(int[] arr, int n, int r, int end, int t) {
            if (r == 0) {
                int sum = 0;
                for (int i = 0; i < visited.length; i++) {
                    if (visited[i]) {
                        sum += arr[i];
                    }
                }

                if (sum <= t) {
                    answer += 1;
                }
                return;
            }

            if (n == end) {
                return;
            }

            visited[n] = true;
            combi(arr, n+1, r-1, end, t);
            visited[n] = false;
            combi(arr, n+1, r, end, t);
        }
    }

    class Solution4 {
        public int solution(int num) {
            int idx = 1;
            while(true){
                if((idx+1)*(idx+1) > num) break;
                idx++;
            }
            if(idx*idx == num) return 1;
            int answer = Integer.MAX_VALUE;
            for(int i=idx;i>0;i--){
                answer = Math.min(answer,cal(num,i));
                if(answer == 2) return 2;
            }
            return answer;
        }

        private static int cal(int num,int idx){
            int answer = 0;
            while(num > 0){
                if(num == 0) return answer;
                int target = idx*idx;
                while(target <= num){
                    answer++;
                    num-=target;
                }
                idx--;
            }
            return answer;
        }
    }
    static class Solution41{
        List<Integer> list = new ArrayList();
        int answer = Integer.MAX_VALUE;

        public int solution(int n) {
            for (int i = 1; i < 317; i++) {
                if(i*i > 100_000) break;
                list.add(i*i);
            }
            for (int i = 2; i < 6; i++) {
                permutation(i,0,n);
                if(answer < 5) return answer;
            }
            return answer;
        }

        private void permutation(int cnt,int depth,int n) {
            // 2개를 선택했으므로, 결과를 출력하고 재귀를 종료한다.
            if(n < 0 || depth > cnt) return;
            if (cnt == depth) {
                if(n==0) answer = Math.min(cnt,answer);
                return;
            }
            // 대상 집합을 순회하며 숫자를 하나 선택한다.
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i) > n) break;
                n -= list.get(i);
                permutation(cnt,depth+1,n);
            }
        }
    }
    class Solution5{}
    class Solution6 {
        String query = "" +
                "— 코드를 입력하세요\n" +
                "SELECT\n" +
                "    CASE\n" +
                "        WHEN b.review = 0 THEN '0' \n" +
                "        WHEN b.review = 1 THEN '< 50' \n" +
                "        WHEN b.review = 2 THEN '< 100' \n" +
                "        ELSE '>= 100'\n" +
                "    END as '후기 수',\n" +
                "    COUNT(b.review) as '공간 수'\n" +
                "FROM(\n" +
                "    SELECT\n" +
                "        CASE\n" +
                "            WHEN a.cnt = 0 THEN '0' \n" +
                "            WHEN a.cnt > 0 AND a.cnt < 50 THEN '1'\n" +
                "            WHEN a.cnt >= 50 AND a.cnt < 100 THEN '2'\n" +
                "            ELSE '3'\n" +
                "        END as review\n" +
                "    FROM (\n" +
                "        SELECT\n" +
                "            COUNT(PR.PLACE_ID) as cnt\n" +
                "        FROM PLACES P\n" +
                "        LEFT JOIN PLACE_REVIEWS PR ON PR.PLACE_ID = P.ID\n" +
                "        GROUP BY P.ID\n" +
                "    ) a\n" +
                ") b\n" +
                "GROUP BY b.review\n" +
                "ORDER BY b.review";
    }
}
