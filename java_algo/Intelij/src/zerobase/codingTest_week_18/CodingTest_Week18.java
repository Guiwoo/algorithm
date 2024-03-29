package zerobase.codingTest_week_18;

import java.util.Arrays;

public class CodingTest_Week18 {
    class Solution1 {
        public int solution(int[] food) {
            int sum = Arrays.stream(food).sum();
            if(sum%2 == 0){
                return sum/2;
            }else{
                return sum/2+1;
            }
        }
    }

    class Solution2{
        public int solution(int n) {
            boolean[] dp = new boolean[n*n+n];
            if(n==1)return 1;
            dp[1] = true;
            dp[2] = true;
            dp[3] = true;
            dp[5] = true;
            int cnt = 1;
            int current = 2;
            while(cnt < n){
                if(current > n*n){break;}

                if(current%2 == 0 && dp[current/2]){
                    dp[current] = true;
                    cnt++;
                }else if(current%3 == 0 && dp[current/3]){
                    dp[current] = true;
                    cnt++;
                }else if (current%5 ==0 && dp[current/5]){
                    dp[current] = true;
                    cnt++;
                }
            }
            return 0;
        }
    }

    class Solution3 {
        public String solution(int[][] points) {
            boolean xEqual = false;
            int curX = points[0][0];
            boolean yEqual = false;
            int curY = points[0][1];

            for(int[] p :points){
                xEqual = curX == p[0];
                yEqual = curY == p[1];
            }

            if(xEqual || yEqual) return "LINE";
            return "CW";
        }
    }

    class Solution4 {
        public int solution(int[] x, int[] y, int k) {
            int maxValue = 0;
            for (int i = 0; i < x.length; i++) {
                int total = 0;
                for (int j = i+1; j <= i+k; j++) {
                    if(j >= x.length) break;
                    int xDistance = Math.abs(x[i]-x[j]);
                    if(xDistance <= k){
                        total = Math.max(total,Math.abs(x[i]-x[j])+y[i]+y[j]);
                    }
                }
                if(total > maxValue){
                    maxValue = total;
                }
            }
            return maxValue;
        }
    }

    class Solution5 {
        boolean[][] map;
        public int solution(int N, int[][] stones) {
            map = new boolean[N][N];
            int[] rows = new int[N];
            int[] cols = new int[N];
            int total = stones.length;
            for(int[] stone:stones){
                int row = stone[0];
                int col = stone[1];
                rows[row]++;
                cols[col]++;
                map[row][col] = true;
            }
            int cnt = 0;
            while(total >0){
                int highRowIdx = 0,rowMax =0;
                int highColIdx =0,colMax = 0;

                for (int i = 0; i < N; i++) {
                    if(rows[i]+countCol(i) > rowMax){
                        highRowIdx = i;
                        rowMax = rows[highRowIdx]+countCol(highRowIdx);
                    }
                }
                for(int i = 0; i< N; i++){
                    if(cols[i]+countRow(i) > colMax){
                        highColIdx = i;
                        colMax = cols[highColIdx]+countRow(highColIdx);
                    }
                }
                if(rowMax > colMax){
                    total -= rows[highRowIdx];
                    rows[highRowIdx] = 0;
                    eraseColByRow(highRowIdx,cols);
                }else{
                    total -= cols[highColIdx];
                    cols[highColIdx] = 0;
                    // 관련된 세로줄 지우기
                    eraseRowByCol(highColIdx,rows);
                }
                cnt++;
            }
            return cnt;
        }
        public void eraseColByRow(int row,int[] cols){
            for (int i = 0; i < map.length; i++) {
                if(map[row][i]){
                    cols[i]--;
                    map[row][i] = false;
                }
            }
        }
        public void eraseRowByCol(int col,int[] rows){
            for (int i = 0; i < map.length; i++) {
                if(map[i][col]){
                    rows[i]--;
                    map[i][col] = false;
                }
            }
        }

        public int countCol(int row){
            int cnt =0;
            for (int i = 0; i < map.length; i++) {
                if(map[row][i])cnt++;
            }
            return cnt;
        }
        public int countRow(int col){
            int cnt = 0;
            for (int i = 0; i < map.length; i++) {
                if(map[i][col]) cnt++;
            }
            return cnt;
        }
    }
}
