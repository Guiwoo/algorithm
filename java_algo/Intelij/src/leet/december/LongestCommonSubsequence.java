package leet.december;

public class LongestCommonSubsequence {
}

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int A, B;
        int[] alpha = new int[26];
        if (text1.length() > text2.length()) {
            A = text1.length();
            B = text2.length();
        } else {
            A = text2.length();
            B = text1.length();
        }
        return 0;
    }
}
