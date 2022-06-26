package BruteForce;

import java.io.*;
import java.util.*;

class Test {
    static int[] arr;
    static boolean result;

    public static void main(String[] args) throws IOException {
        arr = new int[45];
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + i + 1;
        }
        System.out.println(Arrays.toString(arr));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(bf.readLine());
            // sb.append(sol(input) + "\n");
            System.out.println(sol(input));
        }
        // System.out.println(sb);
        bf.close();
    }

    static int sol(int input) {
        result = false;
        int index = indexCounter(input);
        int[] out = new int[3];
        permu(out, 0, 0, input, index);
        return result ? 1 : 0;
    }

    static void permu(int[] out, int flag, int depth, int target, int index) {
        if (result)
            return;
        if (out.length == depth) {
            System.out.println(Arrays.toString(out));
            if (Arrays.stream(out).sum() == target) {
                result = true;
            }
            return;
        } else {
            for (int i = 0; i <= index; i++) {
                if ((flag & 1 << i) != 0)
                    continue;
                out[depth] = arr[i];
                permu(out, flag, depth + 1, target, index);
            }
        }
    }

    static int indexCounter(int target) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > target) {
                index = i - 1;
                break;
            }
        }
        return index;
    }
}
