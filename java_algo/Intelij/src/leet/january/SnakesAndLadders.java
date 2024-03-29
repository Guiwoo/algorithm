package leet.january;

import java.util.*;

public class SnakesAndLadders {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] board ={
        {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                    {-1,35,-1,-1,13,-1},
                        {-1,-1,-1,-1,-1,-1},
                            {-1,15,-1,-1,-1,-1}};
        int[][] board2 = {{-1,-1},{-1,3}};
        int[][] board3 = {{-1,-1,-1},{-1,9,8},{-1,8,9}};
        int[][] board4 = {
                {-1,-1,19,10,-1},
        {2,-1,-1,6,-1},
            {-1,17,-1,19,-1},
                {25,-1,20,-1,-1},
                    {-1,-1,-1,-1,15}
        };
        MySol s2 = new MySol();
        System.out.println(s2.snakesAndLadders(board)+" "+"answer");
    }

    static class Solution {
        int length = 1;
        int [][] board;
        public int snakesAndLadders(int[][] board) {
            this.board = board;
            length = board.length;
            HashMap<Integer,Integer> visited = new HashMap();
            visited.put(1,0);
            Queue<Integer> arr = new LinkedList<>();
            arr.add(1);
            while (!arr.isEmpty()){
                int n = arr.remove();
                for(int i = n+1;i<=n+6;i++){
                    int next = i;
                    int nextPos = getPosition(i);
                    if(next>length*length) return -1;
                    if(nextPos!=-1){
                        next = nextPos;
                    }
                    if(next==length*length) return visited.get(n)+1;
                    if(!visited.containsKey(next)){
                        visited.put(next,visited.get(n)+1 );
                        arr.add(next);
                    }
                }

            }
            return -1;
        }

        public int getPosition(int n){
            int row = (n-1)/length;
            int column = (n-1)%length;
            if(row%2!=0){
                column = (column-length+1)*-1;
            }
            row = (row-length+1)*-1;

            int  result = board[row][column];
            return result;
        }
    }
    static class MySol {
        int n;
        int m;
        public int snakesAndLadders(int[][] board) {
            n = board.length;
            m = board[0].length;
            HashMap<Integer,Integer> visited = new HashMap();
            visited.put(1,0);
            Queue<Integer> arr = new LinkedList<>();
            arr.add(1);
            while (!arr.isEmpty()){
                int poll = arr.remove();
                System.out.println("n = " + poll);
                for(int i = poll+1;i<=poll+6;i++){
                    int next = i;
                    if(next>n*m) return -1;
                    int nextPos = getNextPosition(board,i-1);
                    if(nextPos!=-1){
                        next = nextPos;
                    }
                    if(next==n*m) return visited.get(poll)+1;
                    if(!visited.containsKey(next)){
                        visited.put(next,visited.get(poll)+1 );
                        arr.add(next);
                    }
                }

            }
            return -1;
        }

        private int getNextPosition(int[][] board, int i) {
            int row,col;
            if((i/m)%2 != 0){
                //reverse way
                row = i/m;
                col = m - (i%m) - 1;
            }else{
                //right way
                row = i/m;
                col = i%m;
            }
            return board[n-row-1][col];
        }
    }
}
