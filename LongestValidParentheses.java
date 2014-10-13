import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of
 * the longest valid (well-formed) parentheses substring.
 * 
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * 
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */

public class LongestValidParentheses {
	public int longestValidParentheses(String s) {
		int length = s.length();
		if (length == 0)
			return 0;
		int left = 0;
		Stack<Integer> indexs = new Stack<Integer>();
		boolean[] record = new boolean[length];
		for (int i = 0; i < length; i++) {
			if (s.charAt(i) == '(') {
				left++;
				indexs.push(i);
			} else if (left > 0) {
				int idx = indexs.pop();
				record[idx] = true;
				record[i] = true;
				left--;
			}
		}
		int ret = 0;
		int current = 0;
		for (int k = 0; k < length; k++) {
			if (record[k]) {
				current++;
			} else {
				ret = current > ret ? current : ret;
				current = 0;
			}
		}
		return ret > current ? ret : current;
	}
}


// 利用栈

public static int longestValidParentheses(String s) {
        if (s==null||s.length()==0){
            return 0;
            }
        
        int start=0;
        int maxLen=0;
        Stack<Integer> stack=new Stack<Integer>();
        
        for (int i=0; i<s.length();i++){
            if (s.charAt(i)=='('){
                stack.push(i);
            }else{
                if (stack.isEmpty()){ 
                    // record the position before first left parenthesis
                    start=i+1;
                }else{
                    stack.pop();
                    // if stack is empty mean the positon before the valid left parenthesis is "last"
                    if (stack.isEmpty()){
                        maxLen=Math.max(i-start+1, maxLen);
                    }
                    else{
                        // if stack is not empty, then for current i the longest valid parenthesis length is
                        // i-stack.peek()
						
						// 我的版本和这里的主要差别就是没能实现下面这段代码的功能！！
                        maxLen=Math.max(i-stack.peek(),maxLen);
                    }
                }
            }
        }
            return maxLen;
            }



这道题首先想到的是动态规划, 字符串为s，设bool数组dp[i][j]表示子串s[i…j]是否可以完全匹配，那么动态规划方程如下:

初始化dp数组为false
如果dp[i+1][j-1] == true && s[i] == ‘(’&&s[j] == ‘)’或者 存在k = i+1…j-1 使得dp[i][k] == true && dp[k+1][j] = true ，则dp[i][j] = true
方程的意思是：要使子串s[i…j]能够完全匹配，那么有以下两种情况可以满足：
a、子串s[i+1…j-1]完全匹配，且s[i]、s[j]是左右两个半括号；
b、存在某个k，使得两个子串s[i…k]、s[k+1…j]都能完全匹配.

求得所有dp[i][j]后，最长匹配子串的长度 = max {j-i}, 其中 dp[i][j] = true;

下面代码中isValid相当于方程中的dp，注意到子串长度为奇数时，子串可能完全匹配。概算大时间复杂度为O（n^3），oj上大数据超时

class Solution {
public:
    int longestValidParentheses(string s) {
        const int len = s.size();
        bool isValid[len][len];
        memset(isValid, 0, sizeof(isValid));
        int res = 0;
        for(int i = 0; i < len-1; i++)
            if(s[i] == '(' && s[i+1] == ')')
            {
                isValid[i][i+1] = true;
                res = 2;
            }
        for(int k = 4; k <= len; k+=2)//k表示子串长度，只有长度为偶数的子串才可能是合法括号
            for(int i = 0; i <= len-k; i++)//i表示子串的起始位置
            {
                if(isValid[i+1][i+k-2] && s[i] == '(' && s[i+k-1] == ')')
                    isValid[i][i+k-1] = true;
                else
                {
                    for(int j = i+1; j <= i+k-3; j++)
                        if(isValid[i][j] && isValid[j+1][i+k-1])
                            isValid[i][i+k-1] = true;
                }
                if(isValid[i][i+k-1])res = k;
            }
        return res;
    }
};


// my verison   非正规动态规划  WRONG ANSER
// 是利用栈的思想，不过少了记录位置的start变量
public class Solution {
    public int longestValidParentheses(String s) {
        int n=s.length();
        if(n==0)
         return 0;
        
        int[] dp=new int[n];
        dp[0]=0;
        int i=0;
        int p=0;
        int q=0;
        
        while(i<n){
            
            if(s.charAt(i)=='('){
                p++;
                i++;
               
            }else if(s.charAt(i)==')'){
                q++;
                
                if(q<=p){
				    p--;
					if(p==0)
                    dp[i]=2*q;
					
					if(p>0)
					dp[i]=2*q;
                   
                }
                
                if(q>p){
                    dp[i]=0;
                     p=0;
                     q=0;
                    }
                    
                     i++;
            }

        }
        
         int max=dp[0];
        for(int t=0;t<n;t++){
            if(dp[t]>max)
              max=dp[t];
        }
        
        
        return max;
        
}
}
Submission Result: Wrong Answer

Input:	"()(()"
Output:	4
Expected:	2