import java.util.stream.IntStream;
import java.util.*;

public class Testing {
  public static void main(String[] args) {
    char[][] board = {
        { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
        { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
        { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
        { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
        { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
        { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
        { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
        { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
        { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
    };
    char[][] arr = {
        { '.', '3', '5', '4', '6', '9', '2', '7', '8' },
        { '7', '8', '2', '1', '.', '5', '6', '.', '9' },
        { '.', '6', '.', '2', '7', '8', '1', '3', '5' },
        { '3', '2', '1', '.', '4', '6', '8', '9', '7' },
        { '8', '.', '4', '9', '1', '3', '5', '.', '6' },
        { '5', '9', '6', '8', '2', '.', '4', '1', '3' },
        { '9', '1', '7', '6', '5', '2', '.', '8', '.' },
        { '6', '.', '3', '7', '.', '1', '9', '5', '2' },
        { '2', '5', '8', '3', '9', '4', '7', '6', '.' },
    };
    SdokuSolve s = new SdokuSolve();
    s.solveSudoku(board);
    for (char[] c : board) {
      for (char x : c) {
        System.out.print(x + " ");
      }
      System.out.println();
    }
  }
}

class SdokuSolve {
  public void solveSudoku(char[][] board) {
    helper(board);
  }

  public boolean helper(char[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == '.') {
          for (int x = 1; x <= 9; x++) {
            if (isPossible(i, j, x, board)) {
              board[i][j] = Integer.toString(x).charAt(0);
              if (helper(board)) {
                return true;
              } else {
                board[i][j] = '.';
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }

  public boolean isPossible(int row, int col, int val, char[][] board) {
    // row
    for (int i = 0; i < 9; i++) {
      if (board[i][col] == '0' + val)
        return false;
    }
    // col
    for (int i = 0; i < 9; i++) {
      if (board[row][i] == '0' + val)
        return false;
    }
    // 3*3
    int startRow = (row / 3) * 3;
    int startCol = (col / 3) * 3;
    for (int i = startRow; i < startRow + 3; i++) {
      for (int j = startCol; j < startCol + 3; j++) {
        if (board[i][j] == '0' + val) {
          return false;
        }
      }
    }
    return true;
  }
}
