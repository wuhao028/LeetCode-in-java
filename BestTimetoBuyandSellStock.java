

/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum
 * profit.
 */
 
 // 保证了max 始终在min的后面
public class BestTimetoBuyandSellStock {
	public int maxProfit(int[] prices) {
		int lowest = 0;
		int maxProfit = 0;
		if (prices.length > 0) {
			lowest = prices[0];
			for (int i = 0; i < prices.length; i++) {
				if (lowest > prices[i]) {
					lowest = prices[i];
				}
				maxProfit = Math.max(maxProfit, prices[i] - lowest);
			}
		}
		return maxProfit;
	}
}

//下面为上述代码的简洁版

public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE,max=0;
        for(int i=0;i<prices.length;i++){
            
            min=Math.min(min,prices[i]);
            max=Math.max(max,prices[i]-min);
        }
        return max;
    }