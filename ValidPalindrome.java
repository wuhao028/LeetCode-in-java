
/**
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 * 
 * For example, "A man, a plan, a canal: Panama" is a palindrome. "race a car"
 * is not a palindrome.
 * 
 * Note:
 * 
 * Have you consider that the string might be empty? This is a good question to
 * ask during an interview.
 * 
 * For the purpose of this problem, we define empty string as valid palindrome.
 */

public class ValidPalindrome {
	public boolean isPalindrome(String s) {
		s = s.toUpperCase();
		int i = 0, j = s.length() - 1;
		while (i < j) {
			if (!isAlphabet(s.charAt(i))) {
				i++;
			} else if(!isAlphabet(s.charAt(j))) {
				j--;
			} else if (s.charAt(i) != s.charAt(j)) {
				return false;
			} else {
				i++;
				j--;
			}
		}
		return true;
	}
	
	private boolean isAlphabet(char c) {
		return (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
	}
}

题解：

 这道题的几个点，

一就是alphanumeric characters and ignoring cases，字母和数字，忽略大小写。 

二就是考虑空字符串是否为回文，最好在面试时候问下面试官，这里是认为空字符串是回文。

因为忽略大小写，所以就统一为大写。

然后就判断当前检查字符是否符合范围，否则大小指针挪动。

如果发现有大小指针指向的值有不同的，就返回false，否则，继续检查。

最后返回true。
public static boolean isPalindrome(String s) {
            if(s.length()==0)
                return true;
            
            s = s.toUpperCase();
            int low1 = 'A', high1 = 'Z';
            int low2 = '0', high2 = '9';
            int low = 0, high = s.length()-1;
            
            while(low < high){
                if((s.charAt(low)<low1||s.charAt(low)>high1)
                    && (s.charAt(low)<low2||s.charAt(low)>high2)){
                        low++;
                        continue;
                    }
                    
                if((s.charAt(high)<low1||s.charAt(high)>high1)
                    && (s.charAt(high)<low2||s.charAt(high)>high2)){
                        high--;
                        continue;
                    }
                if(s.charAt(low) == s.charAt(high)){
                    low++;
                    high--;
                }else
                    return false;
            }
            return true;
        }