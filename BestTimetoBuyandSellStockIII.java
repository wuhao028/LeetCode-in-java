

/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most two
 * transactions.
 * 
 * Note: You may not engage in multiple transactions at the same time (ie, you
 * must sell the stock before you buy again).
 */

public class BestTimetoBuyandSellStockIII {
	public int maxProfit(int[] prices) {
		int length = prices.length;
		if (length == 0) return 0;
		int profit = 0;
		int lowest = prices[0];
		int[] left = new int[length];
		int[] right = new int[length];
		for (int i = 1; i < length; i++) {
			if (prices[i] < lowest) {
				lowest = prices[i];
			} else if (prices[i] - lowest > profit) {
				profit = prices[i] - lowest;
			}
			left[i] = profit;
		}
		profit = 0;
		int topest = prices[length - 1];
		for (int j = length - 2; j >= 0; j--) {
			if (prices[j] > topest) {
				topest = prices[j];
			} else if (topest - prices[j] > profit) {
				profit = topest - prices[j];
			}
			right[length - 1 - j] = profit;
		}
		profit = 0;
		for (int k = 0; k < length; k++) {
			int p = left[k] + right[length - 1 - k];
			if (p > profit) {
				profit = p;
			}
		}
		return profit;
	}
}

题解： 

根据题目要求，最多进行两次买卖股票，而且手中不能有2只股票，就是不能连续两次买入操作。

所以，两次交易必须是分布在2各区间内，也就是动作为：买入卖出，买入卖出。

进而，我们可以划分为2个区间[0,i]和[i,len-1]，i可以取0~len-1。

那么两次买卖的最大利润为：在两个区间的最大利益和的最大利润。

一次划分的最大利益为：Profit[i] = MaxProfit(区间[0,i]) + MaxProfit(区间[i,len-1]);

最终的最大利润为：MaxProfit(Profit[0], Profit[1], Profit[2], ... , Profit[len-1])。

 

代码如下：
public int maxProfit(int[] prices) {  
        if(prices == null || prices.length <= 1){  
            return 0;  
        }  
        int len = prices.length;  
        int maxProfit = 0;  
        int min = prices[0];  
        int arrayA[] = new int[len];  
//依次找出在i处前半段最大值
        for(int i=1;i<prices.length;i++){
            min=Math.min(min,prices[i]);
            arrayA[i]=Math.max(arrayA[i-1],prices[i]-min);
        }
        
        int max = prices[len-1];  
        int arrayB[] = new int[len]; 
//依次找出在i处后半段最大值		
        for(int i = len-2; i >= 0; i--){
            max = Math.max(prices[i],max);
            arrayB[i] = Math.max(max-prices[i],arrayB[i+1]);
        }  
        
        for(int i = 0; i < len; i++){  
            maxProfit = Math.max(maxProfit,arrayA[i] + arrayB[i]);
        }  
        
        return maxProfit;  
    }




之前在II的基础上修改，使之只保存最大的两个sum，但是在如下情况出错：
Input:	[1,2,4,2,5,7,2,4,9,0]
Output:	12
Expected:	13
代码如下：
        public int maxProfit(int[] prices) {
        int n=prices.length;
        int[] max=new int[2];
        max[0]=0;
        max[1]=0;
        if(n==0 || n==1){return 0;}
        if(n==2){
            if(prices[1]>prices[0]) return prices[1]-prices[0];
            else return 0;
        }
        int sum=0;
        int low=prices[0];
        int[] price=new int[n+1];
        for(int i=0;i<n;i++){price[i]=prices[i];}
        
        price[n]=0;
        for(int i=1;i<n;i++){
            
            if(price[i]<low){low=price[i];}
            if(price[i]>low && price[i]>price[i+1]){
              sum=price[i]-low;
              if(sum>max[1]){
                  max[0]=max[1];
                  max[1]=sum;
                  low=price[i+1];
                  continue;
              }
              if(sum>max[0]){
                  max[0]=sum;
                  low=price[i+1];
             
              }
             
              low=price[i+1];
              
            }
           
        }
        return max[0]+max[1];
        
    }
