package programmers.apr;

import java.util.Arrays;
import java.util.Stack;

public class APR {
    public static void main(String[] args) {
        Solution2 s2 = new Solution2();
        String ax = "12+12*2-12";
        String[] split = ax.split("");
        System.out.println(Arrays.toString(split));
    }
}

class Solution1{
    public int solution(String phone_number){
        String[] arr = {"010-\\d{4}-\\d{4}","010\\d{8}","\\+82-\\d{2}-\\d{4}-\\d{4}"};
        for (int i = 0; i < arr.length; i++) {
            if(phone_number.matches(arr[i])) return i+1;
        }
        return -1;
    }
}

class Solution2{
    long total = 0;
    public long solution(String s){
        char[] arr = s.toCharArray();
        dfs(0,2,arr);
        return total;
    }
    private void dfs(int start,int end,char[] arr){
        if(start >= arr.length && end > arr.length) return;
        if(end >= arr.length){
            dfs(start+2,start+4,arr);
        }else{
            long cal = cal(start, end, arr);
            total = Math.max(total,cal);
            dfs(start,end+2,arr);
        }
    }
    private long cal(int start,int end,char[] arr){
        long front = getCal(start,end,arr);
        String union = String.valueOf(arr);
        StringBuilder sb = new StringBuilder(union.substring(0,start));
        if(sb.length()>0 && sb.charAt(sb.length()-1) == '-' && front < 0){
            sb.deleteCharAt(sb.length()-1);
            sb.append("+");
            sb.append(front*-1);
        }else{
            sb.append(front);
        }
        sb.append(union.substring(end+1,union.length()));
        char[] chars = sb.toString().toCharArray();
        return getCal(0, chars.length - 1, chars);
    }

    private long getCal(int start, int end, char[] arr) {
        Stack<Long> number = new Stack<>();
        Stack<Character> symbol = new Stack<>();
        for (int i = start; i <= end ; i++) {
            if('0' <= arr[i] && arr[i] <= '9'){
                if(!symbol.isEmpty() && symbol.peek() == '*'){
                    symbol.pop();
                    number.push(number.pop() * (arr[i] - '0'));
                }else{
                    number.push((long) arr[i]-'0');
                }
            }else{
                symbol.push(arr[i]);
            }
        }
        while(!symbol.isEmpty() && number.size() >= 2){
            long num1 = number.pop();
            long num2 = number.pop();
            switch (symbol.pop()){
                case '+':
                    number.push(num1 + num2);
                    break;
                default:
                    number.push(num2 - (num1 < 0 ? num1*-1 : num1));
            }
        }
        return number.pop();
    }
}