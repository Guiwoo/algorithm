import java.io.IOException;
import java.util.*;

public class Testing{
    public static void main(String[] args) throws IOException {
        Solution s = new Solution();
//        int[] x = {-51, 38, -9, 19, -88};
//        int[] y = {69, 28, -46, 54, -44, -27, 24, 77, 100};
//        System.out.println(s.solution(x,y));
    }
}

class Solution {
    public int solution(String src, String dst) {
        Set<String> set = new HashSet();
        int answer = 0;
        int idx = 0;
        while(idx < dst.length()) {
            int i = src.indexOf(dst.charAt(idx++)) + 1;
                if(set.contains(dst.substring(idx))){
                    answer++;
                    break;
                }else{
                    while(idx < dst.length() && src.substring(i).contains(String.valueOf(dst.charAt(idx)))) {
                        i += src.substring(i).indexOf(dst.charAt(idx));
                        idx++;
                    }
                }
            set.add(dst.substring(idx-1));
            answer++;
        }
        return answer;
    }
}