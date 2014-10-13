

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * 
 * Here are some examples. 
 * 
 * Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 -> 1,3,2
 * 3,2,1 -> 1,2,3
 * 1,1,5 -> 1,5,1
 */

public class NextPermutation {
	public void nextPermutation(int[] num) {
		int i1 = 0;
		int i2 = 0;
		int i = num.length - 1;
		int j = 0;
		while (i > 0 && num[i - 1] >= num[i]) {
			i--;
		}
		if (i == 0) {
			Arrays.sort(num);
			return;
		} else {
			i1 = i - 1;
		}
		j = i1 + 1;
		while (j < num.length && num[i1] < num[j]) {
			j++;
		}
		i2 = j - 1;
		int temp = num[i1];
		num[i1] = num[i2];
		num[i2] = temp;
		Arrays.sort(num, i1 + 1, num.length);
	}
}


 本文讲解转自Code Ganker稍稍修改“http://blog.csdn.net/linhuanmars/article/details/20434115”

“这道题是给定一个数组和一个排列，求下一个排列。算法上其实没有什么特别的地方，主要的问题是经常不是一见到这个题就能马上理清思路。下面我们用一个例子来说明，比如排列是(2,3,6,5,4,1)，求下一个排列的基本步骤是这样：
1) 先从后往前找到第一个不是依次增长的数，记录下位置p。比如例子中的3，对应的位置是1;
2) 接下来分两种情况：
    (1) 如果上面的数字都是依次增长的，那么说明这是最后一个排列，下一个就是第一个，其实把所有数字反转过来即可(比如(6,5,4,3,2,1)下一个是(1,2,3,4,5,6));
    (2) 否则，如果p存在，从p开始往后找，找找找，找到第一个比他小的数，然后指针再挪回这个数的前一个数，然后两个调换位置，比如例子中的4。调换位置后得到(2,4,6,5,3,1)。最后把p之后的所有数字倒序，比如例子中得到(2,4,1,3,5,6), 这个即是要求的下一个排列。
以上方法中，最坏情况需要扫描数组三次，所以时间复杂度是O(3*n)=O(n)，空间复杂度是O(1)。代码如下：”
public class Solution {
    //http://blog.csdn.net/linhuanmars/article/details/20434115
    /*
    假设数组大小为 n
        1.从后往前，找到第一个 A[i-1] < A[i]的。也就是第一个排列中的  6那个位置，可以看到A[i]到A[n-1]这些都是单调递减序列。
        2.从 A[n-1]到A[i]中找到一个比A[i-1]大的值（也就是说在A[n-1]到A[i]的值中找到比A[i-1]大的集合中的最小的一个值）
        3.交换 这两个值，并且把A[n-1]到A[i]排序，从小到大。
    */
    public void nextPermutation(int[] num) {  
        if(num==null || num.length==0)  
            return;  
        int i = num.length-2;  
        while(i>=0 && num[i]>=num[i+1])  
            i--;
        
        if(i>=0){  
            int j=i+1;  
            while(j<num.length && num[j]>num[i])
                j++;
            j--;  
            swap(num,i,j);  
        }  
        reverse(num, i+1,num.length-1);  
    }    
    private void swap(int[] num, int i, int j){  
        int tmp = num[i];  
        num[i] = num[j];  
        num[j] = tmp;  
    }  
    private void reverse(int[] num, int i, int j){  
        while(i < j)  
            swap(num, i++, j--);  
    }