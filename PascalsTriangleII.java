/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * 
 * For example, given k = 3, Return [1,3,3,1].
 * 
 * Note:
 * 
 * Could you optimize your algorithm to use only O(k) extra space?
 */

import java.util.ArrayList;

public class PascalsTriangleII {
	public ArrayList<Integer> getRow(int rowIndex) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int[] tmp = new int[rowIndex + 1];
		for (int i = 0; i <= rowIndex; i++) {
			ret.add(1);
			tmp[i] = 1;
		}
		for (int k = 2; k <= rowIndex; k++) {
			int mid = k / 2;
			for (int i = 1; i <= mid; i++) {
				ret.set(i, tmp[i - 1] + tmp[i]);
			}
			for (int m = k; m > mid; m--) {
				ret.set(m, ret.get(k - m));
			}
			for (int n = 0; n <= k; n++) {
				tmp[n] = ret.get(n);
			}
		}
		return ret;
	}
}
public ArrayList<Integer> getRow(int rowIndex) {  
      ArrayList<Integer> result = new ArrayList<Integer>(rowIndex + 1);  
      for (int i = 0; i <= rowIndex; i++) {  
        result.add(0);  
      }  
      result.set(0, 1);  
      for (int i = 1; i <= rowIndex; i++) {  
        result.set(i, 1);  
        for (int j = i - 1; j > 0; j--) {  
          result.set(j, result.get(j) + result.get(j - 1));  
        }  
      }  
      return result;  
    }

	
	
	
	
	