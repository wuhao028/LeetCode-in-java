题目：

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
 

题解：

 一个用了java的s.split(" ")，按空格分隔。

然后调用了系统函数：Collections.reverse(list);把list顺序调换了。

最后再把结果存成数组即可。

代码如下：

public class Solution {
    public static String reverseWords(String s) {
        if(s==null||s.length()==0)
            return s;
        String [] result = s.split(" ");
        if(result==null||result.length==0)
            return "";
            
        ArrayList<String> list = new ArrayList<String>();
        
        for(int i = 0; i<result.length;i++){
            if(!result[i].isEmpty())
                list.add(result[i]);
        }
        Collections.reverse(list);
        
        String ans = new String();
        for(int i = 0; i<list.size()-1;i++){
            ans += list.get(i)+" ";
        }
        ans +=list.get(list.size()-1);
        return ans;
    }
}