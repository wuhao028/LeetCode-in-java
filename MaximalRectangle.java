/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing all ones and return its area.
 */

public class MaximalRectangle {
	public int maximalRectangle(char[][] matrix) {
		int rows = matrix.length;
		if (rows == 0)
			return 0;
		int maxArea = 0;
		int cols = matrix[0].length;
		int[][] map = new int[rows][cols];
		for (int j = 0; j < cols; j++) {
			map[0][j] = matrix[0][j] == '0' ? 0 : 1;
		}
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				map[i][j] = matrix[i][j] == '0' ? map[i - 1][j]
						: map[i - 1][j] + 1;
			}
		}
		int[] row = new int[cols];
		for (int i = 0; i < rows; i++) {
			for (int j = i; j < rows; j++) {
				for (int k = 0; k < cols; k++) {
					row[k] = map[j][k] - (i == 0 ? 0 : map[i - 1][k]);
				}
				int count = 0;
				for (int k = 0; k < cols; k++) {
					if (row[k] == j - i + 1) {
						maxArea = Math.max(maxArea, ++count * (j - i + 1));
					} else {
						maxArea = Math.max(maxArea, count * (j - i + 1));
						count = 0;
					}
				}
			}
		}
		return maxArea;
	}
}



 这道题可以应用之前解过的Largetst Rectangle in Histogram一题辅助解决。解决方法是：

按照每一行计算列中有1的个数，作为高度，当遇见0时，这一列高度就为0。然后对每一行计算 Largetst Rectangle in Histogram，最后得到的就是结果。

public int maximalRectangle(char[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        int[] height = new int[n];//对每一列构造数组
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j] == '0')//如果遇见0，这一列的高度就为0了
                    height[j] = 0;
                else
                    height[j] += 1;
            }
            max = Math.max(largestRectangleArea(height),max);
        }
        return max;
    }
    
    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int maxArea = 0;
        int[] h = new int[height.length + 1];
        h = Arrays.copyOf(height, height.length + 1);
        while(i < h.length){
            if(stack.isEmpty() || h[stack.peek()] <= h[i]){
                stack.push(i);
                i++;
            }else {
                int t = stack.pop();
                int square = -1;
                if(stack.isEmpty())
                    square = h[t]*i;
                else{
                    int x = i-stack.peek()-1;
                    square = h[t]*x;
                }
                maxArea = Math.max(maxArea, square);
            }
        }
        return maxArea;
    }

	
  分析：一般一个题目我首先会想想怎么暴力解决，比如这一题，可以枚举出所有的矩形，求出其中的面积最大者，
那么怎么枚举呢，如果分别枚举矩形的宽度和高度，这样还得枚举矩形的位置，复杂度至少为O(n^4) 
(计算复杂度是我们把matrix的行、列长度都泛化为n，下同)，我们可以枚举矩形左上角的位置，那么知道了矩形左上角的位置，
怎么计算以某一点为左上角的矩形的最大面积呢？举例如下，下面的矩阵我们以(0,0)为矩形的左上角：

1 1 1 1 0 0

1 1 1 0 1 1

1 0 1 0 1 1

0 1 1 1 1 1

1 1 1 1 1 1

矩形高度是1时，宽度为第一行中从第一个位置起连续的1的个数，为4，面积为4 * 1 = 4

矩形高度是2时，第二行从第一个位置起连续1的个数是3，宽度为min(3,4) = 3，面积为3*2 = 6

矩形高度为3时，第三行从第一个位置起连续1的个数是1，宽度为min(1,3) = 1，面积为1*3 = 3

矩形高度为4时，第四行从第一个位置起连续1的个数是0，宽度为min(0,1) = 0，面积为0*4 = 0

后面的行就不用计算了，因为上一行计算的宽度是0，下面所有宽度都是0

因此以(0,0)为左上角的矩形的最大面积是6，计算以某一点为左上角的矩形的最大面积复杂度是O(n)。

注意到上面我们用到了信息“从某一行某个位置开始连续的1的个数”，这个我们可以通过动态规划求得：设dp[i][j]是从点(i,j)开始，这一行连续1的个数，
动态规划方程如下： 

