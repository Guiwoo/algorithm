package BruteForce;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Boj2503 {
    static int[] guessNum;
    static int[] numGuess;
    static int[][] strikeBall;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        numGuess = new int[N];
        strikeBall = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] inputs = bf.readLine().split(" ");
            numGuess[i] = Integer.parseInt(inputs[0]);
            strikeBall[i] = new int[] { Integer.parseInt(inputs[1]),
                    Integer.parseInt(inputs[2]) };
        }
        bf.close();
        setCard();
        getValue();
    }

    static void getValue() {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < guessNum.length; i++) {
            if (passTheAllcases(guessNum[i])) {
                answer.add(guessNum[i]);
            }
        }
        System.out.println(answer.size());
    }

    static boolean passTheAllcases(int target) {
        for (int i = 0; i < numGuess.length; i++) {
            if (!isPossible(target, numGuess[i], strikeBall[i])) {
                return false;
            }
        }
        return true;
    }

    static boolean isPossible(int target, int guessNum, int[] sbCnt) {
        int strike = 0;
        int ball = 0;
        String t = Integer.toString(target);
        String g = Integer.toString(guessNum);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (t.charAt(i) == g.charAt(j) && i == j) {
                    strike++;
                    continue;
                }
                if (t.charAt(i) == g.charAt(j)) {
                    ball++;
                }

            }
        }
        if (sbCnt[0] == strike && sbCnt[1] == ball) {
            return true;
        }
        return false;
    }

    static void setCard() {
        List<Integer> lists = new ArrayList();
        int[] arr = IntStream.range(1, 10).map(x -> Integer.valueOf(x)).toArray();
        int[] out = new int[3];
        permuCard(arr, out, 0, lists);
        ;
        guessNum = lists.stream().mapToInt(x -> Integer.valueOf(x)).toArray();
    }

    static void permuCard(int[] arr, int[] out, int depth, List<Integer> lists) {
        if (depth == out.length) {
            int result = 0;
            for (int i = 0; i < out.length; i++) {
                result *= 10;
                result += out[i];
            }
            lists.add(result);
            return;
        } else {
            for (int i = depth; i < arr.length; i++) {
                out[depth] = arr[i];
                swap(arr, depth, i);
                permuCard(arr, out, depth + 1, lists);
                swap(arr, depth, i);
            }
        }
    }

    static void swap(int[] arr, int depth, int i) {
        int tmp = arr[i];
        arr[i] = arr[depth];
        arr[depth] = tmp;
    }
}
