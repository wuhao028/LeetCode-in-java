/**
 * Follow up for N-Queens problem.
 * 
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 *
 */

public class NQueensII {
	public int solveNQueens(int n) {
		if (n == 0)
			return 0;
		StringBuffer line = new StringBuffer();
		for (int i = 0; i < n; i++) {
			line.append('.');
		}
		StringBuffer[] sol = new StringBuffer[n];
		for (int i = 0; i < n; i++) {
			sol[i] = new StringBuffer(line.toString());
		}
		boolean[] cols = new boolean[n];
		int[] row = new int[n];
		int[] count = new int[1];
		findSolutions(n, 0, sol, row, cols, count);
		return count[0];
	}

	private void findSolutions(int n, int start, StringBuffer[] sol, int[] row,
			boolean[] cols, int[] count) {
		if (start == n) {
			count[0]++;
		} else {
			for (int i = 0; i < n; i++) {
				if (cols[i])
					continue;
				boolean ok = true;
				for (int j = 0; j < start; j++) {
					if (Math.abs(start - j) == Math.abs(i - row[j])) {
						ok = false;
						break;
					}
				}
				if (ok) {
					cols[i] = true;
					sol[start].setCharAt(i, 'Q');
					row[start] = i;
					findSolutions(n, start + 1, sol, row, cols, count);
					row[start] = 0;
					sol[start].setCharAt(i, '.');
					cols[i] = false;
				}
			}
		}
	}
}


//在NQueens I 问题基础上返回的值改为return result.size();即可。

这道题跟NQueens的解法完全一样（具体解法参照N QueensN Queens leetcode java），只不过要求的返回值不同了。。所以要记录的result稍微改一下就好了。。。

因为涉及到递归，result传进去引用类型（List，数组之类的）才能在层层递归中得以保存，所以这里使用一个长度为1的数组帮助计数。

当然，也可以使用一个全局变量来帮助计数。

代码如下：public int totalNQueens(int n) {  
        int[] res = {0};
        if(n<=0)
            return res[0];
            
        int [] columnVal = new int[n];
        
        DFS_helper(n,res,0,columnVal);
        return res[0];
    }
    
    public void DFS_helper(int nQueens, int[] res, int row, int[] columnVal){
        if(row == nQueens){
            res[0] += 1;
        }else{
            for(int i = 0; i < nQueens; i++){
                columnVal[row] = i;//(row,columnVal[row)==>(row,i)
                
                if(isValid(row,columnVal))
                    DFS_helper(nQueens, res, row+1, columnVal);
            }
        }
    }
    
    public boolean isValid(int row, int [] columnVal){
        for(int i = 0; i < row; i++){
            if(columnVal[row] == columnVal[i]
               ||Math.abs(columnVal[row]-columnVal[i]) == row-i)
               return false;
        }
        return true;
		
		}
		
		
		
		
		
		使用全局变量来记录结果的代码是：
		
		int res;
    public int totalNQueens(int n) { 
        res = 0;
        if(n<=0)
            return res;
            
        int [] columnVal = new int[n];
        
        DFS_helper(n,0,columnVal);
        return res;
    }
    
    public void DFS_helper(int nQueens, int row, int[] columnVal){
        if(row == nQueens){
            res += 1;
        }else{
            for(int i = 0; i < nQueens; i++){
                columnVal[row] = i;//(row,columnVal[row)==>(row,i)
                
                if(isValid(row,columnVal))
                    DFS_helper(nQueens, row+1, columnVal);
            }
        }
    }
    
    public boolean isValid(int row, int [] columnVal){
        for(int i = 0; i < row; i++){
            if(columnVal[row] == columnVal[i]
               ||Math.abs(columnVal[row]-columnVal[i]) == row-i)
               return false;
        }
        return true;
    }
