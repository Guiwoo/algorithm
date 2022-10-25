package leet;

import java.util.*;

public class gardenNoAdj {
    public static void main(String[] args) {
        gardenNoAdj g = new gardenNoAdj();
        int[][] paths = {{4,1},{4,2},{4,3},{2,5},{1,2},{1,5}};
        int[] ints = g.gardenNoAdj(5, paths);
        System.out.println(Arrays.toString(ints));
    }
    //어떤 꽃을 사용했는지,
    //현재 어디인지 기록만하면됨
    private List<Set<Integer>> planted = new ArrayList<>();
    private List<Deque<Integer>> answers = new ArrayList<>();

    private void init(int[][] paths,int n){
        for (int i = 0; i < n+1; i++) {
            planted.add(new HashSet<>());
        }
        for (int[] ints : paths) {
            planted.get(ints[0]).add(ints[1]);
            planted.get(ints[1]).add(ints[0]);
        }
    }

    public int[] gardenNoAdj(int n, int[][] paths) {
        int[] answer = new int[n];
        for (int i = 0; i < Math.min(n,4); i++) {
            answer[i] = i+1;
        }
        if(n<=4) return answer;
        init(paths,n);
        for (int i = 5; i <= n; i++) {
            if(planted.get(i).size() == 0){
                answer[i-1] = 1;
            }else{
                answer[i-1] = getType(planted.get(i),answer);
            }
        }
        return answer;
    }

    int getType(Set<Integer> adj,int[] answer){
        boolean[] type = new boolean[5];

        for(int adjs : adj){
            int flowerType = answer[adjs-1];
            if(flowerType>0){
                type[flowerType] = true;
            }
        }
        for (int flowerType = 1; flowerType <= 4 ; flowerType++) {
            if(!type[flowerType]){
                return flowerType;
            }
        }

        return 1;
    }

}
