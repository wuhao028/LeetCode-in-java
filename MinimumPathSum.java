

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 */

public class MinimumPathSum {
	public int minPathSum(int[][] grid) {
		int[][] path = new int[grid.length][grid[0].length];
		path[0][0] = grid[0][0];
		for (int i = 1; i < grid.length; i++) {
			path[i][0] = path[i - 1][0] + grid[i][0];
		}
		for (int j = 1; j < grid[0].length; j++) {
			path[0][j] = path[0][j - 1] + grid[0][j];
		}
		for (int i = 1; i < grid.length; i++)
			for (int j = 1; j < grid[0].length; j++) {
				path[i][j] = Math.min(path[i - 1][j], path[i][j - 1])
						+ grid[i][j];
			}
		return path[grid.length - 1][grid[0].length - 1];
	}
}


public class Solution {
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        if(m==0 || n==0)
          return 0 ;
          
          int[][] sum=new int[m][n];
           sum[0][0]=grid[0][0];
        
             for(int i=1;i<n;i++){
                 sum[0][i]=grid[0][i]+sum[0][i-1];
             }
         
         

             for(int i=1;i<m;i++){
                 sum[i][0]=grid[i][0]+sum[i-1][0];
             }
         
        
        
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(sum[i-1][j]<sum[i][j-1])
                sum[i][j]=sum[i-1][j] + grid[i][j];
                else sum[i][j]=sum[i][j-1] + grid[i][j];
            }
        }

        return sum[m-1][n-1];
    }
}



典型的动态规划问题。

设dp[i][j]表示从左上角到grid[i][j]的最小路径和。那么dp[i][j] = grid[i][j] + min( dp[i-1][j], dp[i][j-1] );

下面的代码中，为了处理计算第一行和第一列的边界条件，我们令dp[i][j]表示从左上角到grid[i-1][j-1]的最小路径和，最后dp[m][n]是我们所求的结果


class Solution {
public:
    int minPathSum(vector<vector<int> > &grid) {
        int row = grid.size(),col;
        if(row == 0)return 0;
        else col = grid[0].size();
        vector<vector<int> >dp(row+1, vector<int>(col+1, INT_MAX));
        dp[0][1] = 0;
        for(int i = 1; i <= row; i++)
            for(int j = 1; j <= col; j++)
                dp[i][j] = grid[i-1][j-1] + min(dp[i][j-1], dp[i-1][j]);
        return dp[row][col];
    }
};
 

注意到上面的代码中dp[i][j] 只和上一行的dp[i-1][j]和上一列的dp[i][j-1]有关，因此可以优化空间为O（n）（准确来讲空间复杂度可以是O（min（row,col

）））                          本文地址

class Solution {
public:
    int minPathSum(vector<vector<int> > &grid) {
        int row = grid.size(),col;
        if(row == 0)return 0;
        else col = grid[0].size();
        vector<int >dp(col+1, INT_MAX);
        dp[1] = 0;
        for(int i = 1; i <= row; i++)
            for(int j = 1; j <= col; j++)
                dp[j] = grid[i-1][j-1] + min(dp[j], dp[j-1]);
        return dp[col];
    }
};
问题扩展

最大路径和只需要把上面的递推公式中的min换成max。

现在有个问题，如果两个人同时从左上角出发，目的地是右下角，两个人的路线不能相交（即除了出发点和终点外，两个人不同通过同一个格子），使得两条路径的和最大。（这和一个人先从左上角到右下角，再回到左上角是相同的问题）。

这是双线程动态规划问题：假设网格为grid，dp[k][i][j]表示两个人都走了k步，第一个人向右走了i步，第二个人向右走了j步 时的最大路径和（只需要三个变量就可以定位出两个人的位置grid[k-i][i-1] 、 grid[k-j][j-1]），那么

dp[k][i][j] = max(dp[k-1][i-1][j-1], dp[k-1][i][j], dp[k-1][i-1][j], dp[k-1][i][j-1]) + grid[k-i][i-1] + grid[k-j][j-1]  （我们假设在起始位置时就已经走了一步）

 

这个方程的意思是从第k-1步到第k步，可以两个人都向右走、都向下走、第一个向下第二个向右、第一个向右第二个向下，这四种走法中选择上一步中路径和最大的。

 

由于要保证两条路线不能相交，即两个人走的过程中，有一个人向右走的步数永远大于另一个人向右走的步数，我们不妨设第二个人向右走的步数较大，即dp[k][i][j]中j > i才是有效的状态。走到终点的步数为：网格的行数+网格的列数-1

 

需要注意的是：当走了k步时，某个人向右走的步数必须 > k - 网格的行数，如果向右走的步数 <= k-行数，那么向下走的步数 = k-向右走的步数 >= 行数，此时超出了网格的范围。由于我们假设了 j > i,因此只要保证 i > k-网格行数即可。

代码如下：

int max2PathSum(vector<vector<int> > grid)
{
    int row = grid.size(), col = grid[0].size();
    vector<vector<vector<int> > > dp(row+col, vector<vector<int> >(col+1, vector<int>(col+1, 0)));
    for(int step = 2; step <= row+col-2; step++)
        for(int i = max(1, step-row+1); i <= step && i <= col; i++)
            for(int j = i+1; j <= step && j <= col; j++)
            {
                 
                dp[step][i][j] = max(max(dp[step-1][i][j], dp[step-1][i-1][j-1]), max(dp[step-1][i-1][j], dp[step-1][i][j-1]));
                dp[step][i][j] += (grid[step-i][i-1] + grid[step-j][j-1]);
            }
    return dp[row+col-2][col-1][col] + 2*grid[row-1][col-1] + 2*grid[0][0];
}
 

我们最终的目标是dp[row+col-1][col][col] = max{dp[row+col-2][col-1][col-1], dp[row+col-2][col][col], dp[row+col-2][col-1][col], dp[row+col-2][col][col-1]} + 2*grid[row-1][col-1]

由于dp[row+col-2][col-1][col-1], dp[row+col-2][col][col], dp[row+col-2][col][col-1]都是无效的状态（dp[k][i][j]中j > i才是有效的状态），

所以dp[row+col-1][col][col]  = dp[row+col-2][col-1][col] + 2*grid[row-1][col-1]，代码中最后结果还加上了所在起点的的网格值。

由以上可知，循环中最多只需要求到了dp[row+col-2][][]。

 

nyoj中 传纸条（一）就是这个问题，可以在这一题中测试上述函数的正确性，测试代码如下：

int main()
{
    int n;
    scanf("%d",&n);
    for(int i = 1; i <= n; i++)
    {
        int row, col;
        scanf("%d%d", &row, &col);
        vector<vector<int> >grid(row, vector<int>(col));
        for(int a = 0; a < row; a++)
            for(int b = 0; b < col; b++)
                scanf("%d", &grid[a][b]);
        printf("%d\n", max2PathSum(grid));
    }
    return 0;
}
 

这个问题还可以使用最小费用流来解决，具体可以参考here