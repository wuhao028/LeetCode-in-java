

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell, where
 * "adjacent" cells are those horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 * 
 * For example, Given board =
 * 
 * [ ["ABCE"], 
 *   ["SFCS"], 
 *   ["ADEE"] 
 * ] 
 * 
 * word = "ABCCED", -> returns true, 
 * word = "SEE", -> returns true, 
 * word = "ABCB", -> returns false.
 */

public class WordSearch {
	public boolean exist(char[][] board, String word) {
		int height = board.length;
		int width = board[0].length;
		boolean[][] map = new boolean[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (search(board, map, i, j, word, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean search(char[][] board, boolean[][] map, int x, int y,
			String word, int index) {
		if (word.charAt(index) != board[x][y]) {
			return false;
		}
		if (index == word.length() - 1) {
			return true;
		}

		int height = board.length;
		int width = board[0].length;
		map[x][y] = true;

		if (x > 0 && !map[x - 1][y]
				&& search(board, map, x - 1, y, word, index + 1)) {
			return true;
		}

		if (x < height - 1 && !map[x + 1][y]
				&& search(board, map, x + 1, y, word, index + 1)) {
			return true;
		}

		if (y > 0 && !map[x][y - 1]
				&& search(board, map, x, y - 1, word, index + 1)) {
			return true;
		}

		if (y < width - 1 && !map[x][y + 1]
				&& search(board, map, x, y + 1, word, index + 1)) {
			return true;
		}

		map[x][y] = false;

		return false;
	}
}





题解：

 这道题分析看，就是一个词，在一行出现也是true，一列出现也是true，一行往下拐弯也是true，一行往上拐弯也是true，
 一列往左拐弯也是true，一列往右拐弯也是true。所以是要考虑到所有可能性，基本思路是使用DFS来对一个起点字母上下左右搜索，
 看是不是含有给定的Word。还要维护一个visited数组，表示从当前这个元素是否已经被访问过了，过了这一轮visited要回false，
 因为对于下一个元素，当前这个元素也应该是可以被访问的。

public boolean exist(char[][] board, String word) {
        int m = board.length;  
        int n = board[0].length;  
        boolean[][] visited = new boolean[m][n];  
        for (int i = 0; i < m; i++) {  
            for (int j = 0; j < n; j++) {  
                if (dfs(board, word, 0, i, j, visited))  
                    return true;  
            }  
        }  
        return false;  
    }
    
    public boolean dfs(char[][] board, String word, int index, int rowindex, int colindex, boolean[][] visited) {  
        if (index == word.length())  
            return true;  
        if (rowindex < 0 || colindex < 0 || rowindex >=board.length || colindex >= board[0].length)  
            return false;  
        if (visited[rowindex][colindex])  
            return false;  
        if (board[rowindex][colindex] != word.charAt(index))  
            return false;  
        visited[rowindex][colindex] = true;  
        boolean res = dfs(board, word, index + 1, rowindex - 1, colindex,  
                visited)  
                || dfs(board, word, index + 1, rowindex + 1, colindex, visited)  
                || dfs(board, word, index + 1, rowindex, colindex + 1, visited)  
                || dfs(board, word, index + 1, rowindex, colindex - 1, visited);  
        visited[rowindex][colindex] = false;  
        return res;  
            }
			
			
			
//my version  结构差不多，有错，没有debug
// 我的版本和上面的版本比起来，有冗余代码，不够简洁！
			public class Solution {
    public boolean exist(char[][] board, String word) {
        
        int n=word.length();
        int a=board.length;
        int b=board[0].length;
        int m=0;
        boolean[][] visit=new boolean[a][b];
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
               if(board[i][j]==word.charAt(m)){
                   visit[i][j]=ture;
                   dfs(board,word,i,j,visit,m+1);
                   visit[i][j]=false;
               }
            }
        }

    }
    
    public boolean dfs(char[][] board, String word,int i,int j,boolean[][] visit,int m){
        if(m>=n)
          return true;
          
        if(i-1>=0 && visit[i-1][j]==false && board[i-1][j]==s.charAt(m)){
           visit[i-1][j]=true;
           dfs(board,word,i-1,j,visit,m+1);
		   visit[][]=false;//这个忘了写上！！！，下面的同此处！！！
        }
        
        if(i+1<a && visit[i+1][j]==false && board[i+1][j]==s.charAt(m)){
           visit[i+1][j]=true;
           dfs(board,word,i+1,j,visit,m+1);
        }
        
        if(j-1>=0 && visit[i][j-1]==false && board[i][j-1]==s.charAt(m)){
           visit[i][j-1]=true;
           dfs(board,word,i,j-1,visit,m+1);
        }
        
        
        if(j+1<b && visit[i][j+1]==false && board[i][j+1]==s.charAt(m)){
           visit[i][j+1]=true;
           dfs(board,word,i,j+1,visit,m+1);
        }
        
    }
    
}