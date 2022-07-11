import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Thing {
    public static void main(String[] args) throws IOException {
        kubin k = new kubin();
        k.main();
    }
}
class jiyoung {
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
class lan{
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int left = citations[0];
        int right = citations[citations.length-1];
        while(left<=right){
            int mid = left+(right-left)/2;
            int ans = validate(citations[mid],citations);
            if(ans == 0){
                return citations[mid];
            }else if(ans == 1){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return citations[left];
    }
    public int validate(int target,int[] arr){
        int left =0;
        int right = arr.length-1;
        while(left<=right){
            int mid =left+(right-left)/2;
            if(arr[mid] < target){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        if(left+1 == arr.length-left)return 0;
        if(left+1 > arr.length-left)return 1;
        else return -1;
    }
}
class ian{
    public int solution(int n) {
        int ans = 0;

        while(n>0){
            if(n%2 != 0) ans++;
            n/=2;
        }

        return ans;
    }
}
class sudoku{
    public void main(){
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(board,0);
    }
    public boolean solveSudoku(char[][] board,int n){
        if(n == 81) return true;
        int row = n/9,col = n%9;
        if(board[row][col] != '.')return solveSudoku(board,n+1);
        boolean[] flag = new boolean[10];
        validateCheck(board,row,col,flag);
        for (int i = 1; i < 10; i++) {
            if(flag[i]){
                board[row][col] = (char)(i+'0');
                if(solveSudoku(board,n+1)){
                    return true;
                }
            }
        }
        board[row][col] = '.';
        return false;
    }
    public void validateCheck(char[][] board,int row,int col,boolean[] flag){
        Arrays.fill(flag,true);
        for (int i = 0; i < 9; i++) {
            if(board[row][i] != '.') flag[board[row][i] -'0']=false;
            if(board[i][col] != '.') flag[board[i][col] -'0']=false;
            int r = row/3*3+i/3;
            int c = col/3*3+i%3;
            if(board[r][c] != '.')flag[board[r][c]]=false;
        }
    }
}
class kubin{
    int[][] arr;
    public void main() throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(bf.readLine());
        arr = new int[input][input];
        for (int i = 0; i < input; i++) {
            String[] inputs = bf.readLine().split(" ");
            for (int j = 0; j < inputs.length; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);
            }
        }
        System.out.println(helper(new int[]{0,0},input));
    }
    public int helper(int[] start,int size) {
        if(size == 2){
            PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
            int row = start[0],col = start[1];
            for (int i = row; i < row+2; i++) {
                for (int j = col; j < col+2; j++) {
                    q.add(arr[i][j]);
                }
            }
            q.poll();
            return q.poll();
        }
        int mid = size/2;
        int curRow = start[0];
        int curCol = start[1];

        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        q.add(helper(new int[]{curRow,curCol},size/2));
        q.add(helper(new int[]{curRow,curCol+mid},size/2));
        q.add(helper(new int[]{curRow+mid,curCol},size/2));
        q.add(helper(new int[]{curRow+mid,curCol+mid},size/2));

        q.poll();
        return q.poll();
    }
}