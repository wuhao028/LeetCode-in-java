import java.util.LinkedList;
import java.util.List;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example,
 * 
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * 
 * click to show corner cases.
 * 
 * Corner Cases:
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 *
 */

public class SimplifyPath {
	public String simplifyPath(String path) {
		int length = path.length();
		if (length == 0)
			return path;
		List<String> dicts = new LinkedList<String>();
		int slow = 0;
		int fast = 0;
		while (true) {
			while (slow < length && path.charAt(slow) == '/') {
				slow++;
			}
			if (slow >= length)
				break;
			fast = slow;
			while (fast < length && path.charAt(fast) != '/') {
				fast++;
			}
			String s = path.substring(slow, fast);
			if (s.equals("..")) {
				if (!dicts.isEmpty()) {
					dicts.remove(dicts.size() - 1);
				}
			} else if (!s.equals(".")) {
				dicts.add(s);
			}
			slow = fast;
		}
		StringBuffer ret = new StringBuffer();
		for (String s : dicts) {
			ret.append('/');
			ret.append(s);
		}
		return ret.length() == 0 ? "/" : ret.toString();
	}
}
题解：

这是一道简化路径的题，路径简化的依据是：

当遇到“/../"则需要返回上级目录，需检查上级目录是否为空。

当遇到"/./"则表示是本级目录，无需做任何特殊操作。 

当遇到"//"则表示是本级目录，无需做任何操作。

当遇到其他字符则表示是文件夹名，无需简化。

当字符串是空或者遇到”/../”，则需要返回一个"/"。

当遇见"/a//b"，则需要简化为"/a/b"。

 

根据这些要求，我需要两个栈来解决问题。

先将字符串依"/"分割出来，然后检查每个分割出来的字符串。

当字符串为空或者为"."，不做任何操作。

当字符串不为".."，则将字符串入栈。

当字符串为"..", 则弹栈（返回上级目录）。

 

当对所有分割成的字符串都处理完后，检查第一个栈是否为空，如果栈为空，则证明没有可以重建的目录名，返回"/"即可。

当第一个栈不为空时，这时候我们需要还原path。但是不能弹出栈，因为按照要求栈底元素应该为最先还原的目录path。

例如：原始path是 /a/b/c/，栈里的顺序是：a b c，如果依次弹栈还原的话是：/c/b/a（错误！），正确答案为：/a/b/c

所以这里我应用了第二个栈，先将第一个栈元素弹出入栈到第二个栈，然后再利用第二个栈还原回初始path。


public String simplifyPath(String path) {
        if(path == null||path.length()==0)
            return path;
        
        Stack<String> stack = new Stack<String>();
        String[] list = path.split("/");
        
        for(int i=0; i<list.length; i++){
            if(list[i].equals(".")||list[i].length()==0)
                continue;
            else if(!list[i].equals(".."))
                stack.push(list[i]);
            else{
                if(!stack.isEmpty())
                    stack.pop();
            }
        }
        
        StringBuilder res = new StringBuilder();
        
        Stack<String> temp = new Stack<String>();
        while(!stack.isEmpty())  
            temp.push(stack.pop());
        
        while(!temp.isEmpty())
            res.append("/"+temp.pop());
        
        if(res.length()==0)
            res.append("/");
        
        return res.toString();
    }
	
	
	
	
	
	
	
	
	
	
	