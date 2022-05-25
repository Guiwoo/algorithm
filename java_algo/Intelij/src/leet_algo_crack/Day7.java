package leet_algo_crack;

import java.util.HashMap;
import java.util.HashSet;

public class Day7 {
    public static void main(String[] args) {

    }


    class Lt733{
        public int[][] floodfill_clean_ver(int[][] image, int sr, int sc, int newColor) {
            if (image[sr][sc] == newColor) return image;
            fill(image, sr, sc, image[sr][sc], newColor);
            return image;
        }
        private void fill(int[][] image, int sr, int sc, int color, int newColor) {
            if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != color) return;
            image[sr][sc] = newColor;
            fill(image, sr + 1, sc, color, newColor);
            fill(image, sr - 1, sc, color, newColor);
            fill(image, sr, sc + 1, color, newColor);
            fill(image, sr, sc - 1, color, newColor);
        }
        public int[][] floodFill_my_ver(int[][] image, int sr, int sc, int newColor) {
            boolean[][] visit = new boolean[image.length][image[0].length];
            spread(image,sc,sr,visit,image[sr][sc],newColor);
            return image;
        }
        void spread(int[][] image,int x,int y,boolean[][] visit,int target,int change){
            int[] dx ={1,0,-1,0};
            int[] dy ={0,-1,0,1};

            if(image[y][x] == target && !visit[y][x]){
                image[y][x] = change;
                visit[y][x] = true;
                for (int i = 0; i <4 ; i++) {
                    if(x+dx[i] > image[0].length-1 || x+dx[i]<0 || y+dy[i]>image.length-1 || y+dy[i]<0){
                        continue;
                    }
                    spread(image,x+dx[i],y+dy[i],visit,target,change);
                }
            }
        }
    }
    class Lt695{
        public void maxAreaOfIsland(){
            int[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
            int result = 0;
            boolean[][] visit = new boolean[grid.length][grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    int target = islandCheck(grid,i,j,visit);
                    result = target >result ? target : result;
                    visit = new boolean[grid.length][grid[0].length];
                }
            }
        }
        public int islandCheck(int[][] grid,int i,int j,boolean[][] visit){
            HashMap<String,Integer> map = new HashMap<>();
            island(grid,i,j,visit,"first",map);
            System.out.println(map+" "+ "i :"+i+" j :"+j);
            if(map.containsKey("w")  && map.containsKey("e")&&map.containsKey("n")&&map.containsKey("s")){
                return map.get("w")+map.get("e")+map.get("n")+map.get("s");
            }
            return 0;
        }
        public void island(int[][] grid,int i,int j ,boolean[][] visit,String way,HashMap<String,Integer> map){
            if(i >grid.length-1 || i<0 || j> grid[0].length-1 || j<0){
                return;
            }else{
                if(grid[i][j] != 0 && !visit[i][j]){
                    if(way != "first"){
                        if(map.containsKey(way)){
                            map.put(way,map.get(way)+1);
                        }else{
                            map.put(way,1);
                        }
                    }
                    visit[i][j] = true;
                    island(grid,i+1,j,visit,"n",map);
                    island(grid,i-1,j,visit,"s",map);
                    island(grid,i,j+1,visit,"e",map);
                    island(grid,i,j-1,visit,"w",map);
                }
            }
        }
    }
}