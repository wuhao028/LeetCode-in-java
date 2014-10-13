

/** 
 * Implement pow(x, n) 
 */

public class PowXN {
	public double pow(double x, int n) {
		if (n == 0)
			return 1;
		else if (n % 2 == 0) {
			double d = pow(x, n / 2);
			return d * d;
		} else if (n > 0) {
			double d = pow(x, (n - 1) / 2);
			return d * d * x;
		} else {
			double d = pow(x, (n + 1) / 2);
			return d * d / x;
		}
	}
}


//my version   Time Limit Exceeded
分成两部分
{
        
        if(n==0)
         return 1L;
        
        if(x==0)
        return 0L;
        double res=1L;
        boolean flag=false;
        boolean flag2=false;
        if(n<0){
         n=-n;
         flag=true;
        }
        
        if(x<0 && n%2!=0){
         x=-x;
         flag2=true;
        }
        
        
        res=x;
        n=n-1;
        int t=1;
        while(t<n){
            res*=res;
            t*=2;
        }
        
        int m=n-t;

        for(int i=0;i<m;i++)
        {
            res=res*x;
        }
        
        
        
        if(flag)
         res=1/res;
         if(flag2)
         res=-res;
         
         return res;
    }
	
	
	// 递归  空间换时间
	public double power(double x, int n) {
        if (n == 0)
            return 1;
 
        double v = power(x, n / 2);
 
        if (n % 2 == 0) {
            return v * v;
        } else {
            return v * v * x;
        }
    }
 
    public double pow(double x, int n) {
        if (n < 0) {
            return 1 / power(x, -n);
        } else {
            return power(x, n);
        }
    }