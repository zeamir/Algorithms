package DynamicProgramming;

public class BestTimeToBuyAndSellStock {

	class Solution {
		public int maxProfit(int[] prices) {
			if (prices.length <= 1) {
				return 0;
			}
			int minPrice = prices[0];
			int maxGain = 0;
			for (int i = 1; i < prices.length; i++) {
				minPrice = Math.min(prices[i], minPrice);
				maxGain = Math.max(maxGain, prices[i] - minPrice);
			}
			return maxGain;
		}
	}
}