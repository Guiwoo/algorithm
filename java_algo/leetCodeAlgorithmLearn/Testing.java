import java.util.PriorityQueue;

public class Testing {
    public static void main(String[] args) {
        MaxPorfit m = new MaxPorfit();
        int rs = m.maxProfit(new int[] { 7, 1, 5, 3, 6, 4 });
        System.out.println(rs);
    }
}

class MaxPorfit {
    public int maxProfit(int[] prices) {
        int lowestPrice = Integer.MAX_VALUE;
        int profit = 0;
        int current = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < lowestPrice) {
                lowestPrice = prices[i];
            }
            current = prices[i] - lowestPrice;
            if (profit < current) {
                profit = current;
            }
        }
        return profit;
    }
}
