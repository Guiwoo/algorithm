package zerobase.codingTest_week_17;
import java.util.*;

public class codingTest_week_17 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int N = 10;
        int M = 15;
        int[][] shapes = {{5,4,3},{8,5,4}};
        int[] colors = {50,200};
        s.solution(N,M,shapes,colors);
    }
}

class Solution1 {
    public int solution(int[] nums) {
        int first = 0,second =0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] > first){
                first = nums[i];
            } else{
                if(nums[i] >second){
                    second = nums[i];
                }
            }
        }
        if(second == 0) return (first-1)*(first-1);
        return (first-1)*(second-1);
    }
}

class Solution2 {
    public int solution(int[][] value) {
        List<int[]> list = new ArrayList<>();
        int player1 = 0;
        int player2 = 0;
        for(int[] x : value){
            list.add(x);
        }
        int cnt = value.length/2;
        while(cnt > 0){
            int[] first = {0,0};
            int[] second  = {0,0};
            for(int[] l : list){
                boolean picked = false;
                if(first[0] < l[0]){
                    first = l;
                    picked = true;
                }else if(first[0] == l[0]){
                    if(first[1] < l[1]){
                        first = l;
                        picked = true;
                    }
                }
                if(picked) continue;;
                if(second[1] < l[1]){
                    second = l;
                }else if(second[1] == l[1]){
                    if(second[0] < l[0]){
                        second = l;
                    }
                }
            }
            list.remove(first);
            list.remove(second);
            cnt--;
            System.out.println(Arrays.toString(first));
            System.out.println(Arrays.toString(second));
            player1 += first[0];
            player2 += second[1];
        }
        System.out.println(player1 + " "+ player2);
        return player1 > player2 ? 1 : player1 == player2 ? 0 : -1;
    }

    boolean isContain(int[] target,List<int[]> list){
        for(int[] x : list){
            if(x[0] == target[0] && x[1] == target[1]){
                list.remove(x);
                return true;
            }
        }
        return false;
    }
}

class Solution3 {
    public int solution(int dest, int start, int[] station, int[] fuel) {
        if(start >= dest) return 0;
        Arrays.sort(fuel);
        int total = start;
        int cnt = 0;
        for (int i = fuel.length-1; i >=0; i--) {
            if(total >= dest){
                return cnt;
            }else {
                total += fuel[i];
                cnt++;
            }
        }
        return -1;
    }
}

class Solution {
    class Help{
        int color;
        int[] shape;

        public Help(int color, int[] shape) {
            this.color = color;
            this.shape = shape;
        }

        @Override
        public String toString() {
            return color + " "+ Arrays.toString(shape);
        }
    }
    int[][] map;
    int M;
    int N;
    public int[][] solution(int a, int b, int[][] shapes, int[] colors) {
        map = new int[a][b];
        for(int[]x : map){
            Arrays.fill(x,11);
        }
        M=a;
        N=b;
        Queue<Help> q = new PriorityQueue<>((x,y)-> x.color - y.color);
        for (int i = 0; i < shapes.length; i++) {
            q.offer(new Help(colors[i],shapes[i]));
        }
        while(!q.isEmpty()){
            Help cur = q.poll();
            int row = cur.shape[0];
            int col = cur.shape[1];
            int r = cur.shape[2];
            fillSquare(row,col,r,cur.color);
        }
        for(int[] x : map){
            System.out.println(Arrays.toString(x));
        }
        return null;
    }
    void fillSquare(int row,int col,int r,int color){
        int left = Math.max(row-r ,0);
        int right = Math.min(row+r, N);
        int top = Math.max(col-r,0);
        int bottom = Math.min(col+r,M);

        List<int[]> skip = new ArrayList<>();
        //왼쪽모서리
        int[] leftTop = {top,left};
        int[] rightTop = {top,right};
        int[] leftBottom = {bottom,left};
        int[] rightBottom = {bottom,right};

        //왼쪽모서리
        int cnt = r/2;
        boolean even = r%2 == 0;
        for(int i=top;i<bottom+1;i++){
            for (int j = left; j < right+1; j++) {
                //왼쪽 모서리 인경우
                if(i < top+cnt && j < left+cnt) continue;
                //오른쪽 위에 모서리 경우

                //왼쪽 아래 모서리
                if(i > bottom-cnt && j <left+cnt)continue;
                map[i][j] = color;
            }
        }
    }
}