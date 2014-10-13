/*Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
*/
 
 public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> item = new ArrayList<Integer>();
        
        if(num.length==0||num==null)
            return res;
        boolean[] visited = new boolean[num.length];  
        
        permutation_helper(num,res,item,visited);
        return res;
    }
    
    public void permutation_helper(int[] num, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> item,boolean[] visited){
        if(item.size()==num.length){
            res.add(new ArrayList<Integer>(item));
            return;
        }
        
        for(int i = 0; i<num.length;i++){
            if(!visited[i]){
                item.add(num[i]);
                visited[i]=true;
                permutation_helper(num,res,item,visited);
                item.remove(item.size()-1);
                visited[i]=false;
            }
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	