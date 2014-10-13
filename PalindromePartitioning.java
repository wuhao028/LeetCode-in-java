

import java.util.ArrayList;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab", Return
 * 
 * [ ["aa","b"], ["a","a","b"] ]
 */

public class PalindromePartitioning {
	public ArrayList<ArrayList<String>> partition(String s) {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		ArrayList<String> r = new ArrayList<String>();
		int length = s.length();
		boolean[][] map = new boolean[length][length];
		findPartition(s, 0, ret, r, map);
		return ret;
	}

	private void findPartition(String s, int start,
			ArrayList<ArrayList<String>> ret, ArrayList<String> r,
			boolean[][] map) {
		int length = s.length();
		if (start == length && r.size() != 0) {
			ArrayList<String> clone = new ArrayList<String>(r);
			ret.add(clone);
		} else {
			for (int j = start; j < length; j++) {
				if (start == j
						|| (j - start > 1 && s.charAt(start) == s.charAt(j) && map[start + 1][j - 1])
						|| (j - start == 1 && s.charAt(start) == s.charAt(j))) {
					map[start][j] = true;
					r.add(s.substring(start, j + 1));
					findPartition(s, j + 1, ret, r, map);
					r.remove(r.size() - 1);
				}
			}
		}
	}
}





public class Solution {public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<String> item = new ArrayList<String>();
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        
        if(s==null||s.length()==0)
            return res;
        
        dfs(s,0,item,res);
        return res;
    }
    
    public void dfs(String s, int start, ArrayList<String> item, ArrayList<ArrayList<String>> res){
        if (start == s.length()){
            res.add(new ArrayList<String>(item));
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            String str = s.substring(start, i+1);
            if (isPalindrome(str)) {
                item.add(str);
                dfs(s, i+1, item, res);
                item.remove(item.size() - 1);
            }
        }
    }
    
    public boolean isPalindrome(String s){
         int low = 0;
         int high = s.length()-1;
         while(low < high){
             if(s.charAt(low) != s.charAt(high))
                return false;
             low++;
             high--;
         }
         return true;
    }
    
}