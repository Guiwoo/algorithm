package Programmers;

import java.util.*;

public class ExpressN {
    public int expressN(int n, int target) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> set = new HashSet<>(List.of(n));
        if (set.contains(target))
            return 1;
        map.put(1, set);
        int cur = n;
        for (int i = 2; i < 9; i++) {
            set = new HashSet<>();
            set.add(cur * 10 + n);
            cur = cur * 10 + n;
            for (int j = 1; j <= i / 2; j++) {
                Set<Integer> set1 = map.get(j);
                Set<Integer> set2 = map.get(i - j);

                for (int x : set1) {
                    for (int y : set2) {
                        set.add(x + y);
                        if (x - y > 0) {
                            set.add(x - y);
                        } else if (y - x > 0) {
                            set.add(y - x);
                        }
                        set.add(x * y);
                        if (x != 0) {
                            set.add(y / x);
                        }
                        if (y != 0) {
                            set.add(x / y);
                        }
                        if (set.contains(target)) {
                            return i;
                        }
                    }
                }

            }
            map.put(i, set);
        }
        for (Map.Entry<Integer, Set<Integer>> en : map.entrySet()) {
            System.out.println(en.getKey() + "  " + en.getValue());
        }
        return -1;
    }
}
