

import java.util.ArrayList;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * 
 * For example, given numRows = 5, Return
 * 
 * [      
 *        [1], 
 *       [1,1], 
 *      [1,2,1], 
 *     [1,3,3,1], 
 *    [1,4,6,4,1] 
 * ]
 */

public class PascalTriangle {
	public ArrayList<ArrayList<Integer>> pascalTriangle(int numRows) {
		ArrayList<ArrayList<Integer>> pt = new ArrayList<ArrayList<Integer>>();
		int k = 1;
		for (int i = 0; i < numRows; i++) {
			ArrayList<Integer> r = new ArrayList<Integer>();
			for (int j = 0; j < k; j++) {
				r.add(1);
			}
			k++;
			pt.add(r);
		}
		for (int f = 2; f < pt.size(); f++) {
			ArrayList<Integer> prev = pt.get(f - 1);
			ArrayList<Integer> current = pt.get(f);
			for (int g = 1; g < current.size() - 1; g++) {
				current.set(g, prev.get(g - 1) + prev.get(g));
			}
		}
		return pt;
	}
}


public class Solution {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        if(numRows == 0)
            return res;
        
       for(int j = 0;j<numRows;j++){
           List<Integer> row = new ArrayList<Integer>();
           row.add(1);
        for(int i=1;i<j;i++){//除去第一行和第二行才进这个循环
            List<Integer> prevRow = res.get(j-1);//当前行的上一行
            int temp = prevRow.get(i-1)+prevRow.get(i);
            row.add(temp);
        }
        if(j!=0)//除了第一行，末尾接个1
            row.add(1);
        res.add(row);
       }
       return res;
    }