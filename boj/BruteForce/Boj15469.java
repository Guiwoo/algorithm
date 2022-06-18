package BruteForce;

import java.util.stream.IntStream;

class Boj15649 {
    StringBuffer sb;

    public StringBuffer bitmask(int n, int k) {
        sb = new StringBuffer();
        int[] arr = IntStream.range(1, n + 1).toArray();
        int[] out = new int[k];
        recur(arr, out, 0, 0);
        return sb;
    }

    public void recur(int[] arr, int[] out, int flag, int depth) {
        if (depth == out.length) {
            for (int i = 0; i < out.length; i++) {
                sb.append(out[i] + " ");
            }
            sb.append("\n");
        } else {
            for (int i = 0; i < arr.length; i++) {
                if ((flag & 1 << i) != 0)
                    continue;
                out[depth] = arr[i];
                recur(arr, out, flag | 1 << i, depth + 1);
            }
        }
    }
}