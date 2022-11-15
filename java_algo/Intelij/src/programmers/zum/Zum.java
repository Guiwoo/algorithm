package programmers.zum;

import java.util.*;
import java.util.stream.Collectors;

public class Zum {
    public static void main(String[] args) {
        Sol3 s3 = new Sol3();
        String[] date = new String[]{};

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기

        String[] solution = s3.solution(date);

        System.out.println(Arrays.toString(solution));

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
    }
}

class Sol1 {
    public int[] solution(int[][] data) {
        Queue<int[]> q = new PriorityQueue<>((x,y) -> {
            if(x[2] == y[2]){
                return x[1] - y[1];
            }else{
                return x[2] - y[2];
            }
        });
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            map.put(data[i][1],data[i][0]);
        }
        Set<Integer> set = new HashSet<>();
        q.offer(data[0]);
        int answerIdx = 0;
        int[] answer = new int[data.length];
        int dataIdx = 1;

        int time = 0;
        while(answerIdx < answer.length){
            if(map.containsKey(time) && !set.contains(map.get(time))){
                int docNum= map.get(time)-1;
                if(set.add(docNum+1)){
                    time += data[docNum][2];
                    answer[answerIdx++] = data[docNum][0];
                }
                continue;
            }

            while(dataIdx < answer.length){
                if(data[dataIdx][1] < time){
                    q.offer(data[dataIdx++]);
                }else{
                    break;
                }
            }

            if(!q.isEmpty()){
                int[] poll = q.poll();
                if(set.add(poll[0])){
                    answer[answerIdx++] = poll[0];
                    if(time == 0 ) time += poll[2]+poll[1];
                    else time += poll[2];
                }
            }else{
                q.offer(data[dataIdx++]);
            }

        }
        return answer;
    }
}

class Sol2 {
    public int solution(int[] histogram) {
        Stack<Integer> stack=new Stack<>();
        int max=0;
        for(int i=0;i<histogram.length;i++) {
            while(!stack.isEmpty() && histogram[stack.peek()]>histogram[i]) {
                int idx=stack.pop();
                // 맨 왼쪽에서 i까지이면 width=i, 부분 구간이면 ~.
                int width= stack.isEmpty()? i: i-stack.peek()-1;
                max=Math.max(max, width*histogram[idx]);
            }
            stack.push(i);
        }

        // 남은 값들도 계산
        while(!stack.isEmpty()) {
            int idx=stack.pop();
            int width= stack.isEmpty()? histogram.length: histogram.length-stack.peek()-1;
            max=Math.max(max, width*histogram[idx]);
        }

        return max;
    }
}

class Sol3{
    public String[] solution (String[] logs){
        Set<String> check = new HashSet<>();
        List<String> ans = new ArrayList<>();
        Map<String,HashMap<Integer,Integer>> map = new HashMap<>();
        for (String log : logs) {
            String[] x = log.split(" ");
            if(!map.containsKey(x[0])){
                map.put(x[0],new HashMap<>());
            }
            map.get(x[0]).put(Integer.valueOf(x[1]), Integer.valueOf(x[2]));
        }
        List<String> strings = map.keySet().stream().collect(Collectors.toList());

        for (int i = 0; i < strings.size(); i++) {
            for (int j = i+1; j < strings.size(); j++) {
                HashMap<Integer,Integer> student1 = map.get(strings.get(i));
                HashMap<Integer,Integer> student2 = map.get(strings.get(j));
                int student1_score = student1.values().stream().mapToInt(k->k).sum();
                int student2_score = student2.values().stream().mapToInt(k->k).sum();
                // 비교할것 두명 의 수험자
                if(student1.size() < 5 || student2.size() < 5) continue;
                if(student1.size() != student2.size()) continue;
                if(student1_score != student2_score) continue;

                if(student1.size() >= 5 && student2.size() >= 5 && student1.size() == student2.size()
                && student1_score == student2_score){
                    if(check.add(strings.get(i))){
                        ans.add(strings.get(i));
                    }
                    if(check.add(strings.get(j))){
                        ans.add(strings.get(j));
                    }
                }
            }
        }
        if(ans.size() == 0) return new String[]{"None"};
        Collections.sort(ans);
        return ans.toArray(new String[0]);
    }
}