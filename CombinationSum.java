
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique
 * combinations in C where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers.
 * 
 * Elements in a combination (a1, a2, ... , ak) must be in non-descending order.
 * (ie, a1 <= a2 <= ... <= ak).
 * 
 * The solution set must not contain duplicate combinations. For example, given
 * candidate set 2,3,6,7 and target 7, A solution set is: [7] [2, 2, 3]
 */
 
 
// Wrong Answer
public class CombinationSum {
	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates,
			int target) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> solution = new ArrayList<Integer>();
		Arrays.sort(candidates);
		combinationSum(candidates, 0, 0, target, ret, solution);
		return ret;
	}

	private void combinationSum(int[] candidates, int start, int sum,
			int target, ArrayList<ArrayList<Integer>> ret,
			ArrayList<Integer> solution) {
		if (sum == target) {
			ret.add(new ArrayList<Integer>(solution));
			return;
		}
		if (sum > target)
			return;
		for (int i = start; i < candidates.length; i++) {
			solution.add(candidates[i]);
			combinationSum(candidates, start, sum + candidates[i], target, ret, solution);
			solution.remove(solution.size() - 1);
		}
	}
}

还是老问题，用DFS找解决方案，不同点是，这道题： The same repeated number may be chosen from C unlimited number of times.

所以，每次跳进递归不用都往后挪一个，还可以利用当前的元素尝试。

同时，这道题还要判断重复解。用我之前介绍的两个方法：

 1.       if(i>0 && candidates[i] == candidates[i-1])//deal with dupicate
                 continue; 

 2.       if(!res.contains(item)) 
                res.add(new ArrayList<Integer>(item));   

这两个方法解决。 

public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {  
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
        ArrayList<Integer> item = new ArrayList<Integer>();
        if(candidates == null || candidates.length==0)  
            return res; 
            
        Arrays.sort(candidates);  
        helper(candidates,target, 0, item ,res);  
        return res;  
    }  
    
    private void helper(int[] candidates, int target, int start, ArrayList<Integer> item,   
    ArrayList<ArrayList<Integer>> res){  
        if(target<0)  
            return;  
        if(target==0){  
            res.add(new ArrayList<Integer>(item));  
            return;  
        }
        
        for(int i=start;i<candidates.length;i++){  
            if(i>0 && candidates[i] == candidates[i-1])//deal with dupicate
                continue;  
            item.add(candidates[i]);
            int newtarget = target - candidates[i];
            helper(candidates,newtarget,i,item,res);//之所以不传i+1的原因是：
                                                    //The same repeated number may be 
                                                    //chosen from C unlimited number of times.
           
//如果被return  ，要删除之前添加的不合适的元素，即是最后一个被添加的元素
		   item.remove(item.size()-1);  
        }  
    }







