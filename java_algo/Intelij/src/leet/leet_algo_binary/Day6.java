package leet.leet_algo_binary;

public class Day6 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.findKthPositive(new int[]{1,2,3,4},2));
    }
}

class Solution {
    public int arrangeCoins(int n) {
        long start = 1 , end = n;
        long k , currentSum;

        while(start<=end){
            k = start + (end-start)/2;
            currentSum = k*(k+1)/2 ;

            if(currentSum == n) return (int)k;
            else if(currentSum > n) end = k-1;
            else start = k+1;
        }
        return (int)end;
    }
    public int findKthPositive(int[] arr, int k) {
        int idx =0;
        int num = 0;
        int point = 0;
        while(idx != k){
            num++;
            if(idx == k){
                break;
            }
            if(point<arr.length && num == arr[point]){
                point++;
            }else{
                idx++;
            }
        };
        System.out.println(arr.length+" "+point);
        return num;
    }
}