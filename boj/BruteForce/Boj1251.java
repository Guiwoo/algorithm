package BruteForce;

import java.io.*;
import java.util.*;

public class Boj1251 {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        bf.close();

        List<String> result = new ArrayList<>();

        String[] willAdd = new String[3];
        for (int i = 1; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                willAdd[0] = s.substring(0, i);
                willAdd[1] = s.substring(i, j);
                willAdd[2] = s.substring(j, s.length());

                String rs = "";
                for (String x : willAdd) {
                    StringBuilder sb = new StringBuilder(x);
                    rs += sb.reverse().toString();
                }
                result.add(rs);
            }
        }

        Collections.sort(result);
        System.out.println(result.get(0));
    }
}