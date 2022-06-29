import java.io.*;
import java.util.StringTokenizer;

public class Testing {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] inputs = br.readLine().split(" ");
    int[] arr = new int[Integer.parseInt(inputs[0])];
    int day = Integer.parseInt(inputs[1]);

    String[] input2 = br.readLine().split(" ");
    for (int i = 0; i < input2.length; i++) {
      arr[i] = Integer.parseInt(input2[i]);
    }

    int curSum = 0;
    int max = 0;
    for (int i = 0; i < day; i++) {
      curSum += arr[i];
      max += arr[i];
    }

    for (int i = day; i < arr.length - day + 1; i++) {
      curSum += (arr[i] - arr[i - day]);
      max = Math.max(curSum, max);
    }
    System.out.println(max);
  }
}