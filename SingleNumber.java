

/**
 * Given an array of integers, every element appears twice except for one. Find
 * that single one.
 * 
 * Note: Your algorithm should have a linear runtime complexity. Could you
 * implement it without using extra memory?
 */

public class SingleNumber {
	public int singleNumber(int[] A) {
		int ret = A[0];
		for (int i = 1; i < A.length; i++) {
			ret ^= A[i];
		}
		return ret;
	}
}


利用hash，最坏情况下时间复杂度大于O(n),空间复杂度也较大
public class Solution {
    public int singleNumber(int[] A) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
         if(A.length == 0)
            return 0;
        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
		for(int a : A)
		{
			if(hash.containsKey(a))
			{
				hash.put(a, hash.get(a) + 1);
			}
			else hash.put(a, 1);
		}
		for(int key : hash.keySet())
		{
			if(hash.get(key) == 1)
				return key;
		}
		return 0;
    }
}



题解：

这道题运用位运算的异或。异或是相同为0，不同为1。所以对所有数进行异或，得出的那个数就是single number。初始时先让一个数与0异或，
然后再对剩下读数挨个进行异或。

这里运用到异或的性质：对于任何数x，都有x^x=0，x^0=x

代码如下：


     public int singleNumber(int[] A) {
         int result = 0;
         for(int i = 0; i<A.length;i++){
             result = result^A[i];
         }
         return result;
     }

 同时异或还有性质：

 交换律 A XOR B = B XOR A

 结合律 A XOR B XOR C = A XOR (B XOR C) = (A XOR B) XOR C

 自反性 A XOR B XOR B = A XOR 0 = A

所以对于这个数组来说，因为只有一个数字是single的，所以数组可以表示为 a a b b c c d d e。 那么利用上述规律可以异或结果为 0 0 0 0 e。
这样写的代码为：

 public static int singleNumber(int[] A) {
     for (int i = 1; i < A.length; i++) {
         A[i] ^= A[i-1];
     }
     return A[A.length-1];
 }




