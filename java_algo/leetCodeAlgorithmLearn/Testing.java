import java.io.*;

public class Testing {
  public static void main(String[] args) throws Exception {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(bf.readLine());
    int[][] arr = new int[N][N];
    for (int i = 0; i < N; i++) {
      String[] inputs = bf.readLine().split(" ");
      for (int j = 0; j < inputs.length; j++) {
        arr[i][j] = Integer.parseInt(inputs[j]);
      }
    }
    bf.close();
    Boj b = new Boj();
    b.main(arr);
  }
}

class Boj {
  int white = 0;
  int blue = 0;
  int[][] arr = {};

  public void main(int arr[][]) {
    this.arr = arr;
    helper(0, 0, arr.length);
    System.out.println(blue);
    System.out.println(white);
  }

  public void helper(int row, int col, int n) {
    if (checker(row, col, n)) {
      if (arr[row][col] == 0) {
        white++;
      } else {
        blue++;
      }
      return;
    }
    int half = n / 2;

    helper(row, col, half);
    helper(row, col + half, half);
    helper(row + half, col, half);
    helper(row + half, col + half, half);
  }

  public boolean checker(int row, int col, int n) {
    int target = arr[row][col];
    for (int i = row; i < row + n; i++) {
      for (int j = col; j < col + n; j++) {
        if (arr[i][j] != target) {
          return false;
        }
      }
    }
    return true;
  }
}