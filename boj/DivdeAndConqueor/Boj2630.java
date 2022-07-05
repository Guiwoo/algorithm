package DivdeAndConqueor;

public class Boj2630 {

    int white = 0;
    int blue = 0;
    int[][] arr = {};

    public void main(int arr[][]) {
        this.arr = arr;
        helper(0, 0, arr.length);
        System.out.println(white);
        System.out.println(blue);
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
