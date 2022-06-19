package weeklyContest;

import java.util.HashMap;

public class Contest298 {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(2, 2);
        System.out.println(System.identityHashCode(map.get(1)));
        System.out.println(System.identityHashCode(map.get(2)));
    }
}

class Sol {
    public String sol01(String s) {
        int[] lower = new int[26];
        int[] upper = new int[26];

        for (char alphabet : s.toCharArray()) {
            if ('a' <= alphabet && alphabet <= 'z') {
                // 소문자.
                lower[alphabet - 'a']++;
            } else {
                // 대문자
                upper[alphabet - 'A']++;
            }
        }
        int cur = -1;
        for (int i = 0; i < upper.length; i++) {
            if (upper[i] >= 1 && lower[i] >= 1) {
                cur = cur > i ? cur : i;
            }
        }
        return cur == -1 ? "" : Character.toString((char) 'A' + cur);
    }

    public int sol02(int num, int k) {
        if (num == 0)
            return 0;
        if (k == 0)
            return num % 10 == 0 ? 1 : -1;
        if (num % 10 == k)
            return 1;
        // max of result is 10, because if exist valid result, we must fint it in [1,10]
        // start with 1 and look for the target i that make unit k
        for (int i = 1; i <= num / k && i <= 10; i++) {
            // if unit equal to k, we can pick any number in set to add the cap to num
            if (num % 10 == ((i * k) % 10)) // Look for equal unit's digit
                return i;
        }
        return -1;
    }
}
