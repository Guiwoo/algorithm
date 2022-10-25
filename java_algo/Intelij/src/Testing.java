import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Testing{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();
        int ans = args.length;
        for (int i = 0; i < input.length(); i++) {
            if(isPal(input.substring(i))){
                break;
            }
            ans++;
        }
        System.out.println(ans);
    }
    static boolean isPal(String s){
        int start = 0;
        int last = s.length()-1;
        while(start<=last){
            if(s.charAt(start) != s.charAt(last)) return false;
            start++;
            last--;
        }
        return true;
    }
}



