package leet.leet_algo_crack;
import java.io.IOException;

public class Day4 {
    public static void main(String[] args) throws IOException {
        String s = "Let's take LeetCode contest";
        char[] arr = s.toCharArray();
        int start = 0;
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i] == ' '){
                reverse(arr,start,i-1);
                start = i+1;
            }
        }
        reverse(arr,start,arr.length-1);
        System.out.println(String.valueOf(arr));
    }
    public static void reverse(char[] s,int start,int end){
        while(start<=end){
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
            start++;
            end--;
        }
    }
    class Lt_day4{
        public void Lt_344(){
            char[] s ={'H','E','L','L','O'};
            reverse_Lt_344(s);
        }
        public void reverse_Lt_344(char[] s){
            if(s.length == 1 || s.length == 0){
                return;
            }
            int left = 0;
            int right = s.length-1;
            while(left<=right){
                char tmp = s[left];
                s[left] = s[right];
                s[right] = tmp;
                left++;
                right--;
            }
        }

        public String Lt_557(String s){
            char[] arr = s.toCharArray();
            int start = 0;
            for (int i = 0; i <arr.length ; i++) {
                if(arr[i] == ' '){
                    reverse_Lt_344(arr,start,i-1);
                    start = i+1;
                }
            }
            reverse_Lt_344(arr,start,arr.length-1);
            return String.valueOf(arr);
        }
        public static void reverse_Lt_344(char[] s,int start,int end){
            while(start<=end){
                char tmp = s[start];
                s[start] = s[end];
                s[end] = tmp;
                start++;
                end--;
            }
        }
    }
}

