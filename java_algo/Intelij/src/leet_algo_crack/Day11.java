package leet_algo_crack;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day11 {
    public static void main(String[] args){
        Lt784 lt = new Lt784();
        System.out.println(lt.letterCasePermutation3("a1bz"));
    }
    class Lt77 {
        List<List<Integer>> result = new ArrayList<>();
        public List<List<Integer>> combine(int n, int k) {
            int arr[] = IntStream.range(1,n+1).toArray();
            int out[] = new int[k];
            combi(arr,0,0,k,out);
            return result;

        }
        public void combi(int[]arr,int depth,int start,int r,int[] out){
            if(r==0){
                result.add(Arrays.stream(out).boxed().collect(Collectors.toList()));
                return;
            }else{
                for (int i = start; i < arr.length; i++) {
                    out[depth] = arr[i];
                    combi(arr,depth+1,i+1,r-1,out);
                }
            }
        }
    }
}
class Lt784{
    class Solution {
     public List<String> letterCasePermutation(String S) {
         if (S == null) {
             return new LinkedList<>();
         }
         Queue<String> queue = new LinkedList<>();
         queue.offer(S);

         for (int i = 0; i < S.length(); i++) {
             if (Character.isDigit(S.charAt(i))) continue;
             int size = queue.size();
             for (int j = 0; j < size; j++) {
                 String cur = queue.poll();
                 char[] chs = cur.toCharArray();

                 chs[i] = Character.toUpperCase(chs[i]);
                 queue.offer(String.valueOf(chs));

                 chs[i] = Character.toLowerCase(chs[i]);
                 queue.offer(String.valueOf(chs));
             }
         }

                 return new LinkedList<>(queue);
     }
        public List<String> letterCasePermutation2(String S) {
            if (S == null) {
                return new LinkedList<>();
            }

            List<String> res = new LinkedList<>();
            helper(S.toCharArray(), res, 0);
            return res;
        }

        public void helper(char[] chs, List<String> res, int pos) {
            if (pos == chs.length) {
                res.add(new String(chs));
                return;
            }
            if (chs[pos] >= '0' && chs[pos] <= '9') {
                helper(chs, res, pos + 1);
                return;
            }

            chs[pos] = Character.toLowerCase(chs[pos]);
            helper(chs, res, pos + 1);

            chs[pos] = Character.toUpperCase(chs[pos]);
            helper(chs, res, pos + 1);
        }
        public List<String> letterCasePermutation1(String s) {
            List<String> list = new ArrayList<>();
            String word = s.toLowerCase();
            list.add(word);
            for(int i = 0; i <= word.length() - 1; i++) {
                List<String> temp = new ArrayList<>();
                for(String item: list) {
                    int val = item.charAt(i) - '0';
                    if(0 <= val && val <= 9) continue;
                    temp.add(capitalize(item, i));
                }
                list.addAll(temp);
            }
            return list;
        }

        public String capitalize(String word, int i) {
            char[] arr = word.toCharArray();
            arr[i] = (char) ((arr[i] - 'a') + 'A');
            return new String(arr);
        }
    }
}
