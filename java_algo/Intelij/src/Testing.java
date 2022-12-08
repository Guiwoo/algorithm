import java.io.IOException;
import java.util.*;

public class Testing{
    public static void main(String[] args) throws IOException {
        Solution s = new Solution();
        s.solution(new int[]{1,1,2,0});
        s.solution(new int[]{1,1,1});
        s.solution(new int[]{1,1,1,1});
    }
}

class Solution{
    public int[] solution(int[] caps){
        int len = caps.length-1;
        // 하얀모자 거르기
        int[] answer = new int[caps.length];
        Arrays.fill(answer,-1);

        answer[1] = caps[0] == 0 ? 2 : 1;
        answer[len-1] = caps[len] == 0 ? 2 : 1;
        for (int i = 1; i < len; i++) {
            switch (caps[i]) {
                case 0 -> {
                    answer[i - 1] = 2;
                    answer[i + 1] = 2;
                }
                case 2 -> {
                    answer[i - 1] = 1;
                    answer[i + 1] = 1;
                }
            }
        }
        int[] Front = answer.clone();
        int[] Rear = answer.clone();

        for (int i = 0; i < answer.length-1; i++) {
            int target = caps[i];
            if(target)
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }
}


