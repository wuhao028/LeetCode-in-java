

/**
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Follow up: Could you do this in-place?
 */
 

public class RotateImage {
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		if (n == 0)
			return;
			
			//沿着主对角线翻转
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int t = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = t;
			}
		}
		//沿着垂直中线翻转
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n / 2; j++) {
				int t = matrix[i][j];
				matrix[i][j] = matrix[i][n - j - 1];
				matrix[i][n - j - 1] = t;
			}
		}
	}
}

 
 //先沿着副对角线翻转一次，然后沿着水平中线翻转一次
 //副对角线是左下  到 右上的那一条
//或者先沿着水平中线翻转一次，然后沿着主对角线翻转一次。

class Solution {
public:
    void rotate(vector<vector<int> > &matrix) {
        int i,j,temp;
        int n=matrix.size();
        // 沿着副对角线反转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n - i; ++j) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][n - 1 - i];
                matrix[n - 1 - j][n - 1 - i] = temp;
            }
        }
        // 沿着水平中线反转
        for (int i = 0; i < n / 2; ++i){
            for (int j = 0; j < n; ++j) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
        }
    }