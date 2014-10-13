

/**
 * Given a string S, find the longest palindromic substring in S. You may assume
 * that the maximum length of S is 1000, and there exists one unique longest
 * palindromic substring.
 */

public class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
		int length = s.length();
		String result = "";
		for (int i = 0; i < length; i++) {
			String ps = getPalindrome(s, i, i);
			if (ps.length() > result.length()) {
				result = ps;
			}
			ps = getPalindrome(s, i, i + 1);
			if (ps.length() > result.length()) {
				result = ps;
			}
		}
		return result;
	}

	private String getPalindrome(String s, int l, int r) {
		while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return s.substring(l + 1, r);
	}
}



 第一种方法就是挨个检查，维护全局最长，时间复杂度为O（n3），会TLE。

 代码如下：
public String longestPalindrome(String s) {
 
    int maxPalinLength = 0;
    String longestPalindrome = null;
    int length = s.length();
 
    // check all possible sub strings
    for (int i = 0; i < length; i++) {
        for (int j = i + 1; j < length; j++) {
            int len = j - i;
            String curr = s.substring(i, j + 1);
            if (isPalindrome(curr)) {
                if (len > maxPalinLength) {
                    longestPalindrome = curr;
                    maxPalinLength = len;
                }
            }
        }
    }
 
    return longestPalindrome;
}
 
public boolean isPalindrome(String s) {
 
    for (int i = 0; i < s.length() - 1; i++) {
        if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
            return false;
        }
    }
 
    return true;
}
 

第二种方法“是对于每个子串的中心（可以是一个字符，或者是两个字符的间隙，比如串abc,中心可以是a,b,c,或者是ab的间隙，bc的间隙，例如aba是回文，abba也是回文，这两种情况要分情况考虑）往两边同时进 行扫描，直到不是回文串为止。假设字符串的长度为n,那么中心的个数为2*n-1(字符作为中心有n个，间隙有n-1个）。对于每个中心往两边扫描的复杂 度为O(n),所以时间复杂度为O((2*n-1)*n)=O(n^2),空间复杂度为O(1)。”引自Code ganker（http://codeganker.blogspot.com/2014/02/longest-palindromic-substring-leetcode.html）

代码如下：
public String longestPalindrome(String s) {
        if (s.isEmpty()||s==null||s.length() == 1)
            return s;
          
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            // get longest palindrome with center of i
            String tmp = helper(s, i, i);
            
            if (tmp.length() > longest.length())
                longest = tmp;
     
            // get longest palindrome with center of i, i+1
            tmp = helper(s, i, i + 1);
            if (tmp.length() > longest.length())
                longest = tmp;
        }
     
        return longest;
    }
     
    // Given a center, either one letter or two letter, 
    // Find longest palindrome
    public String helper(String s, int begin, int end) {
        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }
	
	
	
	
	
	
	