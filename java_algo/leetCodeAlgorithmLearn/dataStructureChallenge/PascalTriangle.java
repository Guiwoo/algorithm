package dataStructureChallenge;

import java.util.*;

class PascalTriangle {
    public void sol(int numRows) {
        List<List<Integer>> result = generate(numRows);

        System.out.println(result);
    }

    List<List<Integer>> generate(int numRows) {
        List<List<Integer>> answer = new ArrayList();
        answer.add(new ArrayList(List.of(1)));

        for (int i = 1; i < numRows; i++) {
            ArrayList<Integer> willAdd = new ArrayList();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    willAdd.add(1);
                } else {
                    willAdd.add(answer.get(i - 1).get(j - 1) + answer.get(i - 1).get(j));
                }
            }
            answer.add(willAdd);
        }
        return answer;
    }
}
