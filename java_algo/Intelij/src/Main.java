import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] me = getPrime(n);
        int result = twoPoints(me,n);
        System.out.println(result);
    }
    public static int twoPoints(int[] arr, int n){
        if(arr.length == 1 && arr[0] != n){
            return 0;
        }

        int start =0;
        int end = 0;
        int answer = 0;
        int total = 0;

        if(arr[arr.length-1] == n){
            answer++;
        }
        while(start<arr.length && end<arr.length){
            if(total<n){
                total+=arr[end++];
            }else if(total == n){
                total = 0;
                start++;
                end = start;
                answer++;
            }else{
                total-=arr[start++];
            }
        }
        return answer;
    }
    public static int[] getPrime(int n){
        ArrayList<Integer> result = new ArrayList<>();
        if(n == 1){
            return new int[1];
        }
        boolean[] arr = new boolean[n+1];
        Arrays.fill(arr,true);
        for (int i = 2; i <= n; i++) {
            if(arr[i]){
                result.add(i);
                for(int j=i+i;j<=n;j+=i){
                    arr[j] = false;
                }
            }
        }
        return result.stream().mapToInt(i->i.intValue()).toArray();
    };
}