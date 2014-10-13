

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Divide two integers without using multiplication, division and mod operator.
 */

public class DivideTwoIntegers {
	public int divide(int dividend, int divisor) {
		if (dividend == 0)
			return 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		BigDecimal d0 = new BigDecimal(dividend);
		d0 = d0.abs();
		BigDecimal d1;
		BigDecimal d3 = new BigDecimal(divisor);
		d3 = d3.abs();
		int count = 0;
		boolean start = true;
		while (d0.compareTo(d3) == 1 || d0.compareTo(d3) == 0) {
			d1 = new BigDecimal(divisor);
			d1 = d1.abs();
			start = true;
			while (d0.compareTo(d1) == 1 || d0.compareTo(d1) == 0) {
				if (start) {
					list.add(1);
					start = false;
				} else {
					int last = list.get(list.size() - 1);
					list.add(last + last);
				}
				d0 = d0.subtract(d1);
				d1 = d1.add(d1);
			}
		}

		for (int e : list) {
			count += e;
		}

		return (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0) ? count
				: -count;
	}
}



//my version 超时

    public int divide(int dividend, int divisor) {
        int sum=0;
        int t=0;
        boolean flag=false;
        
      if(divisor==0)
      return Integer.MAX_VALUE;
      if(dividend==0)
      return 0;
      
       if(dividend<0 && divisor<0){
        dividend=-dividend;
        divisor=-divisor;
       }
       
       if(dividend<0){
           dividend=-dividend;
           flag=true;
       }
       
       if(divisor<0){
           divisor=-divisor;
           flag=true;
       }
      
        for(int i=0;i<dividend;i++){
            while(sum<=dividend){
            sum=sum+divisor;
            t++;
            }
        }
        if(flag==true)
        return -t;
        else return t;
    }

	
	逐个减太慢，肯定超时。需要指数级增加后再减，思想类似于Linked List Cycle II中的Brent方法。

此外，还要注意零、负数的情况。
	public int divide(int dividend, int divisor) {
		if (dividend == 0 || divisor == 0) {
			return 0;
		}
		boolean isNeg = (dividend > 0 && divisor < 0)
				|| (dividend < 0 && divisor > 0);
		long a = Math.abs((long) dividend);
		long b = Math.abs((long) divisor);
		if (b > a) {
			return 0;
		}

		long sum = 0;
		long pow = 0;
		int result = 0;
		
		// 两层while循环！！！！
		while (a >= b) {
			pow = 1;
			sum = b;
			while (sum + sum <= a) {
				sum += sum;
				pow += pow;
			}
			a -= sum;
			result += pow;
		}
		return isNeg ? -result : result;
	}
    

	
	