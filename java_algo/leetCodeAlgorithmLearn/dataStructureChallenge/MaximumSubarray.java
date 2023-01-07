package dataStructureChallenge;

import java.util.Arrays;

public class MaximumSubarray {
    public static void main(String[] args) {
        int[] out = new int[3];
        int[] arr = { 1, 2, 3 };
        bitmask(arr, out, 0, 0);
    }

    public static void bitmask(int[] arr, int[] out, int flag, int depth) {
        if (depth == out.length) {
            System.out.println(Arrays.toString(out));
            return;
        } else {
            for (int i = 0; i < out.length; i++) {
                if ((flag & 1 << i) != 0)
                    continue;
                out[depth] = arr[i];
                bitmask(arr, out, flag | 1 << i, depth + 1);
            }
        }
    }

    class Solution2 {
        public int testing(int[] arr) {
            if (arr.length == 1)
                return 1;
            int windowSum = -10000;
            int max = -10000;
            int start = 0, end = 0;
            while (end < arr.length) {
                windowSum += arr[end];
                if (windowSum < arr[end]) {
                    start = arr[end];
                    windowSum = arr[end];
                }
                max = Math.max(max, windowSum);
                end++;
            }
            return max;
        }

        public int testing2(int[] arr) {
            if (arr.length == 1)
                return arr[0];
            int max = Integer.MIN_VALUE;
            int currentSum = 0;
            for (int i = 0; i < arr.length; i++) {
                currentSum += arr[i];
                max = Math.max(max, currentSum);
                if (currentSum < 0)
                    currentSum = 0;
            }
            return max;
        }
    }
}
