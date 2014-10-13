

/**
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing digits,
 * determine the total number of ways to decode it.
 * 
 * For example, Given encoded message "12", it could be decoded as "AB" (1 2) or
 * "L" (12).
 * 
 * The number of ways decoding "12" is 2.
 */

public class DecodeWays {
	public int numDecodings(String s) {
		if (s.length() == 0)
			return 0;
		int[] c = new int[s.length() + 1];
		c[0] = 1;
		if (s.charAt(0) != '0')
			c[1] = c[0];
		else
			c[1] = 0;
		for (int i = 2; i <= s.length(); i++) {
			if (s.charAt(i - 1) == '0') {
				c[i] = 0;
			} else {
				c[i] = c[i - 1];
			}
			if (s.charAt(i - 2) == '1'
					|| (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')) {
				c[i] += c[i - 2];
			}
		}
		return c[s.length()];
	}
}



//MY VERSION
//DFS 超时

    public int numDecodings(String s) {
        int res=0;
        int n=s.length();
        if(n==1)
        return 1;
        if(n==0)
        return 0;
        dfs(s,0,res);
        return res;
    }
    
    public void dfs(String s,int start,int res){
           
        for(int i=1;i<3 && start+i<s.length(); i++){
           String str=new String(s.substring(start,start+i));
            int num=Integer.parseInt(str);
            if(num<27 && num>0){
                res++;
                dfs(s,start+1,res);
            }
        }

    }
	
分析：需要注意的是，如果序列中有不能匹配的0，那么解码方法是0，比如序列012 、100（
第二个0可以和1组成10，第三个0不能匹配）。递归的解法很容易，但是大集合会超时。转换成动态规划的方法
，假设dp[i]表示序列s[0...i-1]的解码数目，动态规划方程如下：                                                                                                                                                               本文地址

初始条件：dp[0] = 1, dp[1] = (s[0] == '0') ? 0 : 1
dp[i] = ( s[i-1] == 0 ? 0 : dp[i-1] ) + ( s[i-2,i-1]可以表示字母 ？ dp[i-2] : 0 )，
 其中第一个分量是把s[0...i-1]末尾一个数字当做一个字母来考虑，第二个分量是把s[0...i-1]末尾两个数字
 当做一个字母来考虑
class Solution {
public:
    int numDecodings(string s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        //注意处理字符串中字符为0的情况
        int len = s.size();
        if(len == 0)return 0;
        int dp[len+1];//dp[i]表示s[0...i-1]的解码方法数目
        dp[0] = 1;
        if(s[0] != '0')dp[1] = 1;
        else dp[1] = 0;
        for(int i = 2; i <= len; i++)
        {
            if(s[i-1] != '0')
                dp[i] = dp[i-1];
            else dp[i] = 0;
            if(s[i-2] == '1' || (s[i-2] == '2' && s[i-1] <= '6'))
                dp[i] += dp[i-2];
        }
        return dp[len];
    }
};

 动态规划来做。

 设置动态数组dp[n+1]。dp[i]表示从1~i的decode ways的个数。

 当给的code只有一位数时，判断是不是valid（A~Z），是的话就dp[1] = 1 不是的话就是dp[1] = 0

 因为像给的例子12可以有两种可能的解析方法，所以计算dp[i]的时候要判断两种可能性，再累加。

代码如下：
public int numDecodings(String s) {  
        if (s.length()==0||s==null||s=="0") 
            return 0; 

        int[] dp = new int[s.length()+1];  
        dp[0] = 1;  
        
        if (isValid(s.substring(0,1)))
            dp[1] = 1;  
        else 
            dp[1] = 0; 
        
        for(int i=2; i<=s.length();i++){  
            if (isValid(s.substring(i-1,i)))  
                dp[i] += dp[i-1];  
            if (isValid(s.substring(i-2,i)))  
                dp[i] += dp[i-2];  
        }  
        return dp[s.length()];  
    }  
      
    public boolean isValid(String s){  
        if (s.charAt(0)=='0') 
            return false;  
        int code = Integer.parseInt(s);  
        return code>=1 && code<=26;  
    }