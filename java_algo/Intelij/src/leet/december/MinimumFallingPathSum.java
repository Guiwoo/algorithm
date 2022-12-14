package leet.december;

public class MinimumFallingPathSum {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] rs = {{2,1,3},{6,5,4},{7,8,9}};
        int i = s.minFallingPathSum(rs);
        System.out.println(i);
    }
    static class Solution {
        public int minFallingPathSum(int[][] matrix) {
            int len = matrix.length;
            int result = Integer.MAX_VALUE;
            for(int i=1;i<len;i++){
                for (int j = 0; j < len; j++) {
                    if(j == 0){
                        matrix[i][j] += Math.min(matrix[i-1][j],matrix[i-1][j+1]);
                    }else if(j == len-1){
                        matrix[i][j] += Math.min(matrix[i-1][j],matrix[i-1][j-1]);
                    }else{
                        matrix[i][j] += Math.min(
                                Math.min(matrix[i-1][j],matrix[i-1][j+1]),
                                matrix[i-1][j-1]);
                    }
                    if(i == len-1){
                        result = Math.min(result,matrix[i][j]);
                    }
                }
            }

            return result;
        }
    }
}
