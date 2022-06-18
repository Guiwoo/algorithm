import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class PermutationByBItmask {
    public static void main(String[] args) {
        List<String> result = letterCasePermutatin("a1b2");
        // System.out.println(result);
        PermutationBitMaskTestDrive p = new PermutationBitMaskTestDrive();
        p.testing(5, 3);
    }

    static List<String> letterCasePermutatin(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                count++;
            }
        }

        List<String> permutations = new ArrayList();
        for (int i = 0; i < 1 << count; i++) {
            int bit = 1;
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                if ('0' <= c && c <= '9') {
                    sb.append(c);
                } else {
                    sb.append((bit & i) == 0 ? Character.toLowerCase(c) : Character.toUpperCase(c));
                    bit <<= 1;
                }
            }
            permutations.add(sb.toString());
        }
        return permutations;
    }
}

class PermutationBitMaskTestDrive {
    public void testing(int N, int K) {
        // 1~n 사이 숫자의 순열 k 자리 만큼
        int[] arr = IntStream.range(1, N + 1).toArray();
        int[] out = new int[K];
        perMu(arr, out, 0, 0);
    }

    public void perMu(int[] arr, int[] out, int idx, int bit) {
        if (idx == out.length) {
            System.out.println(Arrays.toString(out));
            return;
        } else {
            for (int i = 0; i < arr.length; i++) {
                if ((bit & 1 << arr[i]) != 0)
                    continue;
                out[idx] = arr[i];
                perMu(arr, out, idx + 1, bit | 1 << arr[i]);
            }
        }
    }
}