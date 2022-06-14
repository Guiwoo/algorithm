import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        Sol s = new Sol();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        String[] items = bf.readLine().split(" ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(items[i]);
        }
        bf.close();
        s.sol(arr);
    }
}

class Sol {
    int minAnswer;

    public void sol(int[] arr) {
        minAnswer = Integer.MIN_VALUE;
        boolean[] visit = new boolean[arr.length];
        int[] out = new int[arr.length];
        perMutation(arr, visit, out, 0);
        System.out.println(minAnswer);
    }

    public void perMutation(int[] arr, boolean[] visit, int[] out, int depth) {
        if (depth == out.length) {
            minAnswer = Math.max(minAnswer, cal(out));
        }
        for (int i = 0; i < arr.length; i++) {
            if (visit[i] != true) {
                visit[i] = true;
                out[depth] = arr[i];
                perMutation(arr, visit, out, depth + 1);
                visit[i] = false;
            }
        }

    }

    public int cal(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            result += Math.abs(arr[i] - arr[i + 1]);
        }
        return result;
    }

}