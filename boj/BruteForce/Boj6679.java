package BruteForce;

import java.util.LinkedList;
import java.util.Queue;

class Boj6679 {
    public void solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 2992; i < 10000; i++) {
            int tenDecimal = getDecimal(i);
            int twelveDecimal = getTwelveDecimal(i);
            int hexaDecimal = getHexaDecimal(i);
            if (tenDecimal == twelveDecimal && twelveDecimal == hexaDecimal) {
                sb.append(i + "\n");
            }
        }
        System.out.println(sb.toString());
    }

    public int getHexaDecimal(int n) {
        Queue<Integer> q = new LinkedList<>();
        while (n > 0) {
            q.add(n % 12);
            n = n / 12;
        }
        int total = 0;
        while (!q.isEmpty()) {
            total += q.poll();
        }

        return total;
    }

    public int getTwelveDecimal(int n) {
        Queue<Integer> q = new LinkedList<>();
        while (n > 0) {
            q.add(n % 12);
            n = n / 12;
        }
        int total = 0;
        while (!q.isEmpty()) {
            total += q.poll();
        }

        return total;
    }

    public int getDecimal(int n) {
        int total = 0;
        while (n > 0) {
            total += n % 10;
            n /= 10;
        }

        return total;
    }
}