package dataStructureChallenge;

import java.util.Arrays;

public class FirstUniqChar {
    public int firstUniqChar(String s) {
        int[] alphabets = new int[26];
        for (char c : s.toCharArray()) {
            alphabets[c - 'a']++;
        }
        System.out.println(Arrays.toString(alphabets));
        for (int i = 0; i < s.length(); i++) {
            if (alphabets[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}