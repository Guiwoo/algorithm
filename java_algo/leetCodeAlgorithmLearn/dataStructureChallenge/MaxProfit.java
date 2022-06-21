package dataStructureChallenge;

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
