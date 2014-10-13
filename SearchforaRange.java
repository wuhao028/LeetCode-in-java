

/**
 * Given a sorted array of integers, find the starting and ending position of a
 * given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * For example, Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
 */

public class SearchforaRange {
	public int[] searchRange(int[] A, int target) {
		int low = findLow(A, target, 0, A.length - 1);
		int high = findHigh(A, target, 0, A.length - 1);
		int[] ret = new int[2];
		ret[0] = low;
		ret[1] = high;
		return ret;
	}

	private int findLow(int[] A, int target, int l, int r) {
		int mid = 0;
		int ret = -1;
		while (l <= r) {
			mid = (l + r) / 2;
			if (A[mid] == target) {
				ret = mid;
				int next = findLow(A, target, l, mid - 1);
				if (next != -1) {
					ret = next;
				}
				break;
			} else if (A[mid] < target) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}

		}
		return ret;
	}

	private int findHigh(int[] A, int target, int l, int r) {
		int mid = 0;
		int ret = -1;
		while (l <= r) {
			mid = (l + r) / 2;
			if (A[mid] == target) {
				ret = mid;
				int next = findHigh(A, target, mid + 1, r);
				if (next != -1) {
					ret = next;
				}
				break;
			} else if (A[mid] < target) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return ret;
	}
}


public int[] searchRange(int[] A, int target) {
        int [] res = {-1,-1};
        if(A == null || A.length == 0)
            return res;
        
        //first iteration, find target wherever it is
        int low = 0;
        int high = A.length-1;
        int pos = 0;
        while(low <= high){
            int mid = (low + high)/2;
            pos = mid;
            if(A[mid] > target)
                high = mid - 1;
            else if(A[mid] < target)
                low = mid + 1;
            else{
                res[0] = pos;
                res[1] = pos;
                break;
            }
        }
        
        if(A[pos] != target)
            return res;
        
        //second iteration, find the right boundary of this target
        int newlow = pos;
        int newhigh = A.length-1;
        while(newlow <= newhigh){
            int newmid = (newlow+newhigh)/2;
            if(A[newmid] == target)
                newlow = newmid + 1;
            else
                newhigh = newmid - 1;
        }
        res[1] = newhigh;
        
        //third iteration, find the left boundary of this target
        newlow = 0;
        newhigh = pos;
        while(newlow <= newhigh){
            int newmid = (newlow+newhigh)/2;
            if(A[newmid] == target)
                newhigh = newmid - 1;
            else
                newlow = newmid + 1;
        }
        res[0] = newlow;
        
        return res;
    }
	
	
//迭代二分查找提示  stackoverflow

    public int[] searchRange(int[] A, int target) {
        int[] res=new int[2];
        int n=A.length;
        if(A[0]>target || A[n-1]<target){
           res[0]=-1;
           res[1]=-1;
           return res;
        }
        
    
     res[0]=helperleft(A,target,0,n-1);
     res[1]=helperright(A,target,0,n-1);
     return res;
    }
    
    private int helperleft(int[] A,int target,int left,int right){
       
         if(A[left]==target)
          return left;
         
        if(A[(left+right)/2]<target){
          left=(left+right)/2;
          helperleft(A,target,left,right);
        }
        if(A[(left+right)/2]>=target){
         right=(left+right)/2;
          helperleft(A,target,left,right);
        }
        
        
         
          return left;
    }
    
    
     private int helperright(int[] A,int target,int left,int right){
       
        if(A[right]==target)
         return right;
        
        if(A[(left+right)/2]<=target){
          left=(left+right)/2;
           helperright(A,target,left,right);
        }
        if(A[(left+right)/2]>target){
         right=(left+right)/2;
          helperright(A,target,left,right);
        }
        
        
         
         return right;
         
    }
    
    
