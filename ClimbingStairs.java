

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 */

public class ClimbingStairs {
	public int climbStairs(int n) {
		int[] arr = new int[n + 1];
		arr[0] = 1;
		arr[1] = 1;
		for (int i = 2; i <= n; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		return arr[n];
	}
}

//递归超时，迭代不超时
//my version

    public int climbStairs(int n) {
        
         if(n==1)
       return 1;
       
       if(n==2)
        return 2;

       int[] A=new int[n];
       A[0]=1;
       A[1]=2;
       for(int i=2;i<n;i++){
           A[i]=A[i-1]+A[i-2];
       }
           return A[n-1];
    
   
            }
