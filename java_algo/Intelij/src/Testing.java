import java.io.IOException;
import java.util.*;

public class Testing{
    public static void main(String[] args) throws IOException {
        Solution s = new Solution();
        boolean valid = s.isValid("((");
        System.out.println(valid);

    }
}

class Solution {
    public boolean isValid(String s) {
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');

        Deque<Character> q = new ArrayDeque<>();
        for(char c : s.toCharArray()){
            if(c == ')' || c == '}' || c==']'){
                if(q.isEmpty()) return false;
                if(q.peekLast().equals(map.get(c))){
                    q.pollLast();
                }else{
                    return false;
                }
            }else{
                q.offer(c);
            }
        }
        if(!q.isEmpty()) return false;
        return true;
    }
}