package programmers.wello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Wello {
    public static void main(String[] args) throws IOException {
        Sol s = new Sol();
        Sol2 s2 = new Sol2();
        Sol7 s7 = new Sol7();
        int maze = s7.getNumberOfMovies("maze");
        System.out.println(maze);
    }
}

class Sol7{
    public int getNumberOfMovies(String substr) throws IOException {
        String BASEURL =
                "https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=";
        URL url = new URL(BASEURL + substr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setDoOutput(true);
        StringBuffer sb = new StringBuffer();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while(br.ready()) {
                sb.append(br.readLine());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String s = sb.toString();
        int total = s.indexOf("total");
        int next = s.indexOf("total_pages");
        return Integer.parseInt(s.substring(total+7, next-2));
    }
}
class Sol{
    class People implements Comparable<People> {
        String firstName;
        String fullName;
        int num;

        public People(String firstName, String fullName, int num) {
            this.firstName = firstName;
            this.fullName = fullName;
            this.num = num;
        }

        @Override
        public int compareTo(People o) {
            if (this.firstName.equals(o.firstName)) {
                return this.num - o.num;
            }
            return  this.firstName.compareTo(o.firstName);
        }
    }
    String[] romam1 = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    String[] romam2 = {"X", "XX", "XXX", "XL", "L"};
    HashMap<String, Integer> map = new HashMap<>();

    public List<String> sortRoman(List<String> names) {
        List<String> result = new ArrayList<>();
        init();

        PriorityQueue<People> people = new PriorityQueue<>();

        for (int i = 0; i < names.size(); i++) {
            String[] s = names.get(i).split(" ");
            Integer integer = map.get(s[1]);

            people.offer(new People(s[0], names.get(i), integer));
        }

        while (!people.isEmpty()) {
            result.add(people.poll().fullName);
        }

        return result;
    }

    public void init() {
        for (int i = 0; i < romam1.length; i++) {
            map.put(romam1[i], i + 1);
        }

        for (int i = 0; i < romam2.length; i++) {
            map.put(romam2[i], (i + 1) * 10);
            for (int j = 0; j < romam1.length; j++) {
                map.put(romam2[i] + romam1[j], (i + 1) * 10 + j + 1);
            }
        }
    }
}
class Sol2{
    public String isPangram(List<String> pangram) {
        String answer = "";
        for (String p : pangram) {
            boolean isPan = true;
            int[] alpha = new int[26];
            char[] arr = p.toCharArray();
            for (char c : arr) {
                if('0'<= c && c<= '9') alpha[c - '0']++;
            }
            for(int a :alpha){
                if( a == 0){
                    isPan = false;
                    break;
                }
            }
            answer+= isPan ? 1 : 0;
        }
        return answer;
    }
}
class Sol3{
    public int findLargestSquareSize(List<List<Integer>> samples) {
        int answer = 0, row = samples.size(), col = samples.get(0).size();
        int[][] map = new int[row+1][col+1];

        for (int i = 1; i <= samples.size(); i++) {
            for (int j = 1; j <= samples.get(0).size(); j++) {
                if(samples.get(i-1).get(j-1) != 0) {
                    int min = Math.min(Math.min(map[i - 1][j], map[i][j - 1]), map[i - 1][j - 1]);
                    map[i][j] = min + 1;

                    answer = Math.max(answer, min + 1);
                }
            }
        }
        return answer;
    }
}
class Sol5{

}

