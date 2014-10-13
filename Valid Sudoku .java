Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

 
题解：

 这道题利用的是HashSet的唯一性来帮助check。

先按每行check，如果是'.'说明还没填字，是合法的，往下走，如果没在set中存过就加一下，如果便利过程中出现了在set中存在的key值，说明有重复的数字在一行，不合法，return false。

再按照这个方法check列。

最后按照这个方法check小方块。

注意小方块的ij取法。对于当前这块板子来说，总共有9个小方格，按0~8从左到右依次编号。

按编号求'/'就是求得当前小方格的第一行横坐标，因为每个小方格有3行，所以循环3次。

按编号求'%'就是求得当前小方格的第一列纵坐标，因为每个小方格有3列，所以循环3次。 

对9个小方格依次走一边，就完成了检查小方格的工作。

代码如下：



public boolean isValidSudoku(char[][] board) {
    HashSet<Character> set = new HashSet<Character>();
    // Check for each row
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            if (board[i][j] == '.')
                continue;
            if (set.contains(board[i][j]))
                return false;
            set.add(board[i][j]);
        }
        set.clear();
    }

    // Check for each column
    for (int j = 0; j < 9; j++) {
        for (int i = 0; i < 9; i++) {
            if (board[i][j] == '.')
                continue;
            if (set.contains(board[i][j]))
                return false;
            set.add(board[i][j]);
        }
        set.clear();
    }

    // Check for each sub-grid
    for (int k = 0; k < 9; k++) {
        for (int i = k/3*3; i < k/3*3+3; i++) {
            for (int j = (k%3)*3; j < (k%3)*3+3; j++) {
                if (board[i][j] == '.')
                    continue;
                if (set.contains(board[i][j]))
                    return false;
                set.add(board[i][j]);
            }
        }
        set.clear();
    }
    
    return true;
}



在小方块的验证中也可以用四个循环来进行：
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        
        HashSet<Character> hash= new  HashSet<Character>();
        
        for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            if (board[i][j] == '.')
                continue;
            if (hash.contains(board[i][j]))
                return false;
            hash.add(board[i][j]);
        }
        hash.clear();
    }

    // Check for each column
    for (int j = 0; j < 9; j++) {
        for (int i = 0; i < 9; i++) {
            if (board[i][j] == '.')
                continue;
            if (hash.contains(board[i][j]))
                return false;
            hash.add(board[i][j]);
        }
        hash.clear();
    }

        
        for(int n=0;n<3;n++){
          for(int m=0;m<3;m++){
            for(int j=0;j<3;j++){
                for(int i=0;i<3;i++){
                int a=i+n*3;
                int b=j+3*m;
              if (board[a][b] == '.')
                    continue;
                if (hash.contains(board[a][b]))
                    return false;
                hash.add(board[a][b]);
                }
        }
        hash.clear();
        }
        }
          return true;
         
         
         
    }
}








