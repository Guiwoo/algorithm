package leet_algo_crack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day11 {
    public static void main(String[] args){

    }
    class Lt77 {
        List<List<Integer>> result = new ArrayList<>();
        public List<List<Integer>> combine(int n, int k) {
            int arr[] = IntStream.range(1,n+1).toArray();
            int out[] = new int[k];
            combi(arr,0,0,k,out);
            return result;

        }
        public void combi(int[]arr,int depth,int start,int r,int[] out){
            if(r==0){
                result.add(Arrays.stream(out).boxed().collect(Collectors.toList()));
                return;
            }else{
                for (int i = start; i < arr.length; i++) {
                    out[depth] = arr[i];
                    combi(arr,depth+1,i+1,r-1,out);
                }
            }
        }
    }
}
