package programmers.gaus;

import java.util.Arrays;

public class CodingTest1029 {
    class Solution{
        void sol(){
            int[][] board = {
                    {0, 0, 1, 0, 0, 0},
                    {0, 2, 0, 0, 0, 1},
                    {0, 0, 2, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0},
                    {0, 1, 0, 0, 0, 0}};

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if(board[i][j] == 1){
                        fillIn(board, i,j,2);
                    }
                }
            }
            for (int[] ints : board) {
                System.out.println(Arrays.toString(ints));
            }
        }
        void fillIn(int[][] board,int row,int col,int k){
            for(int i=row-1;i>=Math.max(0,row-k);i--){
                if(board[i][col] == 1 || board[i][col] == 2) break;
                board[i][col] = -1;
            }
            for(int i=row+1;i<=Math.min(board.length-1,row+k);i++){
                if(board[i][col] == 1 || board[i][col] == 2) break;
                board[i][col] = -1;
            }
            for (int i = col-1; i >= Math.max(0,col-k) ; i--) {
                if(board[row][i] == 1 || board[row][i] == 2) break;
                if(i == col) continue;
                board[row][i] = -1;
            }
            for(int i=col+1;i<=Math.min(board[0].length-1,col+k);i++){
                if(board[row][i] == 1 || board[row][i] == 2) break;
                if(i == col) continue;
                board[row][i] = -1;
            }
        }
    }


}
