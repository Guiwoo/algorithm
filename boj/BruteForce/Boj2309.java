package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[9];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }
        int badOne = 0;
        int badTwo = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (sum - arr[i] - arr[j] == 100) {
                    badOne = i;
                    badTwo = j;
                    break;
                }
            }
        }
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == badOne || arr[i] == badTwo) {
                continue;
            }
            System.out.println(arr[i]);
        }
    }
}