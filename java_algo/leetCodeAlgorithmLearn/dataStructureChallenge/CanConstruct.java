package dataStructureChallenge;

public class CanConstruct {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] alphabets = new int[26];
        for (char c : magazine.toCharArray()) {
            alphabets[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            alphabets[c - 'a']--;
        }
        for (int x : alphabets) {
            if (x < 0)
                return false;
        }
        return true;
    }
}