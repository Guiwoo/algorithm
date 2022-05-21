import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test {
    public static void main(String[] args) throws IOException {

    }
    public static long getGcd(long a,long b){
       long r;
       while(b != 0){
           r = a % b;
           a = b;
           b = r;
       }
       return a;
    }
    public static int getFibo(long n){
        if( n == 0){
            return 0;
        }
        if( n == 1){
            return 1;
        }
        if( n == 2){
            return 1;
        }
        return getFibo(n-2)+getFibo(n-1);
    }
    public static void rotate2(int[] nums,int k)  {
        int n = nums.length;
        int[] result = new int[n];
        for(int i=0;i<n;i++){
            result[(i+k)%n] = nums[i];
        }
        System.arraycopy(result,0,nums,0,nums.length);
//        for (int i = 0; i <nums.length ; i++) {
//            nums[i] = result[i];
//        }
    }
    public static void rotate(int[] nums, int k) {
        k %= nums.length; // why ? for the overflow
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while(start<=end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
