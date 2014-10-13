

import java.util.ArrayList;

/**
 * Given two integers n and k, return all possible combinations of k numbers out
 * of 1 ... n. For example, If n = 4 and k = 2, a solution is:
 * 
 * [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
 * 
 */

public class Combinations {
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> subset = new ArrayList<Integer>();
		int[] num = new int[n];
		for (int j = 0; j < n; j++) {
			num[j] = j + 1;
		}
		subsets(n, k, num, 0, subset, result);
		return result;
	}

	private void subsets(int n, int k, int[] num, int begin,
			ArrayList<Integer> subset, ArrayList<ArrayList<Integer>> result) {
		if (subset.size() >= k) {
			ArrayList<Integer> c = new ArrayList<Integer>(subset);
			result.add(c);
		} else {
			for (int i = begin; i < num.length; i++) {
				subset.add(num[i]);
				subsets(n, k, num, i + 1, subset, result);
				subset.remove(subset.size() - 1);
			}
		}
	}
}




public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(n <= 0||n < k)
            return res;
        ArrayList<Integer> item = new ArrayList<Integer>();    
        dfs(n,k,1,item, res);//because it need to begin from 1
        return res;
    }
    private void dfs(int n, int k, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res){
        if(item.size()==k){
            res.add(new ArrayList<Integer>(item));//because item is ArrayList<T> so it will not disappear from stack to stack
            return;
        }
        for(int i=start;i<=n;i++){
            item.add(i);
            dfs(n,k,i+1,item,res);
            item.remove(item.size()-1);//包含i的序列完成，除去i，继续开始
        }
    }