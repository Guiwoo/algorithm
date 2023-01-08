import java.io.*;
import java.util.Arrays;

public class Pass {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        int[] arr = {1,2,3};
        int[] out = new int[2];
        boolean[] visit = new boolean[3];
        combination(0,arr,visit,out,0,0);
    }
    static void combination(int start,int[] arr,boolean[] visit,int[] out,int depth,int res){
        if(depth == out.length){
            System.out.println(Arrays.toString(out));
            System.out.println(res);
            return;
        }else{
            for(int i= start; i<arr.length;i++){
                if(!visit[i]){
                    visit[i] = true;
                    out[depth] = arr[i];
                    res += arr[i];
                    combination(i+1,arr,visit,out,depth+1,res);
                    visit[i] = false;
                    res -= arr[i];
                }
            }
        }
    }
}



