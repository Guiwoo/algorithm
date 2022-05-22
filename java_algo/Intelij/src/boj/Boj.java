package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj {
    public static void main(String[] args){

    }
}


class TwoPointer{
    public void Boj3273() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        HashMap<Integer,Integer> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            map.put(num,i);
        }
        int x = Integer.parseInt(bf.readLine());

        Arrays.sort(arr);

        int left = 0;
        int right = arr.length-1;
        int cnt = 0;

        while(left<=right){
            int target = arr[left]+arr[right];

            if(target<x){
                left++;
            }else if(target>x){
                right--;
            }else{
                if(map.get(arr[left]) <map.get(arr[right]) || map.get(arr[left]) >map.get(arr[right]))
                    left++;
                right--;
            }
        }
        System.out.println(cnt);
    }
    public void Boj2740() throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = new int[2];
        int left = 0;
        int right = arr.length-1;
        int max = 2000000000;
        Arrays.sort(arr);
        while(left<right){
            int sum = arr[left]+arr[right];
            if(Math.abs(sum) < max){
                max = sum;
                result[0] = arr[left];
                result[1] = arr[right];
            }else if(sum>0){
                right--;
            }else{
                left++;
            }
        }
        System.out.println(result[0]+" "+result[1]);
    }
    public void Boj1806() throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = bf.readLine().split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        int[] n = new int[N+1];
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        for (int i = 0; i < N; i++) {
            n[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int cur = 0;
        int answer = Integer.MAX_VALUE;

        while(start<=N && end<=N){
            if(cur >= M && answer>end-start) answer = end-start;
            if(cur<M) cur += n[end++];
            else cur -= n[start++];
        }
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }

}