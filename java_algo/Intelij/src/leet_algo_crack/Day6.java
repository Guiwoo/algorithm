package leet_algo_crack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Day6 {

    public static void main(String[] args){
    }

    class Lt367 {
        public boolean isPerfectSquare(int num) {
            int i = 1, j = num;
            while(i <= j){
                int mid = i + (j-i)/2;
                int res = num/mid, tail = num%mid;
                if(tail == 0 && res == mid) return true;
                else if(res < mid){
                    j = mid-1;
                } else i = mid+1;
            }
            return false;
        }
    }

    class Lt567_clean_answer {
        public boolean checkInclusion(String s1, String s2) {
            int len1 = s1.length(), len2 = s2.length();
            if (len1 > len2) return false;

            int[] count = new int[26];
            for (int i = 0; i < len1; i++) {
                count[s1.charAt(i) - 'a']++;
                count[s2.charAt(i) - 'a']--;
            }
            if (allZero(count)) return true;

            for (int i = len1; i < len2; i++) {
                count[s2.charAt(i) - 'a']--;
                count[s2.charAt(i - len1) - 'a']++;
                if (allZero(count)) return true;
            }

            return false;
        }

        private boolean allZero(int[] count) {
            for (int i = 0; i < 26; i++) {
                if (count[i] != 0) return false;
            }
            return true;
        }
    }

    class Lt567_my_timeExcced{
        ArrayList<String> result = new ArrayList<>();
        public void permutation(String s){
            char[] arr = s.toCharArray();
            int r = arr.length;
            boolean[] visit = new boolean[arr.length];
            char[] out = new char[arr.length];
            recur(arr,visit,0,r,out);
        }
        public void recur(char[] arr,boolean[] visit,int depth,int r,char[] out){
            if(depth == r){
                String builder = "";
                for(int i=0; i<out.length;i++){
                    builder+=out[i];
                }
                result.add(builder);
                return;
            }
            for (int i = 0; i < arr.length; i++) {
                if(!visit[i]){
                    visit[i]=true;
                    out[depth] = arr[i];
                    recur(arr,visit,depth+1,r,out);
                    visit[i]=false;
                }
            }
        }
    }

    class Lt3{
        public int lengthOfLongestSubstring(String s){
            int start = 0;
            int max_Len = 0;
            HashMap<Character,Integer> map = new HashMap<>();
            for (int i = 0; i <s.length() ; i++) {
                if(map.containsKey(s.charAt(i)) && start <= map.get(s.charAt(i))){
                    start = map.get(s.charAt(i))+1;
                }else{
                    max_Len = Math.max(max_Len,i-start+1);
                }
                map.put(s.charAt(i),i);
            }
            return max_Len;
        }
        public int helper(String s){
            HashMap<Character,Integer> map = new HashMap<>();
            int cnt = 0;
            char[] arr = s.toCharArray();
            for(int i=0;i<arr.length;i++){
                if(map.get(arr[i]) == null){
                    map.put(arr[i],1);
                }else{
                    if(map.size()>cnt){
                        cnt = map.size();
                    }
                    map.clear();
                    map.put(arr[i],1);
                }
            }
            return map.size() > cnt ? map.size():cnt;
        }
    }
}
