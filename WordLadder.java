

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given two words (start and end), and a dictionary, find the length of
 * shortest transformation sequence from start to end, such that:
 * 
 * Only one letter can be changed at a time Each intermediate word must exist in
 * the dictionary For example,
 * 
 * Given: start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"]
 * 
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * 
 * Note:
 * 
 * Return 0 if there is no such transformation sequence. All words have the same
 * length. All words contain only lowercase alphabetic characters.
 */

public class WordLadder {
	public int ladderLength(String start, String end, HashSet<String> dict) {
		if (dict.size() == 0)
			return 0;
		int currentLevel = 1;
		int nextLevel = 0;
		int step = 1;
		boolean found = false;
		Queue<String> st = new LinkedList<String>();
		HashSet<String> visited = new HashSet<String>();
		st.add(start);
		while (!st.isEmpty()) {
			String s = st.remove();
			currentLevel--;
			if (stringDiff(s, end) == 1) {
				found = true;
				step++;
				break;
			} else {
				int length = s.length();
				StringBuffer sb = new StringBuffer(s);
				for (int i = 0; i < length; i++) {
					for (int j = 0; j < 26; j++) {
						char c = sb.charAt(i);
						sb.setCharAt(i, (char) ('a' + j));
						if (dict.contains(sb.toString())
								&& !visited.contains(sb.toString())) {
							nextLevel++;
							st.add(sb.toString());
							visited.add(sb.toString());
						}
						sb.setCharAt(i, c);
					}
				}
			}
			if (currentLevel == 0) {
				currentLevel = nextLevel;
				nextLevel = 0;
				step++;
			}
		}
		return found ? step : 0;
	}

	private int stringDiff(String s1, String s2) {
		int diff = 0;
		int length = s1.length();
		for (int i = 0; i < length; i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				diff++;
			}
		}
		return diff;
	}
}

 这道题是套用BFS同时也利用BFS能寻找最短路径的特性来解决问题。

 把每个单词作为一个node进行BFS。当取得当前字符串时，对他的每一位字符进行从a~z的替换，如果在字典里面，就入队，并将下层count++，并且为了避免环路，需把在字典里检测到的单词从字典里删除。这样对于当前字符串的每一位字符安装a~z替换后，在queue中的单词就作为下一层需要遍历的单词了。

 正因为BFS能够把一层所有可能性都遍历了，所以就保证了一旦找到一个单词equals（end），那么return的路径肯定是最短的。

 

 像给的例子 start = hit，end = cog，dict = [hot, dot, dog, lot, log]

 按照上述解题思路的走法就是：

  level = 1    hit   dict = [hot, dot, dog, lot, log]

         ait bit cit ... xit yit zit ，  hat hbt hct ... hot ... hxt hyt hzt ，  hia hib hic ... hix hiy hiz

 

  level = 2    hot  dict = [dot, dog, lot, log]

         aot bot cot dot ...  lot ... xot yot zot，hat hbt hct ... hxt hyt hzt，hoa hob hoc ... hox hoy hoz

 

  level = 3    dot lot  dict = [dog log]

         aot bot ... yot zot，dat dbt ...dyt dzt，doa dob ... dog .. doy doz，

         aot bot ... yot zot，lat lbt ... lyt lzt，loa lob ... log... loy loz

 

  level = 4   dog log dict = [] 

         aog bog cog

 

  level = 5   cog  dict = []

public int ladderLength(String start, String end, HashSet<String> dict) {
        if(start==null || end==null || start.length()==0 || end.length()==0 || start.length()!=end.length())  
        return 0; 
        
        LinkedList<String> wordQueue = new LinkedList<String>();
        int level = 1; //the start string already count for 1
        int curnum = 1;//the candidate num on current level
        int nextnum = 0;//counter for next level
        
        wordQueue.add(start);
        
        while(!wordQueue.isEmpty()){
            String word = wordQueue.poll();
            curnum--;
            
            for(int i = 0; i < word.length(); i++){
                char[] wordunit = word.toCharArray();
                for(char j = 'a'; j <= 'z'; j++){
                    wordunit[i] = j;
                    String temp = new String(wordunit);  
                    
                    if(temp.equals(end))
                        return level+1;
                    if(dict.contains(temp)){
                        wordQueue.add(temp);
                        nextnum++;
                        dict.remove(temp);
                    }
                }
            }
            
            if(curnum == 0){
                curnum = nextnum;
                nextnum = 0;
                level++;
            }
        }
        
        return 0;
    }