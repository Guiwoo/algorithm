package pracitGroup.practicePs0621;

import java.util.stream.IntStream;

import javax.xml.validation.Validator;

import java.util.*;

public class Sudoku101 {
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
        Sdoku01 s = new Sdoku01();
        s.solveSudoku(board);
        for (char[] c : board) {
            for (char x : c) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}

interface ISudoku101 {
    abstract public void solveSudoku(char[][] board);
}

class Sdoku01 implements ISudoku101 {
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

class Sudoku02 implements ISudoku101 {

    @Override
    public void solveSudoku(char[][] board) {
        dfs(board, 0);
    }

    public boolean dfs(char[][] board, int cnt) {
        if (cnt == 81)
            return true;
        int row = cnt / 9;
        int col = cnt % 9;

        if (board[row][col] != '.')
            return dfs(board, cnt + 1);
        boolean[] flag = new boolean[10];
        validateNum(board, row, col, flag);
        for (int i = 1; i < 9; i++) {
            if (flag[i]) {
                board[row][col] = (char) ('0' + i);
                if (dfs(board, cnt + 1))
                    return true;
            }
        }
        board[row][col] = '.';
        return false;
    }

    public void validateNum(char[][] board, int row, int col, boolean[] flag) {
        Arrays.fill(flag, true);
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != '.')
                flag[board[row][i] - '0'] = false;
            if (board[i][col] != '.')
                flag[board[i][col] - '0'] = false;
            int r = row / 3 * 3 + i / 3;
            int c = col / 3 * 3 + i / 3;
            if (board[r][c] != '.')
                flag[board[r][c] - '0'] = false;
        }
    }
}