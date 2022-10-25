package programmers;

import java.util.*;

public class Anagram {
    public static int[] solution(String[] words,String[] sentences){
        int[] answer = new int[sentences.length];
        Map<String,Integer> map = new HashMap<>();
        Map<String,String> alpha = new HashMap<>();
        for(String word:words){
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            alpha.put(word,s);
            map.put(s,map.getOrDefault(s,0)+1);
        }

        for (int i = 0; i < sentences.length; i++) {
            int total = 1;
            String[] split = sentences[i].split(" ");
            for (String s : split){
                String s1 = alpha.get(s);
                total *= map.get(s1);
            }
            answer[i] = total;
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] words = {"the","bats","tabs","in","cat","act"};
        String[] sentences = {"cat the bats","in the cat","act tabs in"};

        int[] solution = solution(words, sentences);
        System.out.println(Arrays.toString(solution));
    }
}
