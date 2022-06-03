import java.util.*;
//String change = code.substring(leftIdx+1,rightIdx).repeat(code.charAt(leftIdx-1)-'0');
//        System.out.println(leftIdx+" "+rightIdx);
//        sb.replace(leftIdx-1,rightIdx+1,change);
//        code = sb.toString();
//        System.out.println(code);
public class Test {
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public int[] solution(int[] a, int[] b) {
        if(a.length < b.length){
            int[] tmp = a;
            a = b;
            b = a;
        }
        Stack<Integer> list = new Stack<>();
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }
        int cur = b.length-1;
        int upper = 0;
        for (int i = list.size()-1; i >= 0; i--) {
            if(cur > -1){
                int sum = b[cur]+upper+list.get(i);
                upper = 0;
                if(sum>9){
                    list.set(i,sum-10);
                    upper++;
                }else{
                    list.set(i,sum);
                }
                cur--;
            }
            if(upper != 0){
                int sum = list.get(i)+upper;
                upper = 0;
                if(sum>9){
                    list.set(i,sum-10);
                    upper++;
                }else{
                    list.set(i,sum);
                }
            }
        }
        int[] result = list.stream().mapToInt(x->x).toArray();
        if(upper != 0){
            int[] newResult = new int[result.length+1];
            System.arraycopy(result,0,newResult,1,result.length);
            newResult[0] = 1;
            result = newResult;
        }
        return result;
    }
}