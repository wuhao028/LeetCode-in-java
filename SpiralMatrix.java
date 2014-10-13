

import java.util.ArrayList;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * 
 * For example, Given the following matrix:
 * 
 * [ 
 *   [ 1, 2, 3 ],
 *   [ 4, 5, 6 ],
 *   [ 7, 8, 9 ]
 * ]
 * 
 * You should return [1,2,3,6,9,8,7,4,5].
 */

public class SpiralMatrix {
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (matrix.length == 0)
			return list;
		int beginX = 0, endX = matrix.length - 1;
		int beginY = 0, endY = matrix[0].length - 1;
		while (true) {
			for (int i = beginY; i <= endY; i++) {
				list.add(matrix[beginX][i]);
			}
			if (++beginX > endX)
				break;
			for (int i = beginX; i <= endX; i++) {
				list.add(matrix[i][endY]);
			}
			if (beginY > --endY)
				break;
			for (int i = endY; i >= beginY; i--) {
				list.add(matrix[endX][i]);
			}
			if (beginX > --endX)
				break;
			for (int i = endX; i >= beginX; i--) {
				list.add(matrix[i][beginY]);
			}
			if (++beginY > endY)
				break;
		}
		return list;
	}
}


public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0)
            return result;
 
        int m = matrix.length;
        int n = matrix[0].length;
 
        int x=0; 
        int y=0;
 
        while(m>0 && n>0){
 
            //if one row/column left, no circle can be formed
            if(m==1){
                for(int i=0; i<n; i++){
                    result.add(matrix[x][y++]);
                }
                break;
            }else if(n==1){
                for(int i=0; i<m; i++){
                    result.add(matrix[x++][y]);
                }
                break;
            }
 
            //below, process a circle
 
            //top - move right
            for(int i=0;i<n-1;i++)
                result.add(matrix[x][y++]);
 
            //right - move down
            for(int i=0;i<m-1;i++)
                result.add(matrix[x++][y]);
 
            //bottom - move left
            for(int i=0;i<n-1;i++)
                result.add(matrix[x][y--]);
 
            //left - move up
            for(int i=0;i<m-1;i++)
                result.add(matrix[x--][y]);
 
            x++;
            y++;
            m=m-2;
            n=n-2;
        }
 
        return result;
    }