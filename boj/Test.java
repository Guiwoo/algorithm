import java.io.IOException;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        Sol s = new Sol();
        int[] arr = { -7, -3, -2, 5, 8 };
        s.sol(arr);
    }
}

class Sol {
    int answer = 0;

    public void sol(int[] arr) {
        recur(arr, 0, 0);
        System.out.println(answer - 1);
    }

    public void recur(int[] arr, int sum, int idx) {
        if (idx >= arr.length) {
            if (sum == 0) {
                answer++;
            }
            return;
        }
        recur(arr, sum + arr[idx], idx + 1);
        recur(arr, sum, idx + 1);
    }
}