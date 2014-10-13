

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * 
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */

public class ValidParentheses {
	public boolean isValid(String s) {
		int len = s.length();
		if (len == 0)
			return true;
		Stack<Character> st = new Stack<Character>();
		st.add(s.charAt(0));
		int i = 1;
		while (i < len) {
			switch (s.charAt(i)) {
			case '(':
				st.add('(');
				break;
			case ')':
				if (!st.empty() && st.peek() == '(') {
					st.pop();
				} else {
					return false;
				}
				break;
			case '{':
				st.add('{');
				break;
			case '}':
				if (!st.empty() && st.peek() == '{') {
					st.pop();
				} else {
					return false;
				}
				break;
			case '[':
				st.add('[');
				break;
			case ']':
				if (!st.empty() && st.peek() == '[') {
					st.pop();
				} else {
					return false;
				}
				break;
			}
			i++;
		}
		return st.empty();
	}
}


public class Solution {
public boolean isValid(String s) {
        if(s.length()==0||s.length()==1)
            return false;

        Stack<Character> x = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('||s.charAt(i)=='{'||s.charAt(i)=='['){
                x.push(s.charAt(i));
            }else{
                if(x.size()==0)
                    return false;
                char top = x.pop();
                if(s.charAt(i)==')')
                    if(top!='(')
                        return false;
                else if(s.charAt(i)=='}')
                    if(top!='{')
                        return false;
                else if(s.charAt(i)==']')
                    if(top!='[')
                        return false;
            }
    }
    //考虑到可能最后单出一个{ （ [;
        return x.size()==0;
}
     }
     
     
     