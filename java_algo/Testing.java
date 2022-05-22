public class Testing {
    public static void main(String[] args) {
        Practice p = new Practice();
        int answer = p.recur(1, 5, 4);
        System.out.println(answer);
    }
}

class Practice {
    int x = 4;

    public int recur(int start, int end, int target) {
        int mid = start + (end - start) / 2;
        if (start > end) {
            return start;
        }
        if (isBadVersion(mid)) {
            return recur(start, mid - 1, target);
        } else {
            return recur(mid + 1, end, target);
        }

    }

    public boolean isBadVersion(int n) {
        return n < 4 ? false : true;
    }

}