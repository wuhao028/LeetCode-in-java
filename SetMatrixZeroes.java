

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0.
 * Do it in place.
 * 
 * Follow up: 
 * Did you use extra space? 
 * A straight forward solution using O(mn) space is probably a bad idea. 
 * A simple improvement uses O(m + n) space, but still not the best solution. 
 * Could you devise a constant space solution?
 */
 
 空间复杂度为O（m*n）
public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        if(m==0||n==0)
            return;
        int[] flagr = new int[m];
        int[] flagc = new int[n];
        // 先标记，再修改，否在标记后的数字会影响判断。
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    flagr[i]= 1; 
                    flagc[j]= 1;
                }
            }
        }
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(flagr[i]==1||flagc[j]==1){
                    matrix[i][j]=0;
                }
            }
        }
    }
	
	
	
分析：O(mn)解法：用m*n的空间记录每个某个位置是否要设置成0。O(m+n)解法
：用一个数组记录某一列是否需要设置成0，用一个数组记录某一行是否需要设置成0。
常数空间呢？如果我们可以用matrix本身的空间来记录，就不需要额外的空间了。解法如下：

用matrix第一行记录相应的列是都需要置0，第一列来记录相应的行是否需要置0。
用来记录之前需要提前判断第一行和第一列是否需要置0。然后遍历matrix（出了第一行和第一列）
如果matrix[i][j]等于0，那么matrixp[i][0] = 0 (行标志)，matrix[0][j] = 0(列标志)。
再次遍历matrix只要对应位置的行标志或者列标志等于0，相应的位置就置0,。
最后还要看第一行和第一列是否需要置0.

class Solution {
public:
    void setZeroes(vector<vector<int> > &matrix) {
        //使用第一行和第一列作为记录空间
        int row = matrix.size();
        if(row == 0)return;
        int col = matrix[0].size();
        bool firstRow = false, firstCol = false;//第一行和第一列是否要清0的标志
        for(int i = 0; i < col; i++)
            if(matrix[0][i] == 0){firstRow = true; break;}
        for(int i = 0; i < row; i++)
            if(matrix[i][0] == 0){firstCol = true; break;}
             
        for(int i = 1; i < row; i++)
            for(int j = 1; j < col; j++)
                if(matrix[i][j] == 0)
                {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
        for(int i = 1; i < row; i++)
            for(int j = 1; j < col; j++)
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
                     
        if(firstRow)
            for(int i = 0; i < col; i++)
                matrix[0][i] = 0;
        if(firstCol)
            for(int i = 0; i < row; i++)
                matrix[i][0] = 0;
    }
};
	
	
	
	
	