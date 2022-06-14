import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        // triangle.add(new ArrayList(Arrays.asList(2)));
        // triangle.add(new ArrayList(Arrays.asList(3, 4)));
        // triangle.add(new ArrayList(Arrays.asList(6, 5, 7)));
        // triangle.add(new ArrayList(Arrays.asList(4, 1, 8, 3)));
        triangle.add(new ArrayList(Arrays.asList(-10)));
        Days13_02 d = new Days13_02();
        System.out.println(d.minimumTotal(triangle));
    }
}

class Days13_02 {
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int curValue = triangle.get(i).get(j);
                if (j == 0) {
                    int prevLayer = triangle.get(i - 1).get(0);
                    triangle.get(i).set(j, curValue + prevLayer);
                } else if (j == triangle.get(i).size() - 1) {
                    int prevLayer = triangle.get(i - 1).get(triangle.get(i - 1).size() - 1);
                    triangle.get(i).set(j, curValue + prevLayer);
                } else {
                    int prevLayerLeft = triangle.get(i - 1).get(j - 1);
                    int prevLayerRight = triangle.get(i - 1).get(j);
                    triangle.get(i).set(j, curValue + Math.min(prevLayerLeft, prevLayerRight));
                }
            }
        }
        for (List<Integer> tr : triangle) {
            System.out.println(tr);
        }
        return triangle.get(triangle.size() - 1).stream().min((x1, x2) -> x1 - x2).get();
    }
}
