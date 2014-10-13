

import java.util.ArrayList;

/**
 * Given a triangle, find the minimum path sum from top to bottom. 
 * 
 * Each step you may move to adjacent numbers on the row below. 
 * 
 * For example, given the following triangle 
 * [   
 *     [2], 
 *    [3,4], 
 *   [6,5,7], 
 *  [4,1,8,3] 
 * ]
 * 
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * Note: 
 * 
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */







 
 
public class Triangle {
	public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
		int n = triangle.size() - 1;
		int[] path = new int[triangle.size()];
		for (int o = 0; o < triangle.get(n).size(); o++) {
			path[o] = triangle.get(n).get(o);
		}
		for (int i = triangle.size() - 2; i >= 0; i--) {
			for (int j = 0, t = 0; j < triangle.get(i + 1).size() - 1; j++, t++) {
				path[t] = triangle.get(i).get(t)
						+ Math.min(path[j], path[j + 1]);
			}
		}
		return path[0];
	}
}





    
    public int minimumTotal(List<List<Integer>> triangle) {
    if(triangle.size()==1)
        return triangle.get(0).get(0);
        
    int[] dp = new int[triangle.size()];

    //initial by last row 
    for (int i = 0; i < triangle.get(triangle.size() - 1).size(); i++) {
        dp[i] = triangle.get(triangle.size() - 1).get(i);
    }
 
    // iterate from last second row
    for (int i = triangle.size() - 2; i >= 0; i--) {
        for (int j = 0; j < triangle.get(i).size(); j++) {
            dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
        }
    }
 
    return dp[0];
}




Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

分析：从最小面一层开始往上计算，设dp[i][j]是以第i层j个元素为起点的最小路径和，动态规划方程如下

dp[i][j] = value[i][j] + max{dp[i-1][j], dp[i-1][j+1]}

因为每一层之和它下一层的值有关，因此只需要一个一位数组保存下层的值，代码如下：                                                     本文地址

复制代码
 1 class Solution {
 2 public:
 3     int minimumTotal(vector<vector<int> > &triangle) {
 4         // IMPORTANT: Please reset any member data you declared, as
 5         // the same Solution instance will be reused for each test case.
 6         const int rows = triangle.size();
 7         int dp[rows];
 8         for(int i = 0; i < rows; i++)
 9             dp[i] = triangle[rows-1][i];
10         for(int i = rows-2; i >= 0; i--)
11             for(int j = 0; j <= i; j++)
12                 dp[j] = triangle[i][j] + min(dp[j], dp[j+1]);
13         return dp[0];
14     }
15 };
复制代码












