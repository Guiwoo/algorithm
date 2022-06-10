public class Test {
    public static void main(String[] args) {
        // helper(new int[] { 1, 2, 3, 4 }, 0, 0);
        System.out.println(1 << 20);
    }

    public static void helper(int[] arr, int sum, int idx) {
        if (idx >= arr.length) {
            if (sum == 5) {
                System.out.println("Found 5");
            }
            return;
        }
        helper(arr, sum + arr[idx], idx + 1);
        helper(arr, sum, idx + 1);
    }
}
