import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Given two words (start and end), and a dictionary, find all shortest
 * transformation sequence(s) from start to end, such that:
 * 
 * Only one letter can be changed at a time Each intermediate word must exist in
 * the dictionary For example,
 * 
 * Given: start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"]
 * 
 * Return:
 * 
 * [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ] Note:
 * 
 * All words have the same length. All words contain only lowercase alphabetic
 * characters.
 */

public class WordLadderII {
	private void GeneratePath(Map<String, ArrayList<String>> prevMap,
			ArrayList<String> path, String word,
			ArrayList<ArrayList<String>> ret) {
		if (prevMap.get(word).size() == 0) {
			path.add(0, word);
			ArrayList<String> curPath = new ArrayList<String>(path);
			ret.add(curPath);
			path.remove(0);
			return;
		}

		path.add(0, word);
		for (String pt : prevMap.get(word)) {
			GeneratePath(prevMap, path, pt, ret);
		}
		path.remove(0);
	}

	public ArrayList<ArrayList<String>> findLadders(String start, String end,
			HashSet<String> dict) {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		Map<String, ArrayList<String>> prevMap = new HashMap<String, ArrayList<String>>();
		dict.add(start);
		dict.add(end);
		for (String d : dict) {
			prevMap.put(d, new ArrayList<String>());
		}
		ArrayList<HashSet<String>> candidates = new ArrayList<HashSet<String>>();
		candidates.add(new HashSet<String>());
		candidates.add(new HashSet<String>());
		int current = 0;
		int previous = 1;
		candidates.get(current).add(start);
		while (true) {
			current = current == 0 ? 1 : 0;
			previous = previous == 0 ? 1 : 0;
			for (String d : candidates.get(previous)) {
				dict.remove(d);
			}
			candidates.get(current).clear();
			for (String wd : candidates.get(previous)) {
				for (int pos = 0; pos < wd.length(); ++pos) {
					StringBuffer word = new StringBuffer(wd);
					for (int i = 'a'; i <= 'z'; ++i) {
						if (wd.charAt(pos) == i) {
							continue;
						}

						word.setCharAt(pos, (char) i);

						if (dict.contains(word.toString())) {
							prevMap.get(word.toString()).add(wd);
							candidates.get(current).add(word.toString());
						}
					}
				}
			}

			if (candidates.get(current).size() == 0) {
				return ret;
			}
			if (candidates.get(current).contains(end)) {
				break;
			}
		}

		ArrayList<String> path = new ArrayList<String>();
		GeneratePath(prevMap, path, end, ret);

		return ret;
	}
}



分析：本题主要的框架和上一题是一样，但是还要解决两个额外的问题：一、 怎样保证求得所有的最短路径；二、 怎样构造这些路径

第一问题：

不能像上一题第二点注意那样，找到一个单词相邻的单词后就立马把它从字典里删除，因为当前层还有其他单词可能和该单词是相邻的，这也是一条最短路径，比如hot->hog->dog->dig和hot->dot->dog->dig，找到hog的相邻dog后不能立马删除，因为和hog同一层的单词dot的相邻也是dog，两者均是一条最短路径。但是为了避免进入死循环，再hog、dot这一层的单词便利完成后dog还是得从字典中删除。即等到当前层所有单词遍历完后，和他们相邻且在字典中的单词要从字典中删除。
如果像上面那样没有立马删除相邻单词，就有可能把同一个单词加入bfs队列中，这样就会有很多的重复计算（比如上面例子提到的dog就会被2次加入队列）。因此我们用一个哈希表来保证加入队列中的单词不会重复，哈希表在每一层遍历完清空（代码中hashtable）。
当某一层的某个单词转换可以得到end单词时，表示已经找到一条最短路径，那么该单词的其他转换就可以跳过。并且遍历完这一层以后就可以跳出循环，因为再往下遍历，肯定会超过最短路径长度
第二个问题：