初始条件：dp[i][column-1] = (matrix[i][column-1] == '1') （column是matrix的列数）
dp[i][j] = (matrix[i][j] == '1') ?  1 + dp[i][j + 1] : 0 ，(从方程看出我们应该从每一行的后往前计算)
计算dp复杂度O(n^2)，枚举左上角位置以及计算以该位置为左上角的矩形最大面积复杂度是O(n^2*n)=O(n^3)，总的复杂度是O(n^3)

这个算法还可以优化，枚举到某个点时我们可以假设该点右下方全是1，得到一个假设最大面积，如果这个面积比当前计算好的面积还要小，
该点就可以直接跳过；在上面计算以某点为左上角的矩形面积时，也可以剪枝，剪枝方法同上。具体可以参考代码注释。

class Solution {
public:
    int maximalRectangle(vector<vector<char> > &matrix) {
        int row = matrix.size();
        if(row == 0)return 0;
        int column = matrix[0].size();
        int dp[row][column], res = 0;
        memset(dp, 0, sizeof(dp));
        //求出所有的dp值
        for(int i = 0; i < row; i++)
            dp[i][column-1] = (matrix[i][column-1] == '1');
        for(int i = 0; i < row; i++)
            for(int j = column - 2; j >= 0; j--)
                if(matrix[i][j] == '1')
                    dp[i][j] = 1 + dp[i][j + 1];
        //以每个点作为矩形的左上角计算所得的最大矩形面积
        for(int i = 0; i < row; i++)
        {
             
            for(int j = 0; j < column; j++)
            {
                //剪枝，column-j是最大宽度，row-i是最大高度
                if((column - j) * (row - i) <= res)break;
                int width = dp[i][j];
                for(int k = i; k < row && width > 0; k++)
                {
                    //剪枝,row-i是以点(i,j)为左上角的矩形的最大高度
                    if(width * (row - i) <= res)break;
                    //计算以(i.j)为左上角，上下边缘是第i行和第k行的矩形面积
                    if(width > dp[k][j])width = dp[k][j];//矩形宽度要取从第i行到第k行宽度的最小值
                    res = max(res, width * (k - i + 1));
                }
            }
        }
        return res;
    }
};
 

O(n^2)的解法：

回想leetcode的另一题计算直方图最大面积：Largest Rectangle in Histogram，它可以在O(n)时间内解决，这一题可以转化成求直方图最大面积问题，
对每一行中的每个位置，计算该位置所在列向上的1的连续个数，这样每一行中每个位置1的个数就形成了一个直方图，对每一行调用计算直方图面积的算法，
就可以求出最大的面积。下面代码中height就是保存每一行每个位置1的个数，并且和上面解法中求dp类似，每一行的height可以由上一行的height求得。

class Solution {
public:
    int maximalRectangle(vector<vector<char> > &matrix) {
        int row = matrix.size();
        if(row == 0)return 0;
        int column = matrix[0].size();
        int height[column+1], res = 0;
        memset(height, 0, sizeof(height));
        stack<int> S;
        for(int i = 0; i < row; i++)
        {
            stack<int>().swap(S);//清空栈
            bool flag = false;//防止同一height[j]被多次更新
            for(int j = 0; j <= column; j++)
            {
                if(j < column && flag == false)
                {//更新当前行第j列的height值
                    if(matrix[i][j] == '1')
                    {
                        if(i-1 >=0 && matrix[i-1][j] == '1')
                            height[j]++;
                        else height[j] = 1;
                    }
                    else height[j] = 0;
                }
                if (S.empty() || height[j] > height[S.top()])
                {
                    S.push(j);
                    flag = false;
                }
                else
                {
                     int tmp = S.top();
                     S.pop();
                     res = max(res, height[tmp] * (S.empty() ? j : j-S.top()-1));
                     j--; //保持此次循环中j不变
                     flag = true;//height[j]已经更新，无需再次更新
                }
            }
        }
        return res;
    }
};
 
 其实第一种解法也包含求直方图最大面积的思想，我们只是把直方图顺时针旋转了90度，大家可以好好想想看。