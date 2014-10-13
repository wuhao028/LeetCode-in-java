

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 * 
 * Some hints: Could negative integers be palindromes? (ie, -1)
 * 
 * If you are thinking of converting the integer to string, note the restriction
 * of using extra space.
 * 
 * You could also try reversing an integer. However, if you have solved the
 * problem "Reverse Integer", you know that the reversed integer might overflow.
 * How would you handle such case?
 * 
 * There is a more generic way of solving this problem.
 */

public class PalindromeNumber {
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		int k = 1;
		while (x / k >= 10) {
			k *= 10;
		}
		while (x >= 10) {
			int left = x / k;
			int right = x - x / 10 * 10;
			if (left != right)
				return false;
			x = (x - x / k * k) / 10;
			k /= 100;
		}
		return true;
	}
}


//my version
    public boolean isPalindrome(int x) {
     int n=0;
     if(x<0)return false;
     
     int y=x;
     while(y!=0){
         y=y/10;
         n++;
     }
     
     int[] A=new int[n];
     for(int i=0;i<n;i++){
         A[i]=x%10;
         x=x/10;
     }
     
     for(int i=0;i<n/2;i++){
         if(A[i]!=A[n-1-i]){
             return false;
         }
         continue;
         }
         
         return true;
     }
	 
	 
	 只能逐个比较整数的第一个字符和最后一个字符，比如12321 第一次比较成功后数字变为232，再次比价后变为3
	 class Solution {
public:
    bool isPalindrome(int x) {
        if(x < 0)return false;
        int len = integerLength(x);
//求指数
        int factor1 = pow(10, len-1);
        while(x != 0)
        {
            if(x / factor1 != x % 10)
                return false;
            x %= factor1;
            x /= 10;
            factor1 /= 100;
        }
        return true;
    }
     
    //求整数x的长度 
    int integerLength(int x)
    {
        int res = 0;
        while(x)
        {
            x /= 10;
            res++;
        }
        return res;
    }
};