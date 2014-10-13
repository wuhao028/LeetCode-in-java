Wildcard Matching Total Accepted: 14308 Total Submissions: 101243 My Submissions
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false

正确的做法是贪心.

大概是这样的

我们来匹配s和p

如果匹配就s++ , p++

如果不匹配的话就看p之前知否有*

当然是否有*我们需要记录的,遇到*就记录当前*的位置和匹配到的s的位置

然后从*的下一位置匹配,开始匹配0个字符

如果ok往后走,往后不ok,那么匹配1个字符...同理2,3,4个字符(有点回溯的感觉吧

所以实践复杂度是O(len(s) * len(p))

public class WildcardMatching {
	public boolean isMatch(String s, String p) {
		int i = 0;
		int j = 0;
		int star = -1;
		int mark = -1;
		while (i < s.length()) {
			if (j < p.length()
					&& (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
				++i;
				++j;
			} else if (j < p.length() && p.charAt(j) == '*') {
				star = j++;
				mark = i;
			} else if (star != -1) {
				j = star + 1;
				i = ++mark;
			} else {
				return false;
			}
		}
		// 考虑边界情况!!!
		while (j < p.length() && p.charAt(j) == '*') {
			++j;
		}
		return j == p.length();
	}
}





//my version 没有用动态规划  没有AC

    public boolean isMatch(String s, String p) {
        
        char[] schar=s.toCharArray();
        char[] pchar=p.toCharArray();
        int n=p.length();
        int m=s.length();
        int i=0;
        int j=0;
        while(i<m){
            if(p.charAt(i)!='?' && p.charAt(i)!='*'){
               if(p.charAt(i)==s.charAt(j)){
                   j++;
                   i++;
               }
               if(i<n && j<m && p.charAt(i)!=s.charAt(j))
                return false;
            
            }
        
            if(p.charAt(i)=='?'){
                i++;
                j++;
            }
            
            if(p.charAt(i)=='*'){
                i++;
                for(int k=j;j<m;j++){
                    return isMatch(s.substring(j,m),p.substring(i,n));
                }
            }
  
        }
        
        return true;

    }

	
	
	
	
	
	
	
	
	