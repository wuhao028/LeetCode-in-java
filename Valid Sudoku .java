Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

 
��⣺

 ��������õ���HashSet��Ψһ��������check��

�Ȱ�ÿ��check�������'.'˵����û���֣��ǺϷ��ģ������ߣ����û��set�д���ͼ�һ�£�������������г�������set�д��ڵ�keyֵ��˵�����ظ���������һ�У����Ϸ���return false��

�ٰ����������check�С�

������������checkС���顣

ע��С�����ijȡ�������ڵ�ǰ��������˵���ܹ���9��С���񣬰�0~8���������α�š�

�������'/'������õ�ǰС����ĵ�һ�к����꣬��Ϊÿ��С������3�У�����ѭ��3�Ρ�

�������'%'������õ�ǰС����ĵ�һ�������꣬��Ϊÿ��С������3�У�����ѭ��3�Ρ� 

��9��С����������һ�ߣ�������˼��С����Ĺ�����

�������£�



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



��С�������֤��Ҳ�������ĸ�ѭ�������У�
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








