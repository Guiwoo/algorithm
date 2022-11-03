package programmers.kakaoMobility;

import java.util.*;

public class KakaoMobility {

    class Solution1 {
        public String solution(String S, String C) {
            String[] names = S.split(", ");
            String ettEmail = C.toLowerCase();
            Map<String,Integer> nameChecker = new HashMap<>();
            StringBuffer answer = new StringBuffer();
            for (String name : names) {
                StringBuffer sb = new StringBuffer();
                String[] splitName = name.toLowerCase().split(" ");
                //첫번쨰 이름 추가해주는 부분
                sb.append(splitName[0].charAt(0));
                if(splitName.length == 3){
                    sb.append(splitName[1].charAt(0));
                }
                int idx = splitName.length == 3 ? 2 : 1;
                String last = splitName[idx].replace("-", "");
                if(last.length() > 8){
                    last = last.substring(0,8);
                }
                sb.append(last);
                if(nameChecker.containsKey(sb.toString())){
                    Integer integer = nameChecker.get(sb.toString());
                    nameChecker.put(sb.toString(), integer+1);
                    sb.append(integer+1);
                }else{
                    nameChecker.put(sb.toString(),1);
                }
                answer.append(name+" "+"<"+ sb+"@"+ettEmail+".com" +">, ");
            }
            answer.deleteCharAt(answer.length()-2);
            return answer.toString().trim();
        }
    }
    class Solution2 {
        Map<Integer, List<Integer>> reverse = new HashMap<>();
        void init(int[] T){
            for (int i = 0; i < T.length; i++) {
                reverse.put(i,new ArrayList<>());
            }
            for (int i = 0; i < T.length; i++) {
                if(T[i] == i) continue;
                reverse.get(i).add(T[i]);
            }
        }
        public int solution(int[] T, int[] A) {
            init(T);
            Set<Integer> skils = new HashSet<>();
            Queue<Integer> q = new LinkedList<>();
            for (Integer a : A) {
                q.add(a);
            }
            while(!q.isEmpty()){
                Integer poll = q.poll();
                if(skils.add(poll)){
                    List<Integer> integers = reverse.get(poll);
                    for (int i = 0; i < integers.size(); i++) {
                        q.add(integers.get(i));
                    }
                }
            }
            return skils.size();
        }
    }
    class Solution3 {
        public int solution(int[] A) {
            int max = 0;
            int[] oddNum = {A[0],1};
            int[] evenNum = {A[1],1};
            for(int i=2;i<A.length;i++){
                if((i-1)%2 ==0){
                    //짝수케이스
                    if(A[i] != evenNum[0]){
                        max = Math.max(oddNum[1]+evenNum[1],max);
                        evenNum[0] = A[i];
                        evenNum[1] = 1;
                    }else{
                        evenNum[1]+=1;
                    }
                }else{
                    //홀수케이스
                    if(A[i] != oddNum[0]){
                        max = Math.max(oddNum[1]+evenNum[1],max);
                        //끊고 다시시작
                        oddNum[0] = A[i];
                        oddNum[1] = 1;
                    }else{
                        oddNum[1] +=1;
                    }
                }
            }
            return Math.max(max,oddNum[1]+evenNum[1]);
        }
    }
}
