import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Test {
    static ArrayList<ArrayList<Integer>> totalClassOfStd;
    public static void main(String[] args){
        System.out.println(solution(10,3,4,new int[]{3,3,4}));
    }
    static public long solution(int N, int M, int K, int[] capacity) {
        long answer =0;
        int[] caps = new int[3];

        for (int i=0;i<capacity.length;i++){
            caps[i] = capacity[i];
        }
        for (int i = capacity.length; i < 3; i++) {
            caps[i] = 0;
        }

        totalClassOfStd = getCaseStudents(caps,N);
        answer = getAllCasesOfStudents(0,N);
        return answer * perm(K,M);
    }
    static public ArrayList<ArrayList<Integer>> getCaseStudents(int[] caps,int totalStudents){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= caps[0]; i++) {
            for (int j = 0; j <= caps[1]; j++) {
                for (int k = 0; k <= caps[2]; k++) {
                    if(i+j+k == totalStudents){
                        ArrayList<Integer> classOfStd = new ArrayList<>();
                        classOfStd.add(i);
                        classOfStd.add(j);
                        classOfStd.add(k);
                        result.add(classOfStd);
                    }
                }
            }
        }
        return result;
    }
    static public int getAllCasesOfStudents(int answer,int totalStudent){
        for(ArrayList<Integer> classOfStd : totalClassOfStd){
            int firstClassStd = classOfStd.get(0);
            int secondClassStd = classOfStd.get(1);
            int thirdClassStd = classOfStd.get(2);

            long firstCase = comb(totalStudent,firstClassStd);
            long secondCase = comb(totalStudent-firstClassStd,secondClassStd);
            long thirdCase = comb(totalStudent-firstClassStd-secondClassStd,thirdClassStd);
            answer += firstCase*secondCase*thirdCase;
        }
        return answer;
    }
    static public long comb(int n,int r){
        long top =1;
        long bottom =1;
        for(int i=0; i<r;i++){
            top *= n-i;
            bottom *= i+1;
        }
        return top/bottom;
    }
    static public long perm(int n,int r){
        long result = 1;
        for(int i=0;i<r;i++){
            result *= n-i;
        }
        return result;
    }
}


