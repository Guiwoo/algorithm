import java.sql.Array;
import java.util.*;
public class Main {
    public static void main(String[] args) {
//    int[] votes = new int[]{4,3,2,3,3,3,3,1,2,2,3};
//    Solution s = new Solution();
//        System.out.println(s.solution(votes));
//    }
//        Solution422 s = new Solution422();
//        s.solution("2552552551");
        Solution455 s = new Solution455();
        s.solution("12321321321");
    }
}

class Solution {
    public int solution(int[] votes) {
        Arrays.sort(votes);
        if(votes[0] == votes[votes.length-1]) return votes[0];
        int[] answer= helper(votes,0);
        return answer[0];
    }
    int curDive =Integer.MAX_VALUE;
    boolean found = false;
    public int[] helper(int[] votes,int dive){
        int left = 0;
        int right = votes.length;
        if(!found && votes[left] == votes[right]){
            found = true;
            return new int[]{votes[left],dive};
        }else{
            int mid = left +(right-left)/2;
            int[] leftArr = Arrays.copyOfRange(votes,left,mid);
            int[] rightArr = Arrays.copyOfRange(votes,mid,right);

            int[] leftAn = helper(leftArr,dive+1);
            int[] rightAn = helper(rightArr,dive+1);
            if(leftAn[1] < rightAn[1]) return leftAn;
            else return rightAn;
        }
    }
}
class Solution422 {
    List<String> list;
    String target;
    public String[] solution(String s) {
        list = new ArrayList<>();
        this.target = s;
        combination(5,new int[3],0,0);
        Collections.sort(list);
        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        System.out.println(list);
        return answer;
    }
    public void combination(int n,int[] out,int idx,int depth){
        if(depth == 3){
            StringBuilder sb = new StringBuilder();
            int cur = 0;
            int cnt = 0;
            for (int i = 0; i < out.length; i++) {
                int curIdx = out[i];
                String rs = target.substring(cur,curIdx+1);

                if(rs != "")cnt++;
                if(rs.charAt(0) == '0' && rs.length() > 1)return;
                sb.append(rs+".");
                cur = curIdx+1;
            }
            if(cur < target.length()){
                String rs = target.substring(cur);
                if(rs != "") cnt++;
                if(rs.charAt(0) == '0'&& rs.length() > 1)return;
                sb.append(rs+".");
            }
            sb.deleteCharAt(sb.length()-1);
            if(cnt == 4)list.add(sb.toString());
            return;
        }else{
            for (int i = idx; i < n; i++) {
                out[depth] = i;
                combination(n,out,i+1,depth+1);
            }
        }
    }
}
class Solution455 {
    Map<Integer,Integer> fibo;
    public int[] solution(String nums) {
        return null;
    }
    public boolean helper(int start,int end,String nums){
        return false;
    }
}