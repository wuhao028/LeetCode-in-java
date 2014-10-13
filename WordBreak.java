

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 * 
 * For example, given s = "leetcode", dict = ["leet", "code"].
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 */

public class WordBreak {
	public boolean wordBreak(String s, Set<String> dict) {
		Map<String, Boolean> wordMap = new HashMap<String, Boolean>();
		for (String w : dict) {
			wordMap.put(w, true);
		}
		int len = s.length();
		boolean[] strMap = new boolean[len + 1];
		strMap[0] = true;
		for (int i = 1; i <= len; i++) {
			for (int j = 0; j < i; j++) {
				if (strMap[j] && wordMap.containsKey(s.substring(j, i))) {
					strMap[i] = true;
				}
			}
		}
		return strMap[len];
	}
}


这道题仍然是动态规划的题目，我们总结一下动态规划题目的基本思路。首先我们要决定要存储什么历史信息以及用什么数据结构来存储信息。
然后是最重要的递推式，就是如从存储的历史信息中得到当前步的结果。最后我们需要考虑的就是起始条件的值。
接 下来我们套用上面的思路来解这道题。首先我们要存储的历史信息res[i]是表示到字符串s的第i个元素为止能不能用字典中的词来表示，
我们需要一个长度 为n的布尔数组来存储信息。然后假设我们现在拥有res[0,...,i-1]的结果，我们来获得res[i]的表达式。思路是对于每个以i为
结尾的子 串，看看他是不是在字典里面以及他之前的元素对应的res[j]是不是true，如果都成立，那么res[i]为true，写成式子是
假 设总共有n个字符串，并且字典是用HashSet来维护，那么总共需要n次迭代，每次迭代需要一个取子串的O(i)操作，然后检测i个子串，
而检测是 constant操作。所以总的时间复杂度是O(n^2)（i的累加仍然是n^2量级），而空间复杂度则是字符串的数量，即O(n)。代码如下：

public boolean wordBreak(String s, Set<String> dict) {
    if(s==null || s.length()==0)
        return true;
    boolean[] res = new boolean[s.length()+1];
    res[0] = true;
    for(int i=0;i<s.length();i++)
    {
        StringBuilder str = new StringBuilder(s.substring(0,i+1));
        for(int j=0;j<=i;j++)
        {
            if(res[j] && dict.contains(str.toString()))
            {
                res[i+1] = true;
                break;
            }
            str.deleteCharAt(0);
        }
    }
    return res[s.length()];
}



//my version  
// 之所以错误是因为没有保存相应的res[j] 的值！，没有用到动态规划的思想！
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        
        if(s.length()==1)
           return dict.contains(s);

        int n=s.length();
        
        for(int i=1;i<=n;i++){
            
            String sub=s.substring(0,i);
            if(dict.contains(sub))
              return wordBreak(s.substring(i,n),dict);
        }
        return true;
        
        
    }
}




