

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome. 
 * Return the minimum cuts needed for a palindrome partitioning of s. 
 * 
 * For example, given s = "aab", Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * 
 * D[i] = min(1 + D[j + 1], D[i]) i<=j<n s[i, j] is palindrome
 */

public class PalindromePartitioningII {
	public int minCut(String s) {
		int length = s.length();
		int[] dp = new int[length + 1];
		boolean[][] parlin = new boolean[length][length];

		for (int i = length; i >= 0; i--) {
			dp[i] = length - i;
		}

		for (int i = length - 1; i >= 0; i--) {
			for (int j = i; j < length; j++) {
				if (s.charAt(i) == s.charAt(j)
						&& (j - i < 2 || parlin[i + 1][j - 1])) {
					parlin[i][j] = true;
					dp[i] = Math.min(dp[i], dp[j + 1] + 1);
				}
			}
		}

		return dp[0] - 1;
	}
}


/*
 这道题需要用动态规划做，如果用I的DFS的方法做会TLE。

 

 首先设置dp变量 cuts[len+1]。cuts[i]表示从第i位置到第len位置（包含，即[i, len])的切割数（第len位置为空）。

 初始时，是len-i。比如给的例子aab，cuts[0]=3，就是最坏情况每一个字符都得切割：a|a|b|' '。cuts[1] = 2, 即从i=1位置开始，a|b|' '。

 cuts[2] = 1 b|' '。cuts[3]=0,即第len位置，为空字符，不需要切割。

 

 上面的这个cuts数组是用来帮助算最小cuts的。

 

 还需要一个dp二维数组matrixs[i][j]表示字符串[i,j]从第i个位置（包含）到第j个位置（包含） 是否是回文。

 如何判断字符串[i,j]是不是回文？

 1. matrixs[i+1][j-1]是回文且 s.charAt(i) == s.charAt(j)。

 2. i==j（i，j是用一个字符）

 3. j=i+1（i，j相邻）且s.charAt(i) == s.charAt(j)

 

 当字符串[i,j]是回文后，说明从第i个位置到字符串第len位置的最小cut数可以被更新了，

 那么就是从j+1位置开始到第len位置的最小cut数加上[i,j]|[j+1,len - 1]中间的这一cut。

 即，Math.min(cuts[i], cuts[j+1]+1) 

 最后返回cuts[0]-1。把多余加的那个对于第len位置的切割去掉，即为最终结果。
 */
 
 // 复杂度为O（n^2）的动态规划
public int minCut(String s) {  
        int min = 0;  
        int len = s.length();  
        boolean[][] matrix = new boolean[len][len];  
        int cuts[] = new int[len+1];  
          
        if (s == null || s.length() == 0)  
            return min;  
         
        for (int i=0; i<len; ++i){  
            cuts[i] = len - i;  //cut nums from i to len [i,len]
        }  
          
        for (int i=len-1; i>=0; --i){  
            for (int j=i; j<len; ++j){  
                if ((s.charAt(i) == s.charAt(j) && (j-i<2))  
                        || (s.charAt(i) == s.charAt(j) && matrix[i+1][j-1]))  
                {  
                    matrix[i][j] = true;  
					// i到j一段，j到最后为cuts[j+1]
                    cuts[i] = Math.min(cuts[i], cuts[j+1]+1);  
                }  
            }  
        }  
        min = cuts[0]-1;  
        return min;  
    }
	
	
	
	


	
算法1：在上一题的基础上，我们很容易想到的是在DFS时，求得树的最小深度即可（
遍历时可以根据当前求得的深度进行剪枝），但是可能是递归层数太多，大数据时运行超时，也贴上代码：


复制代码
 1 class Solution {
 2 public:
 3     int minCut(string s) {
 4         // IMPORTANT: Please reset any member data you declared, as
 5         // the same Solution instance will be reused for each test case.
 6         int len = s.length();
 7         if(len <= 1)return 0;
 8         //f[i][j] = true表示以i为起点，长度为j的子串是回文
 9         bool **f = new bool*[len];
10         for(int i = 0 ; i < len; i++)
11         {
12             f[i] = new bool[len+1];
13             for(int j = 0; j < len+1; j++)
14                 f[i][j] = 0;
15             f[i][1] = true;
16         }
17         for(int k = 2; k <= len; k++)
18         {
19             for(int i = 0; i <= len-k; i++)
20             {
21                 if(k == 2)f[i][2] = (s[i] == s[i+1]);
22                 else f[i][k] = f[i+1][k-2] && (s[i] == s[i+k-1]);
23             }
24         }
25         int res = len, depth = 0;
26         DFSRecur(s, f, 0, res, depth);
27         for(int i = 0 ; i < len; i++)
28             delete [](f[i]);
29         delete []f;
30         return res - 1;
31     }
32     void DFSRecur(const string &s, bool **f, int i, 
33             int &res, int &currdepth)
34     {
35         int len = s.length();
36         if(i >= len){res = res<=currdepth? res:currdepth; return;}
37         for(int k = 1; k <= len - i; k++)
38             if(f[i][k] == true)
39             {
40                 currdepth++;
41                 if(currdepth < res)
42                     DFSRecur(s, f, i+k, res, currdepth);
43                 currdepth--;
44             }
45                 
46     }
47 
48 };
复制代码
算法2：设f[i][j]是i为起点，长度为j的子串的最小分割次数，f[i][j] = 0时，该子串是回文，f的动态规划方程是：

