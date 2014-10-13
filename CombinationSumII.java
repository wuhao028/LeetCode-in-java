/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers.
 * 
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8, A solution set is: 
 * [1, 7] 
 * [1, 2, 5] 
 * [2, 6] 
 * [1, 1, 6] 
 */
题解：

这道题跟combination sum本质的差别就是当前已经遍历过的元素只能出现一次。

所以需要给每个candidate一个visited域，来标识是否已经visited了。

当回退的时候，记得要把visited一起也回退了。

代码如下：
public static ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {  
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
        ArrayList<Integer> item = new ArrayList<Integer>();
        if(candidates == null || candidates.length==0)  
            return res; 
            
        Arrays.sort(candidates);  
        boolean [] visited = new boolean[candidates.length];
        helper(candidates,target, 0, item ,res, visited);  
        return res;  
    }  
    
    private static void helper(int[] candidates, int target, int start, ArrayList<Integer> item,   
    ArrayList<ArrayList<Integer>> res, boolean[] visited){  
        if(target<0)  
            return;  
        if(target==0){  
            res.add(new ArrayList<Integer>(item));  
            return;  
        }
        
        for(int i=start;i<candidates.length;i++){
            if(!visited[i]){
                if(i>0 && candidates[i] == candidates[i-1] && visited[i-1]==false)//deal with dupicate
                    continue;  
                item.add(candidates[i]);
                visited[i]=true;
                int newtarget = target - candidates[i];
                helper(candidates,newtarget,i,item,res,visited);  
                visited[i]=false;
                item.remove(item.size()-1);  
            }
        }  
    }
	
	
	
	
	
	Idea: 99% same as Combination Sum, beside, the duplication is not

     allow, all we need to do is to increment last selection by i+1 instead i
     before in Combination Sum & skip the recursion step when i>last instead of  i>0
    Combinations Sum II:
    num: set of numbers
    target: when target==0, we find combination satisfied requirement
            that target minus elements from set of "candidates"
    last: last selection, start from 0 & increment as i+1 each time
    curr: a list containing current combination
    res: result of list contain all combinations
	
	
	
	
	
	public class Solution {
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        ArrayList<ArrayList<Integer>> res=new ArrayList<ArrayList<Integer>>();
        if(num.length==0 || target==0) return res;
        Arrays.sort(num);
        helper(num,target,0,res,new ArrayList<Integer>());
        return res;
    }
    private void helper(int[] num, int target,int last,ArrayList<ArrayList<Integer>> res,
                               ArrayList<Integer> curr){
            if(target<0) return;;
            if(target==0){
                res.add(new ArrayList<Integer>(curr));
                return;
            }
        for(int i=last;i<num.length;i++){
            if(i>last && num[i]==num[i-1]){
                continue;
            }
            curr.add(num[i]);
            helper(num,target-num[i],i+1,res,curr);
            curr.remove(curr.size()-1);
        }
    }
}

// my version
line 9:illegal character;

public class Solution {
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> item= new ArrayList<Integer>();
        if(num.length==0){
            return result;}
        Arrays.sort(num);        
        hleper(num,0,target,result,item);
       　 return result;
    }

    private void helper(int[] num,int start,int target,ArrayList<ArrayList<Integer>> result
    ,ArrayList<Integer> item){
        
        if(target<0){return;}
        if(target==0){
            new ArrayList<Integer>(item);
            return;
        }
        
        for(int i=start;i<num.length;i++ ){
            	if(i>1 && candidates[i]==candidates[i-1]){continue;}
         int newtarget=target-num[i];
            item.add(num[i]);
            hleper(num,i+1,newtarget,result,item);
             
            item.remove(item.size()-1);
            
        }
        
	}
}

	
	
	