
// package leetCodeAlgorithmLearn;
import java.util.*;

public class Testing {
  public static void main(String[] args) throws Exception {
    Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
    int idx = 1;
    while (true) {
      int x = gen();
      if (map.containsKey(x)) {
        break;
      }
      map.put(x, true);
      System.out.println(idx + "실행");
      idx++;
    }
  }

  public static int gen() {
    int x = 0;
    Random rand = new Random();
    for (int i = 0; i < 10; i++) {
      x = x * 10 + rand.nextInt(9);
      ;
    }
    return x;
  }
}