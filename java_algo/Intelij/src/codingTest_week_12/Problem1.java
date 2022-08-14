package codingTest_week_12;

import java.io.*;

public class Problem1 {
    public static void main(String[] args) throws IOException {
        Solution s = new Solution();

        for (int i = 1; i <= 5; i++) {
            BufferedReader bf = new BufferedReader(
                    new FileReader("./src/codingTest_week_12/test/problem1/acc_test/"+i+"_i.txt")
            );
            String x =bf.readLine().replaceAll("\"", "");
            bf = new BufferedReader(
                    new FileReader("./src/codingTest_week_12/test/problem1/acc_test/"+i+"_o.txt")
            );
            int y = Integer.parseInt(bf.readLine());
            System.out.println(x+" "+ y);
            isAnswer(i,y,s.solution(x));
        }

    }
    public static void isAnswer(int caseNum ,int ans,int ans2){
        if(ans == ans2){
            System.out.println("Case "+caseNum + ": Pass");
        }else{
            System.out.println("Case "+caseNum + ": Fail");
        }
    }
}

class Solution{
    class isRepeatA{
        int cnt;
        boolean isRepeat;
        isRepeatA(int cnt,boolean isRepeat){
            this.cnt = cnt;
            this.isRepeat = isRepeat;
        }
    }
    char a = 'a';
    public int solution(String s){
        int cnt = 0;
        while(!s.equals(Character.toString(a))){
            s= change(s);
            cnt++;
        }
        return cnt;
    }
    public String change(String s){
        StringBuffer sb = new StringBuffer();
        int idx = 0;
        while(idx < s.length()){
            if(s.charAt(idx) != a){
                sb.append(s.charAt(idx++));
                continue;
            }
            if(isLeftOrRight(s,idx)){
                int x = idx-1;
                int x2 = idx+1;
                if(x >= 0){
                    sb.setCharAt(sb.length()-1,a);
                    sb.append(a);
                }
                if(x2 < s.length()){
                    sb.append(a);
                    sb.append(a);
                }
                idx +=2;
            }
            isRepeatA rs = isRepeat(s,idx);
            if(rs.isRepeat){
                sb.append(a);
                idx += rs.cnt;
            }
        }
        return sb.toString();
    }
    public isRepeatA isRepeat(String s,int idx){
        // 원래 boolean 만 넘겨줄려다가 idx 계산하기 귀찮아서 클래스 만들었습니다.
        int cnt = 0;
        boolean rs = false;
        for (int i = idx; i < s.length(); i++) {
            if(s.charAt(i) != a) break;
            else cnt++;
        }
        if(cnt > 1) rs = true;
        return new isRepeatA(cnt,rs);
    }
    public boolean isLeftOrRight(String s,int idx){
        //idx 0 인경우 처리
        if(idx == 0 && s.charAt(idx+1) == a){
            return false;
        }else if(idx == 0 && s.charAt(idx+1) != a){
            return true;
        }
        //idx 끝에인 경우  처리
        if(idx == s.length()-1 && s.charAt(idx-1) == a){
            return false;
        }else if(idx == s.length()-1 && s.charAt(idx-1) !=a){
            return true;
        }
        // idx 그외
        if(s.charAt(idx-1) == a || s.charAt(idx+1) == a){
            return false;
        }
        // 위에 다통과 한다면 외로운 아이
        return true;
    }
}