f[i][j] = min{f[i][k] + f[i+k][j-k] +1} ,其中 1<= k <j

这里f充当了两个角色，一是记录子串是否是回文，二是记录子串的最小分割次数，可以结合上一题的动态规划方程，
算法复杂度是O(n^3), 还是大数据超时，代码如下：


复制代码
 1 class Solution {
 2 public:
 3     int minCut(string s) {
 4         // IMPORTANT: Please reset any member data you declared, as
 5         // the same Solution instance will be reused for each test case.
 6         int len = s.length();
 7         if(len <= 1)return 0;
 8         //f[i][j] = true表示以i为起点，长度为j的子串的最小切割次数
 9         int **f = new int*[len];
10         for(int i = 0 ; i < len; i++)
11         {
12             f[i] = new int[len+1];
13             for(int j = 0; j < len+1; j++)
14                 f[i][j] = len;
15             f[i][1] = 0;
16         }
17         for(int k = 2; k <= len; k++)
18         {
19             for(int i = 0; i <= len-k; i++)
20             {
21                 if(k == 2 && s[i] == s[i+1])f[i][2] = 0;
22                 else if(f[i+1][k-2] == 0 &&s[i] == s[i+k-1])f[i][k] = 0;
23                 else 
24                 {
25                     for(int m = 1; m < k; m++)
26                         if(f[i][k] > f[i][m] + f[i+m][k-m] + 1)
27                             f[i][k] = f[i][m] + f[i+m][k-m] + 1;
28                 }
29             }
30         }
31         int res = f[0][len], depth = 0;
32         for(int i = 0 ; i < len; i++)
33             delete [](f[i]);
34         delete []f;
35         return res;
36     }
37 };
复制代码
算法3：同上一题，用f来记录子串是否是回文，另外优化最小分割次数的动态规划方程如下,mins[i] 表示子串s[0...i]的最小分割次数：

如果s[0...i]是回文，mins[i] = 0
如果s[0...i]不是回文，mins[i] = min{mins[k] +1 (s[k+1...i]是回文)  或  mins[k] + i-k  (s[k+1...i]不是回文)} ，其中0<= k < i
代码如下，大数据顺利通过，结果Accept：                                                                                                                                 本文地址

复制代码
 1 class Solution {
 2 public:
 3     int minCut(string s) {
 4         // IMPORTANT: Please reset any member data you declared, as
 5         // the same Solution instance will be reused for each test case.
 6         int len = s.length();
 7         if(len <= 1)return 0;
 8         //f[i][j] = true表示以i为起点，长度为j的子串是回文
 9         bool **f = new bool*[len];
10         for(int i = 0 ; i < len; i++)
11         {
12             f[i] = new bool[len+1];
13             for(int j = 0; j < len+1; j++)
14                 f[i][j] = false;
15             f[i][1] = true;
16         }
17         int mins[len];//mins[i]表示s[0...i]的最小分割次数
18         mins[0] = 0;
19         for(int k = 2; k <= len; k++)
20         {
21             for(int i = 0; i <= len-k; i++)
22             {
23                 if(k == 2)f[i][2] = (s[i] == s[i+1]);
24                 else f[i][k] = f[i+1][k-2] && (s[i] == s[i+k-1]);
25             }
26             if(f[0][k] == true){mins[k-1] = 0; continue;}
27             mins[k-1] = len - 1;
28             for(int i = 0; i < k-1; i++)
29             {
30                 int tmp;
31                 if(f[i+1][k-i-1] == true)tmp = mins[i]+1;
32                 else tmp = mins[i]+k-i-1;
33                 if(mins[k-1] > tmp)mins[k-1] = tmp;
34             }
35         }
36         for(int i = 0 ; i < len; i++)
37             delete [](f[i]);
38         delete []f;
39         return mins[len-1];
40     }
41 
42 };
复制代码
	