package recursion101;

import java.util.*;

class fuckNqueen {
    int[] board;
    int cnt;
    int num;

    public int totalNQueens(int n) {
        board = new int[n];
        num = n;
        helper(0);
        return cnt;
    }

    int helper(int row) {
        if (row == num) {
            cnt++;
            return cnt;
        }
        for (int i = 0; i < num; i++) {
            board[row] = i;
            // 유망성체크
            if (isValidate(row)) {
                helper(row + 1);
            }
        }
        return cnt;
    }

    boolean isValidate(int row) {
        for (int i = 0; i < row; i++) {
            if (board[row] == board[i] || row - i == Math.abs(board[row] - board[i])) {
                return false;
            }
        }
        return true;
    }
}

class LeetCodeQuickSort {
    public void quickSort(int[] lst) {
        int n = lst.length;
        qSort(lst, 0, n - 1);
    }

    void qSort(int[] list, int lo, int hi) {
        if (lo < hi) {
            int p = partition(list, lo, hi);
            qSort(list, lo, p - 1);
            qSort(list, p + 1, hi);
        }
    }

    int partition(int[] lst, int lo, int hi) {
        int pivot = lst[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (lst[j] < pivot) {
                int tmp = lst[i];
                lst[i] = lst[j];
                lst[j] = tmp;
                i++;
            }
        }
        int tmp = lst[i];
        lst[i] = lst[hi];
        lst[hi] = tmp;
        return i;
    }
}

class otherWay {
    public void quickSort(int[] arr, int left, int right) {
        if (left >= right)
            return;
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    public int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (arr[j] > pivot && i < j)
                j--;
            while (arr[i] <= pivot && i < j)
                i++;
            swap(arr, i, j);
        }
        swap(arr, left, i);
        return i;
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

class NQueen {
    int answer;

    public int totalNQueens(int n) {
        int[][] arr = new int[n][n];
        helper(0, arr, n);
        return answer;
    }

    public boolean helper(int d, int[][] arr, int queen) {
        if (queen < 0)
            return false;
        if (d == arr.length * arr.length) {
            if (queen == 0) {
                for (int[] x : arr) {
                    System.out.println(Arrays.toString(x));
                }
                System.out.println("===============");
                answer++;
            }
            return queen == 0 ? true : false;
        }
        int row = d / arr.length, col = d % arr.length;

        if (arr[row][col] == 0) {
            for (int i = 0; i < arr.length; i++) {
                if (validCheck(row, i, arr)) {
                    arr[row][i] = 1;
                    queen--;
                    if (helper(d + 1, arr, queen)) {
                        return true;
                    } else {
                        queen++;
                        arr[row][col] = 0;
                    }
                }
            }
        }
        helper(d + 1, arr, queen);
        return false;
    }

    public boolean validCheck(int row, int col, int[][] arr) {
        // row check and column check
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][col] != 0)
                return false;
            if (arr[row][i] != 0)
                return false;
        }

        // diagonal lefttop
        for (int i = col; i >= 0; i--) {
            if (col - 1 >= 0 && row - 1 >= 0) {
                if (arr[row - 1][col - 1] != 0)
                    return false;
            }
        }
        // diagonal righttop
        for (int i = col; i < arr.length; i++) {
            if (col + 1 < arr.length && row - 1 >= 0) {
                if (arr[row - 1][col + 1] != 0)
                    return false;
            }
        }
        // diagonal rightbottom
        for (int i = col; i < arr.length; i++) {
            if (col + 1 < arr.length && row + 1 < arr.length) {
                if (arr[row + 1][col + 1] != 0)
                    return false;
            }
        }
        // dialing leftbottom
        for (int i = col; i >= 0; i--) {
            if (col - 1 >= 0 && row + 1 < arr.length) {
                if (arr[row + 1][col - 1] != 0)
                    return false;
            }
        }
        return true;
    }
}
