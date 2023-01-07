
// package leetCodeAlgorithmLearn;
import java.util.*;

public class Testing {
  public static void main(String[] args) throws Exception {
    Solution s = new Solution();
    String[] words1 = { "amazon", "apple", "facebook", "google", "leetcode" };
    String[] words2 = { "l", "e" };
    s.wordSubsets(words1, words2);
  }
}

class Solution {

  public List<String> wordSubsets(String[] words1, String[] words2) {
    // words2 specific in words1 for returning answer;
    // the word is unique word, lowercase letters
    // length >1
    ArrayList<String> answer = new ArrayList();
    int[] alpha2 = new int[26];
    for (int i = 0; i < words2.length; i++) {
      alpha2[words2[i].charAt(0) - 'a']++;
    }
    for (int i = 0; i < words1.length; i++) {
      // words check to alpha2;
      if (checker(words1[i], alpha2)) {
        answer.add(words1[i]);
      }
    }
    System.out.println(answer);
    return answer;
  }

  public boolean checker(String word, int[] alpha2) {
    int[] alpha = alpha2.clone();
    for (int i = 0; i < word.length(); i++) {
      alpha[word.charAt(i) - 'a']--;
    }
    return underZero(alpha);
  }

  public boolean underZero(int[] alpha) {
    for (int i : alpha) {
      if (i > 0)
        return false;
    }
    return true;
  }
}