为了输出最短路径，我们就要在比bfs的过程中保存好前驱节点，比如单词hog通过一次变换可以得到hot，那么hot的前驱节点就包含hog，每个单词的前驱节点有可能不止一个，那么每个单词就需要一个数组来保存前驱节点。为了快速查找因此我们使用哈希表来保存所有单词的前驱路径，哈希表的key是单词，value是单词数组。（代码中的unordered_map<string,vector<string> >prePath）                         
有了上面的前驱路径，可以从目标单词开始递归的构造所有最短路径（代码中的函数 ConstructResult）
public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {  
          
        HashMap<String, HashSet<String>> neighbours = new HashMap<String, HashSet<String>>();  
          
        dict.add(start);  
        dict.add(end);  
          
        // init adjacent graph          
        for(String str : dict){  
            calcNeighbours(neighbours, str, dict);  
        }  
          
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();  
          
        // BFS search queue  
        LinkedList<Node> queue = new LinkedList<Node>();  
        queue.add(new Node(null, start, 1)); //the root has not parent and its level == 1 
          
        // BFS level  
        int previousLevel = 0;  
          
        // mark which nodes have been visited, to break infinite loop  
        HashMap<String, Integer> visited = new HashMap<String, Integer>();   
        while(!queue.isEmpty()){  
            Node n = queue.pollFirst();              
            if(end.equals(n.str)){   
                // fine one path, check its length, if longer than previous path it's valid  
                // otherwise all possible short path have been found, should stop  
                if(previousLevel == 0 || n.level == previousLevel){  
                    previousLevel = n.level;  
                    findPath(n, result);                      
                }else {  
                    // all path with length *previousLevel* have been found  
                    break;  
                }                  
            }else {  
                HashSet<String> set = neighbours.get(n.str);                   
                  
                if(set == null || set.isEmpty()) continue;  
                // note: I'm not using simple for(String s: set) here. This is to avoid hashset's  
                // current modification exception.  
                ArrayList<String> toRemove = new ArrayList<String>();  
                for (String s : set) {  
                      
                    // if s has been visited before at a smaller level, there is already a shorter   
                    // path from start to s thus we should ignore s so as to break infinite loop; if   
                    // on the same level, we still need to put it into queue.  
                    if(visited.containsKey(s)){  
                        Integer occurLevel = visited.get(s);  
                        if(n.level+1 > occurLevel){  
                            neighbours.get(s).remove(n.str);  
                            toRemove.add(s);  
                            continue;  
                        }  
                    }  
                    visited.put(s,  n.level+1);  
                    queue.add(new Node(n, s, n.level + 1));  
                    if(neighbours.containsKey(s))  
                        neighbours.get(s).remove(n.str);  
                }  
                for(String s: toRemove){  
                    set.remove(s);  
                }  
            }  
        }  
  
        return result;  
    }  
      
    public void findPath(Node n, ArrayList<ArrayList<String>> result){  
        ArrayList<String> path = new ArrayList<String>();  
        Node p = n;  
        while(p != null){  
            path.add(0, p.str);  
            p = p.parent;   
        }  
        result.add(path);  
    }  
  
    /* 
     * complexity: O(26*str.length*dict.size)=O(L*N) 
     */  
    void calcNeighbours(HashMap<String, HashSet<String>> neighbours, String str, HashSet<String> dict) {  
        int length = str.length();  
        char [] chars = str.toCharArray();  
        for (int i = 0; i < length; i++) {  
              
            char old = chars[i];   
            for (char c = 'a'; c <= 'z'; c++) {  
  
                if (c == old)  continue;  
                chars[i] = c;  
                String newstr = new String(chars);                  
                  
                if (dict.contains(newstr)) {  
                    HashSet<String> set = neighbours.get(str);  
                    if (set != null) {  
                        set.add(newstr);  
                    } else {  
                        HashSet<String> newset = new HashSet<String>();  
                        newset.add(newstr);  
                        neighbours.put(str, newset);  
                    }  
                }                  
            }  
            chars[i] = old;  
        }  
    }  
      
    private class Node {  
        public Node parent;  //previous node
        public String str;  
        public int level;  
        public Node(Node p, String s, int l){  
            parent = p;  
            str = s;  
            level = l;  
        }  
    }