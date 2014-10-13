/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * 
 * For example,
 * 
 * There exist two distinct solutions to the 4-queens puzzle:
 * 
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 *  ]
 */

import java.util.ArrayList;

public class NQueens {
	public ArrayList<String[]> solveNQueens(int n) {
		ArrayList<String[]> ret = new ArrayList<String[]>();
		if (n == 0)
			return ret;
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
		findSolutions(n, 0, ret, sol, row, cols);
		return ret;
	}

	private void findSolutions(int n, int start, ArrayList<String[]> ret,
			StringBuffer[] sol, int[] row, boolean[] cols) {
		if (start == n) {
			String[] newSol = new String[n];
			for (int i = 0; i < n; i++) {
				newSol[i] = sol[i].toString();
			}
			ret.add(newSol);
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
					findSolutions(n, start + 1, ret, sol, row, cols);
					row[start] = 0;
					sol[start].setCharAt(i, '.');
					cols[i] = false;
				}
			}
		}
	}
}

题解：
这道题很经典，网上有很多讲解实例。
在国际象棋中，皇后最强大，可以横竖斜的走（感谢AI课让我稍微对国际象棋了解了一下）。而八皇后问题就是让8个皇后中的每一个的横竖斜都没有其他皇后，这样其实感觉是一种和棋的状态。。
不知道说的对不对。。。毕竟真实棋盘中不能放8个皇后。。
额。。这道题是N皇后，我说着说着就说到了8皇后去了。。其实是一样的。。。

我这道题的解法也是参考了网上的一些人的讲法。
经典的DFS递归回溯解法，大体思路就是对每一行，按每一列挨个去试，试到了就保存结果没试到就回溯。
难点大概就是用1个一维数组存皇后所在的坐标值。对于一个棋盘来说，每个点都有横纵坐标，用横纵坐标可以表示一个点。
而这道题巧就巧在，每一行只能有一个皇后，也就是说，对于一行只能有一个纵坐标值，所以用1维数组能提前帮助解决皇后不能在同一行的问题。
那么用一维数组表示的话，方法是：一维数组的下标表示横坐标（哪一行），而数组的值表示纵坐标（哪一列）。

例如：对于一个4皇后问题，声明一个长度为4的数组（因为行数为4）。
     A[] = [1,0,2,3]表达含义是：
     当前4个皇后所在坐标点为：[[0,1],[1,0],[2,2],[3,3]]（被我标蓝的正好是数组的下标，标粉的正好是数组的值）
     相当于：A[0] = 1, A[1] = 0, A[2] = 2, A[3] = 3 

这样以来，皇后所在的坐标值就能用一维数组表示了。
而正是这个一维数组，在回溯找结果的时候不需要进行remove重置操作了，因为回溯的话正好就回到上一行了，就可以再重新找下一个合法列坐标了。

因为是按照每一行去搜索的，当行坐标值等于行数时，说明棋盘上所有行都放好皇后了，这时就把有皇后的位置标为Q，没有的地方标为0。
按照上面讲的那个一维数组，我们就可以遍历整个棋盘，当坐标为（row，columnVal[row]）的时候，就说明这是皇后的位置，标Q就行了。

public ArrayList<String[]> solveNQueens(int n) {
        ArrayList<String[]> res = new ArrayList<String[]>();
        if(n<=0)
            return res;
            
        int [] columnVal = new int[n];
        
        DFS_helper(n,res,0,columnVal);
        return res;
    }
    
    public void DFS_helper(int nQueens, ArrayList<String[]> res, int row, int[] columnVal){
        if(row == nQueens){
            String[] unit = new String[nQueens];
            for(int i = 0; i < nQueens; i++){
                StringBuilder s = new StringBuilder();
                for(int j = 0; j < nQueens; j++){
                    if(j == columnVal[i])
                        s.append("Q");
                    else
                        s.append(".");
                }
                
                unit[i] = s.toString();
            }
            res.add(unit);
        }else{
            for(int i = 0; i < nQueens; i++){
			因为是一维数组所以后面不用remove，这里会直接取代；
                columnVal[row] = i;//(row,columnVal[row)==>(row,i)
                
                if(isValid(row,columnVal))
                    DFS_helper(nQueens, res, row+1, columnVal);
            }
        }
    }
    
    public boolean isValid(int row, int [] columnVal){
        for(int i = 0; i < row; i++){
            if(columnVal[row] == columnVal[i]
			//判断对角线上是否满足条件
               ||Math.abs(columnVal[row]-columnVal[i]) == row-i)
               return false;
        }
        return true;
    }