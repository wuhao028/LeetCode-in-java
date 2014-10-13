

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a set of distinct integers, S, return all possible subsets.
 * 
 * Note: Elements in a subset must be in non-descending order. The solution set
 * must not contain duplicate subsets. For example, If S = [1,2,3], a solution
 * is:
 * 
 * [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 */

public class Subsets {
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
		Arrays.sort(S);
		ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();
		subsets.add(new ArrayList<Integer>());
		for (int i = 0; i < S.length; i++) {
			int size = subsets.size();
			for (int j = 0; j < size; j++) {
				ArrayList<Integer> subset = new ArrayList<Integer>(
						subsets.get(j));
				subset.add(S[i]);
				subsets.add(subset);
			}
		}
		return subsets;
	}
}

一个思路就是套用combination的方法，其实combination那道题就是在求不同n下的subset，这里其实是要求一个集合罢了。

public static void dfs(int[] S, int start, int len, ArrayList<Integer> item,ArrayList<ArrayList<Integer>> res){
        if(item.size()==len){
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i=start; i<S.length;i++){
            item.add(S[i]);
            dfs(S, i+1, len, item, res);
            item.remove(item.size()-1);
        }

    }
    
    public static ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>> ();
        ArrayList<Integer> item = new ArrayList<Integer>();
        if(S.length==0||S==null)
            return res;
        
        Arrays.sort(S);
        for(int len = 1; len<= S.length; len++)
            dfs(S,0,len,item,res);
            
        res.add(new ArrayList<Integer>());
        
        return res;
    }