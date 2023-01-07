package DivdeAndConqueor;

import java.io.*;
import java.util.*;


public class Boj17829{
    public static void main(String[] args) throws IOException {
        kubin k = new kubin();
        k.main();
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
            int[] n = new int[4];
            int x = 0;
            int row = start[0],col = start[1];
            for (int i = row; i < row+2; i++) {
                for (int j = col; j < col+2; j++) {
                    n[x++] = arr[i][j];
                }
            }
            Arrays.sort(n);
            return n[2];
        }
        int mid = size/2;
        int curRow = start[0];
        int curCol = start[1];

        int[] needToSort = new int[4];
        needToSort[0] = (helper(new int[]{curRow,curCol},size/2));
        needToSort[1] = (helper(new int[]{curRow,curCol+mid},size/2));
        needToSort[2] = (helper(new int[]{curRow+mid,curCol},size/2));
        needToSort[3] = (helper(new int[]{curRow+mid,curCol+mid},size/2));

        Arrays.sort(needToSort);
        return needToSort[2];
    }
}