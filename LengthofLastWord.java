

/**
 * Given a string s consists of upper/lower-case alphabets and empty space
 * characters ' ', return the length of last word in the string.
 * 
 * If the last word does not exist, return 0.
 * 
 * Note: A word is defined as a character sequence consists of non-space
 * characters only.
 * 
 * For example, Given s = "Hello World", return 5.
 */

public class LengthofLastWord {
	public int lengthOfLastWord(String s) {
		int i = s.length() - 1;
		while (i >= 0 && s.charAt(i) == ' ') {
			i--;
		}
		if (i < 0)
			return 0;
		int j = i;
		while (j - 1 >= 0 && s.charAt(j - 1) != ' ') {
			j--;
		}
		return i - j + 1;
	}
}

// my version
 public int lengthOfLastWord(String s) {
        String x=s;
         x=x.trim();
        int n=x.length();
        int t=0;
        for(int i=n-1;i>=0;i--){
            if(x.charAt(i)!=' '){
                t++;
            }else break;
        }
      return t;
    }
	
	
	
	 这道题主要是考虑一下最后是不是空格，方法是倒着找不是空格的字符并计数，如果遇到空格且计数不是0，说明最后一个单词已经被计数了，所以可以返回了。

 

代码如下：

复制代码
     public int lengthOfLastWord(String s) {
          if (s == null || s.length() == 0)   
             return 0;  
           
         int len = s.length();  
         int count = 0;  
         for (int i = len - 1; i >= 0; i--) {  
             if (s.charAt(i) != ' ') {  
                 count++;  
             }  
            if(s.charAt(i)==' '&&count != 0){  
                 return count;  
             }  
         }  
         return count;  
     }
复制代码
 当然这道题也能用投机取巧的方法，用split函数把字符串按照空格分隔好，返回最后那个就行。。。

代码如下：

复制代码
     public int lengthOfLastWord(String s) {
         String[] a = s.split(" ");
         if(a == null || a.length == 0)
             return 0;
 
         return a[a.length-1].length();
     }
复制代码