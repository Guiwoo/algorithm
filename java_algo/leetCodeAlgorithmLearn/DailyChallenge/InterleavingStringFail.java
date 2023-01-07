package DailyChallenge;

import java.util.*;

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        boolean[][] visit = new boolean[s1.length() + 1][s2.length() + 1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, 0 });

        while (!q.isEmpty()) {
            int[] p = q.poll();
            if (visit[p[0]][p[1]])
                continue;
            if (p[0] == s1.length() && p[1] == s2.length())
                return true;

            if (p[0] < s1.length() && s1.charAt(p[0]) == s3.charAt(p[0] + p[1]))
                q.offer(new int[] { p[0] + 1, p[1] });
            if (p[1] < s2.length() && s2.charAt(p[1]) == s3.charAt(p[0] + p[1]))
                q.offer(new int[] { p[0], p[1] + 1 });
            visit[p[0]][p[1]] = true;
        }
        return false;
    }
}