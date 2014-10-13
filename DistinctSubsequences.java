

/**
 * Distinct SubsequencesOct 19 '12 Given a string S and a string T, count the
 * number of distinct subsequences of T in S.
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 * 
 * Here is an example: S = "rabbbit", T = "rabbit"
 * 
 * Return 3.
 * 
 * f(i, j) = f(i - 1, j) + S[i] == T[j]? f(i - 1, j - 1) : 0 Where f(i, j) is
 * the number of distinct sub-sequence for T[0:j] in S[0:i].
 */

public class DistinctSubsequences {
	public int numDistinct(String S, String T) {
		int[][] f = new int[S.length() + 1][T.length() + 1];
		for (int k = 0; k < S.length(); k++)
			f[k][0] = 1;
		for (int i = 1; i <= S.length(); i++) {
			for (int j = 1; j <= T.length(); j++) {
				if (S.charAt(i - 1) == T.charAt(j - 1)) {
					f[i][j] += f[i - 1][j] + f[i - 1][j - 1];
				} else {
					f[i][j] += f[i - 1][j];
				}
			}
		}
		return f[S.length()][T.length()];
	}
}



题目大意：删除S中某些位置的字符可以得到T，总共有几种不同的删除方法

设S的长度为lens，T的长度为lent

算法1：递归解法，首先，从个字符串S的尾部开始扫描，找到第一个和T最后一个字符相同的位置k，那么有下面两种匹配：a. T的最后一个字符和S[k]匹配，b. T的最后一个字符不和S[k]匹配。a相当于子问题:从S[0...lens-2]中删除几个字符得到T[0...lent-2]，b相当于子问题：从S[0...lens-2]中删除几个字符得到T[0...lent-1]。那么总的删除方法等于a、b两种情况的删除方法的和。递归解法代码如下，但是通过大数据会超时：


复制代码
 1 class Solution {
 2 public:
 3     int numDistinct(string S, string T) {
 4         // IMPORTANT: Please reset any member data you declared, as
 5         // the same Solution instance will be reused for each test case.
 6         return numDistanceRecur(S, S.length()-1, T, T.length()-1);
 7     }
 8     int numDistanceRecur(string &S, int send, string &T, int tend)
 9     {
10         if(tend < 0)return 1;
11         else if(send < 0)return 0;
12         while(send >= 0 && S[send] != T[tend])send--;
13         if(send < 0)return 0;
14         return numDistanceRecur(S,send-1,T,tend-1) + numDistanceRecur(S,send-1,T,tend);
15     }
16 };
复制代码
算法2：动态规划，设dp[i][j]是从字符串S[0...i]中删除几个字符得到字符串T[0...j]的不同的删除方法种类，有上面递归的分析可知，动态规划方程如下

如果S[i] = T[j], dp[i][j] = dp[i-1][j-1]+dp[i-1][j]
如果S[i] 不等于 T[j], dp[i][j] = dp[i-1][j]
初始条件：当T为空字符串时，从任意的S删除几个字符得到T的方法为1
代码如下：                                                                                     本文地址

复制代码
 1 class Solution {
 2 public:
 3     int numDistinct(string S, string T) {
 4         // IMPORTANT: Please reset any member data you declared, as
 5         // the same Solution instance will be reused for each test case.
 6         int lens = S.length(), lent = T.length();
 7         if(lent == 0)return 1;
 8         else if(lens == 0)return 0;
 9         int dp[lens+1][lent+1];
10         memset(dp, 0 , sizeof(dp));
11         for(int i = 0; i <= lens; i++)dp[i][0] = 1;
12         for(int i = 1; i <= lens; i++)
13         {
14             for(int j = 1; j <= lent; j++)
15             {
16                 if(S[i-1] == T[j-1])
17                     dp[i][j] = dp[i-1][j-1]+dp[i-1][j];
18                 else dp[i][j] = dp[i-1][j];
19             }
20         }
21         return dp[lens][lent];
22     }
23 };
复制代码


题解：

 这道题首先引用我忘记在哪里看到的一句话：

 “When you see string problem that is about subsequence or matching, dynamic programming method should come to your mind naturally. ”

 所以这种类型题可以多往DP思考思考。

 首先设置动态规划数组dp[i][j]，表示S串中从开始位置到第i位置与T串从开始位置到底j位置匹配的子序列的个数。

 如果S串为空，那么dp[0][j]都是0；

 如果T串为空，那么dp[i][j]都是1，因为空串为是任何字符串的字串。

 可以发现规律，dp[i][j] 至少等于 dp[i][j-1]。

 当i=2，j=1时，S 为 ra，T为r，T肯定是S的子串；这时i=2，j=2时，S为ra，T为rs，T现在不是S的子串，当之前一次是子串所以现在计数为1.

 

同时，如果字符串S[i-1]和T[j-1]（dp是从1开始计数，字符串是从0开始计数）匹配的话，dp[i][j]还要加上dp[i-1][j-1]

 例如对于例子： S = "rabbbit", T = "rabbit"

 当i=2，j=1时，S 为 ra，T为r，T肯定是S的子串；当i=2，j=2时，S仍为ra，T为ra，这时T也是S的子串，所以子串数在dp[2][1]基础上加dp[1][1]。

 

代码如下：

复制代码
 1     public int numDistinct(String S, String T) {
 2         int[][] dp = new int[S.length() + 1][T.length() + 1];
 3         dp[0][0] = 1;//initial
 4         
 5         for(int j = 1; j <= T.length(); j++)//S is empty
 6             dp[0][j] = 0;
 7             
 8         for (int i = 1; i <= S.length(); i++)//T is empty
 9             dp[i][0] = 1;
10            
11         for (int i = 1; i <= S.length(); i++) {
12             for (int j = 1; j <= T.length(); j++) {
13                 dp[i][j] = dp[i - 1][j];
14                 if (S.charAt(i - 1) == T.charAt(j - 1)) 
15                     dp[i][j] += dp[i - 1][j - 1];
16             }
17         }
18      
19         return dp[S.length()][T.length()];
20     }
复制代码

