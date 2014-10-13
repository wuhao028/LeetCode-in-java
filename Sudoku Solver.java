��Ŀ��

Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.


A sudoku puzzle...


...and its solution numbers marked in red.

 

��⣺
��һ��Ӧ����N�ʺ����⡣����һ��㳢�������������еĻ��ͻ��ݣ�ֱ���������ͷ��ء� 
�����һ�����ӳ��Դ�0~9�����У���ô˵������sudoku�޽⣬����false�ͺá�

��������������'.'�������ˣ���ô�Ϳ��Է���true��

public void solveSudoku(char[][] board) {
        if (board==null||board.length==0)
            return;
        helper(board);
    }
    
    private boolean helper(char[][] board){
        for(int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                if (board[i][j]=='.'){
				
				
				//ע����char  ����int  �������ʾ loss precission
                    for (char num='1'; num<='9'; num++){//����
                        if(isValid(board, i, j, num)){
                            board[i][j]=num;
                            
                            if (helper(board))
                                return true;
                            else
                                board[i][j]='.';//����
                        }
                    }
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean isValid(char[][] board, int i, int j, char c){
        // check column
        for (int row=0; row<9; row++)
            if (board[row][j] == c)
                return false;
        
       // check row
        for (int col=0; col<9; col++)
            if (board[i][col]==c)
                return false;
      
        // check block
        for(int row=i/3*3; row<i/3*3+3; row++)
            for (int col=j/3*3; col<j/3*3+3; col++)
                if (board[row][col]==c)
                    return false;
                    
        return true;
    }
	
	
	�ҵĴ��룺
	
	Time Limit Exceeded��
	
	public class Solution {
    public void solveSudoku(char[][] board) {
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    for(int t=1;t<10;t++){
                        board[i][j]=(char)t;
                        if(isValidSudoku(board)){ solveSudoku(board); }
                        else board[i][j]='.';
                    }
                }
            }
        }
    }
    
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
}