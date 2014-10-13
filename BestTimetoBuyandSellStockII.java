

/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock
 * multiple times). However, you may not engage in multiple transactions at the
 * same time (ie, you must sell the stock before you buy again).
 */

public class BestTimetoBuyandSellStockII {
	public int maxProfit(int[] prices) {
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			int d = prices[i] - prices[i - 1];
			if (d > 0) {
				profit += d;
			}
		}
		return profit;
	}
}

//my version  贪心法S
//注意有两个数组，prices 和 price ，新建price是为了顾及到最后一个数
// 但是这样做 用了额外的空间  时间复杂度为O（n）


    public int maxProfit(int[] prices) {
        int n=prices.length;
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
              sum=sum+price[i]-low;
              low=price[i+1];
            }
           
        }
        return sum;
        
    }
	
	
	题解：

简单的方法就是一旦第二天的价格比前一天的高，就在前一天买入第二天卖出。代码如下：
public int maxProfit(int[] prices) {
    int total = 0;
    for (int i=1; i< prices.length; i++) {
        if (prices[i]>prices[i-1]){ 
            int profit = prices[i]-prices[i-1];
            total += profit;
        }
    }
    return total;
    }
	但是这个会违反“不能同一天买卖的规则”，例如3天的价格分别为1，2，3，那么按上述算法就会在2那天同一天买卖了。。。

 正确的做法是： 第1天买第3天卖。

 虽然两种方法数字结果是对的，但是逻辑不一样。。

 不过虽然这么说，这道题的本事仍然是让你找最大利润，并没有让你明确哪天买哪天卖。

 所以对面试官你仍然可以说，这种方法只是帮助我找到最大利润，我并没有说是要在同一天买卖，只是计算：所有第二天比前一天大的差值的合，我是为了找最大利润而已（画个趋势图讲解一下就行了。。）。

 

不过如果不是那样略有点投机取巧的话，干嘛非要每一次有一点点增长就加总和，带来不必要的解释麻烦？

何不先找到递减的局部最低点，再找到递增的局部最高点，算一次加和，然后再这么找？ 这样就能保证买卖不会在同一天了。。

代码如下：
public static int maxProfit(int[] prices) {
        int len = prices.length;
        if(len <= 1)
            return 0;
        
        int i = 0;
        int total = 0;
        while(i < len - 1){
            int buy,sell;
            //寻找递减区间的最后一个值（局部最小点）
            while(i+1 < len && prices[i+1] < prices[i])
                i++;
            //局部最小点作为买入点
            buy = i;
            
            //找下一个点(卖出点至少为下一个点）
            i++;
            //不满足。。继续往下找递增区间的最后一个值（局部最高点）
            while(i<len && prices[i] >= prices[i-1])
                i++;
            //设置卖出点
            sell = i-1;
            //计算总和
            total += prices[sell] - prices[buy];
        }
        return total;
    }