/**
 * Follow up for "Unique Paths":
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 *
 * [
 *  [0,0,0],
 *  [0,1,0],
 *  [0,0,0]
 * ]
 * 
 * The total number of unique paths is 2.
 *
 * Note: m and n will be at most 100.
 *
 */
public class UniquePathsII {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		if (m == 0)
			return 0;
		int n = obstacleGrid[0].length;
		int[][] map = new int[m][n];
		map[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
		for (int i = 1; i < m; i++) {
			map[i][0] = obstacleGrid[i][0] == 1 ? 0 : map[i - 1][0];
		}
		for (int j = 1; j < n; j++) {
			map[0][j] = obstacleGrid[0][j] == 1 ? 0 : map[0][j - 1];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				map[i][j] = obstacleGrid[i][j] == 1 ? 0 : map[i - 1][j]
						+ map[i][j - 1];
			}
		}
		return map[m - 1][n - 1];
	}
}

这道题大体想法跟Unique Path是一样的。

只是要单独考虑下障碍物对整个棋盘的影响。

 

先看看初始条件会不会受到障碍物的影响。

假设整个棋盘只有一行，那么在第i个位置上设置一个障碍物后，说明位置i到最后一个格子这些路都没法走了。

如果整个棋盘只有一列，那么第i位置上的障碍物，也会影响从第i位置往后的路。

所以说明，在初始条件时，如果一旦遇到障碍物，障碍物后面所有格子的走法都是0。

 

再看求解过程，当然按照上一题的分析dp[i][j] = dp[i-1][j] + dp[i][j-1] 的递推式依然成立
（机器人只能向下或者向右走嘛）。但是，一旦碰到了障碍物，那么这时的到这里的走法应该设为0，
因为机器人只能向下走或者向右走，所以到这个点就无法通过。

 

处理完障碍物的特殊问题，依照unique paths改一下代码就好。

public int uniquePathsWithObstacles(int[][] obstacleGrid) {  
        int m = obstacleGrid.length;  
        int n = obstacleGrid[0].length;  
        
        if(m==0||n == 0)  
            return 0; 
        
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1)  
            return 0; 
            
        int[][] dp = new int[m][n]; 
        
        dp[0][0] = 1;  
        for(int i = 1; i < n; i++){  
            if(obstacleGrid[0][i] == 1)  
                dp[0][i] = 0;  
            else 
                dp[0][i] = dp[0][i-1];  
        }  
        
        for(int i = 1; i < m; i++){  
            if(obstacleGrid[i][0] == 1)  
                dp[i][0] = 0;  
            else 
                dp[i][0] = dp[i-1][0];  
        }  
        
        for(int i = 1; i < m; i++){  
            for(int j = 1; j < n; j++){  
                if(obstacleGrid[i][j] == 1)  
                    dp[i][j] = 0;  
                else  
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];  
            }  
        }  
        return dp[m-1][n-1];  
    }
	
	