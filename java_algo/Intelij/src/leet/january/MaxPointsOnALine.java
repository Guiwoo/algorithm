package leet.january;

import java.util.*;

public class MaxPointsOnALine{
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] arr = {{1,1},{2,2},{3,3}};
        int[][] arr2 = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        int[][] arr3 = {{0,0},{1,0}};
//        int i = s.maxPoints(arr);
        int i1 = s.maxPoints(arr3);
        System.out.println(i1);
    }
    static class Solution{
        public int maxPoints(int[][] points) {
            int max =0;
            HashMap<Long,Integer> map = new HashMap<>();
            for (int i = 0; i < points.length; i++) {
                int dup = 1;
                map.clear();
                for (int j = i+1; j < points.length; j++) {
                    int dx = points[j][0] - points[i][0], dy = points[j][1] - points[j][0];
                    if(dx == 0 && dy == 0) dup++;
                    else{
                        int gc = gcd(dx,dy);
                        long slope = ((long) (dy/gc) << 32) + dx/gc;
                        map.put(slope,map.getOrDefault(slope,0)+1);
                    }
                }
                max = Math.max(max,dup);
                for(Map.Entry<Long,Integer> entry : map.entrySet()){
                    max = Math.max(max,entry.getValue()+dup);
                }
            }
            return max;
        }
        private int gcd(int a,int b){
            return b == 0 ? a : gcd(b, a%b);
        }
    }
}
