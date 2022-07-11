import java.util.*;


public class Test {
    public static void main(String[] args) {
//        Solution1 s = new Solution1();
//        Solution2 s = new Solution2();
//        Solution3 s = new Solution3();
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
//        s.solveSudoku(board);
    }
}
//Coordinate Beginner
//Coordinate Intermediate
class Solution2 {
    public void solveSudoku(char[][] board) {
        helper(board,0);
        for(char[] c : board){
            System.out.println(Arrays.toString(c));
        }
    }
    public boolean helper(char[][] board,int n){
        if(n == 81)return true; // 왜 끝났으니깐 9*9 ? 81 우리는 0부터 출발합니다.
        int row = n/9,col = n%9; // 로우칼럼 계산하기 항상 칼럼으로 나누고 나머지 연산 해주면 로우칼럼 나오는 트릭.
        if(board[row][col]!= '.') return helper(board,n+1);  // 비어있지 않기 떄문에 넘겨줍니다.

        for (int i = 1; i <= 9; i++) { //어떤 숫자가 검증이 된지 모르기 떄문에 for 를돌려줍니다. 1번부터 가던 9번부터 가던 어쩃든 모든 숫자 돌면됩니다.
            if(validateCheck(board,row,col,i)){ // 검증 된 숫자라면 진행 시켜 줍니다.
                board[row][col] = (char) (i + '0');
                if(helper(board,n+1)){
                    //false 가 경우 단하나. helper 함수가 끝까지 내려가는경우
                    // 즉 어는 숫자도 검증되지 못해 여기서 다음 재귀로 넘어가지 를 못해 아래로 내려가는 경우
                    return true;
                }
            }
        }
        board[row][col] = '.'; // 저 위에서 다음 재귀로 못넘어간 나머지 가치 가 없으니 원복시키고 함수를 종료 합니다.
        return false;
    }
    public boolean validateCheck(char[][] board,int row,int col,int target){
        char tg = (char)(target+'0');
        //row check && col check;
        for (int i = 0; i < 9; i++) {
            if(board[row][i] == tg || board[i][col] == tg){
                return false;
            }
        }
        //3*3 chcck;
        int nRow = row/3*3;
        int nCol = col/3*3;
        for (int i = nRow; i < nRow+3; i++) {
            for (int j = nCol; j < nCol+3; j++) {
                if(board[i][j] == tg) return false;
            }
        }
        return true;
    }
}
//Coordinate Master 10.9k view Solution
class Solution3 {
    public void solveSudoku(char[][] board) {
        dfs(board,0);
    }
    private boolean dfs(char[][] board, int d) {
        if (d==81) return true; //found solution
        int i=d/9, j=d%9;
        if (board[i][j]!='.') return dfs(board,d+1);//prefill number skip

        boolean[] flag=new boolean[10];
        validate(board,i,j,flag);
        for (int k=1; k<=9; k++) {
            if (flag[k]) {
                board[i][j]=(char)('0'+k);
                if (dfs(board,d+1)) return true;
            }
        }
        board[i][j]='.'; //if can not solve, in the wrong path, change back to '.' and out
        return false;
    }
    private void validate(char[][] board, int i, int j, boolean[] flag) {
        Arrays.fill(flag,true);
        for (int k=0; k<9; k++) {
            if (board[i][k]!='.') flag[board[i][k]-'0']=false;
            if (board[k][j]!='.') flag[board[k][j]-'0']=false;
            int r=i/3*3+k/3;
            int c=j/3*3+k%3;
            if (board[r][c]!='.') flag[board[r][c]-'0']=false;
        }
    }
}