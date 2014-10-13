

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * For example: A = [2,3,1,1,4], return true.
 * 
 * A = [3,2,1,0,4], return false.
 */

 
 // 并非必须在i处跳A[i],A[i]只是最大距离
public class JumpGame {
	public boolean canJump(int[] A) {
		if (A.length <= 1)
			return true;
		int curMax = 0;
		int max = 0;
		for (int i = 0; i < A.length - 1; i++) {
			if (i > max)
				break;
			curMax = A[i] + i;
			if (curMax > max) {
				max = curMax;
			}
			if (max >= A.length - 1)
				return true;
		}
		return false;
	}
}



public boolean canJump(int[] A) {  
            int maxCover = 0;  
            for(int start =0; start<= maxCover && start<A.length; start++)  
            {  
                 if(A[start]+start > maxCover)  
                      maxCover = A[start]+start;  
                 if(maxCover >= A.length-1) 
                    return true;  
            }  
            return false;  
       }
	
	
	
	
	
	
	
	
	
	
	
	