package pracitGroup.practice0720;

import java.util.*;

public class Practice0720 {

    public static void main(String[] args) {
        ArrayList<List<String>> eq = new ArrayList();
        eq.add(new ArrayList(List.of("a", "b")));
        eq.add(new ArrayList(List.of("b", "c")));

        double[] values = { 2.0, 3.0 };
        List<List<String>> query = new ArrayList();
        query.add(new ArrayList<>(List.of("a", "c")));
        query.add(new ArrayList<>(List.of("b", "a")));
        query.add(new ArrayList<>(List.of("a", "e")));
        query.add(new ArrayList<>(List.of("a", "a")));
        query.add(new ArrayList<>(List.of("x", "x")));

    }
}

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // first handle union
        String[] alpha = new String[26];
        for (int i = 0; i < values.length; i++) {
            String fr = equations.get(i).get(0);
            String ba = equations.get(i).get(1);
            // fr/ba = valuse[i]; fr = values[i]*ba;
            alpha[fr.charAt(0) - 'a'] = values[i] + ba;
        }
        System.out.println(Arrays.toString(alpha));
        return values;
    }
}
