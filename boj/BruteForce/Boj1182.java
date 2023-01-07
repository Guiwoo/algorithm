package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1182 {
    static int answer = 0;
    static int target = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] inputOne = bf.readLine().split(" ");
        int[] arr = new int[Integer.parseInt(inputOne[0])];
        target = Integer.parseInt(inputOne[1]);
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= arr.length; i++) {
            boolean[] visit = new boolean[arr.length];
            int[] out = new int[i];
            helper(arr, visit, out, 0, 0, i);
        }

        System.out.println(answer);
    }

    public static void helper(int[] arr, boolean[] visit, int[] out, int start, int depth, int r) {
        if (depth == r) {
            if (target == Arrays.stream(out).sum()) {
                answer++;
            }
            return;
        } else {
            for (int i = start; i < arr.length; i++) {
                if (visit[i] != true) {
                    visit[i] = true;
                    out[depth] = arr[i];
                    helper(arr, visit, out, i + 1, depth + 1, r);
                    visit[i] = false;
                }
            }
        }
    }

}
