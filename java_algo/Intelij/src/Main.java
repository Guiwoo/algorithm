import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution s = new Solution();
        int[][] maze ={
                {0,0,0,0},
                {1,1,1,0},
                {1,0,0,0},
                {0,0,0,0},
                {0,1,1,1},
                {0,1,0,0},
        };
        int[][] maze2 = {
                {0,0,0},
                {1,1,0},
                {0,0,0},
                {0,1,1},
                {0,1,0}
        };
        int[] height = {2,1,5,6,2,3};
        System.out.println(s.solution(height));
    }
}
class Solution {
    public  static int solution(int[] heights) {
        int max = 0;

        for(int i = 0; i < heights.length; ++i){
            int sideLength = 1;
            int currHeight = heights[i];
            if(heights[i]== 0) continue;
            for(int j = i - 1; j >= 0; --j){
                if(heights[j] < currHeight ){
                    break;
                }
                ++sideLength;
            }
            for(int j = i + 1; j < heights.length; ++j){
                if(heights[j] < currHeight ){
                    break;
                }
                ++sideLength;
            }
            max = Math.max (max, currHeight * sideLength);  // (현재 높이 * 가능한 최대한의 밑변의 길이)
        }
        return max;
    }
}