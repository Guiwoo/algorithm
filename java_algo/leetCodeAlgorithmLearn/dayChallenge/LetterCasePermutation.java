package dayChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LetterCasePermutation {
    public static void main(String[] args) {
        ReucrLetterPermutation r = new ReucrLetterPermutation();
        r.recur("a1b2".toCharArray(), 0, r.alphabet);
        System.out.println(r.set);
        Character c = 'c';
        System.out.println(Character.isDigit(c));

        System.out.println("===== bit mask answer =====");
        BitMaskWay bit = new BitMaskWay();
        System.out.println(bit.letterCasePermutation("a1b2"));

    }
}

class ReucrLetterPermutation {
    Set<String> set;
    HashMap<Character, Character> alphabet;

    ReucrLetterPermutation() {
        set = new HashSet<>();
        alphabet = new HashMap<>();
        for (int i = 'a'; i <= 'z'; i++) {
            alphabet.put((char) i, (char) (i - 32));
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            alphabet.put((char) i, (char) (i + 32));
        }
    }

    public List<String> result() {
        return this.set.stream().toList();
    }

    public void recur(char[] str, int idx, HashMap<Character, Character> alphabet) {
        System.out.println(str);
        for (int i = idx; i < str.length; i++) {
            if (alphabet.containsKey(str[i])) {
                char[] changeable = str.clone();
                changeable[i] = alphabet.get(str[i]);
                if (!set.contains(String.valueOf(str))) {
                    set.add(String.valueOf(str));
                    recur(str, i + 1, alphabet);
                }
                if (!set.contains(String.valueOf(changeable))) {
                    set.add(String.valueOf(changeable));
                    recur(changeable, i + 1, alphabet);
                }
            } else {
                continue;
            }
        }
    }
}

class BitMaskWay {
    public List<String> letterCasePermutation(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                cnt++;
            }
        }
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < 1 << cnt; i++) {
            StringBuilder sb = new StringBuilder();
            int bit = 1;
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                } else {
                    sb.append((bit & i) == 0 ? Character.toLowerCase(c) : Character.toUpperCase(c));
                    bit <<= 1;
                }
            }
            result.add(sb.toString());
        }
        return result;
    }
}