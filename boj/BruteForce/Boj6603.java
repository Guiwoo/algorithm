package BruteForce;

import java.io.IOException;
import java.util.*;

public class Boj6603 {
    public static void main(String[] args) throws IOException {
        // BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // while (true) {
        // String cases = bf.readLine();
        // if (cases.equals("0"))
        // break;
        // String[] input = cases.split(" ");
        // int groups = Integer.parseInt(input[0]);
        // int[] arr = new int[groups];
        // for (int i = 1; i < input.length; i++) {
        // arr[i - 1] = Integer.parseInt(input[i]);
        // }
        // boolean[] visit = new boolean[groups];
        // int[] out = new int[6];
        // combi(arr, visit, 0, 6);
        // }
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        int[] out = new int[6];
        boolean[] visit = new boolean[arr.length];
        forLoop6(arr);
        backTrackPr(arr, out, visit, 0, 0);
    }

    static public void backTrackPr(int[] arr, int[] out, boolean[] visit, int depth, int start) {
        if (depth == 6) {
            System.out.println(Arrays.toString(out));
            return;
        } else {
            for (int i = start; i < visit.length; i++) {
                if (visit[i] != true) {
                    visit[i] = true;
                    out[depth] = arr[i];
                    backTrackPr(arr, out, visit, depth + 1, i + 1);
                    visit[i] = false;
                }
            }
        }
    }

    static public void forLoop6(int[] arr) {
        for (int i = 0; i < arr.length - 5; i++) {
            for (int j = i + 1; j < arr.length - 4; j++) {
                for (int j2 = j + 1; j2 < arr.length - 3; j2++) {
                    for (int k = j2 + 1; k < arr.length - 2; k++) {
                        for (int k2 = k + 1; k2 < arr.length - 1; k2++) {
                            for (int l = k2 + 1; l < arr.length; l++) {
                                System.out.println(arr[i] + " " + arr[j] + " " + arr[j2] + " " + arr[k] + " " + arr[k2]
                                        + " " + arr[l]);
                            }
                        }
                    }
                }
            }
        }
    }

    static public void helper(int[] arr, int[] out, int start, int end, int index, int r) {
        if (index == r) {
            System.out.println(Arrays.toString(out));
            return;
        }
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            out[index] = arr[i];
            helper(arr, out, i + 1, end, index + 1, r);
        }
    }

    static public void backTrack(int[] arr, int[] out, boolean[] visit, int depth, int start, int r) {
        if (r == 0) {
            System.out.println(Arrays.toString(out));
            return;
        } else {
            for (int i = start; i < arr.length; i++) {
                if (visit[i] != true) {
                    visit[i] = true;
                    out[depth] = arr[i];
                    backTrack(arr, out, visit, depth + 1, i + 1, r - 1);
                    visit[i] = false;
                }
            }
        }
    }

    static public void combi(int[] arr, boolean[] visit, int depth, int require) {
        if (require == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < visit.length; i++) {
                if (visit[i]) {
                    sb.append(arr[i] + " ");
                }
            }
            System.out.println(sb.toString());
            return;
        }
        if (depth == arr.length) {
            return;
        }
        visit[depth] = true;
        combi(arr, visit, depth + 1, require - 1);
        visit[depth] = false;
        combi(arr, visit, depth + 1, require);
    }
}