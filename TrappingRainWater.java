/**
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * For example,
 * 
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In
 * this case, 6 units of rain water (blue section) are being trapped. Thanks
 * Marcos for contributing this image!
 * 
 */

public class TrappingRainWater {
	public int trap(int A[], int n) {
		if (n <= 2)
			return 0;

		int[] lmh = new int[n];
		lmh[0] = 0;
		int maxh = A[0];
		for (int i = 1; i < n; ++i) {
			lmh[i] = maxh;
			if (maxh < A[i])
				maxh = A[i];
		}
		int trapped = 0;
		maxh = A[n - 1];
		for (int i = n - 2; i > 0; --i) {
			int left = lmh[i];
			int right = maxh;
			int container = Math.min(left, right);
			if (container > A[i]) {
				trapped += container - A[i];
			}
			if (maxh < A[i])
				maxh = A[i];
		}
		return trapped;
	}
}

// my version wrong
// 没能具体实现求出left right 最大柱子高度

     {   
        int n=A.length;
        int i=0,j=0;
        
        while(j<n){
            part=0;
           if(A[i+1]<A[i &  A[i-1]<A[i] & A[j-1]<A[j] & A[j]>A[j+1]){
               for(int t=i;t<=j;t++){
                   part+=A[t];
               }           
                sum += (A[j]-A[i])*(j-1) -part;
           }
            i=j;
            j++;
			}
        }

		
	分析某个柱子，发现该柱子上水的高度和其左右两边的最高柱子有关，设该柱子左边所有柱子中最高的为leftmax，
右边所有柱子中最高的为rightmax，如果min(leftmax, rightmax) 大于该柱子的高度，那么该柱子上可以蓄水为
min(leftmax, rightmax) - 该柱子高度，如果min(leftmax, rightmax) <= 该柱子高度，则该柱子上没有蓄水。
可以从后往前扫描一遍数组求得某个柱子右边的最高柱子，从前往后扫描一遍数组求得某个柱子左边的最高柱子, 
然后按照上面的分析可以求得所有的蓄水量。
		
		public int trap(int[] A) {  
        if (A == null || A.length == 0)  
            return 0;  
          
        int i, max, total = 0;
        int left[] = new int[A.length];
        int right[] = new int[A.length];  
  
        // from left to right
        left[0] = A[0];
        max = A[0];
        for (i = 1; i < A.length; i++) {  
            left[i] = Math.max(max, A[i]);
            max = Math.max(max, A[i]);
        }  
  
        // from right to left
        right[A.length-1] = A[A.length-1];
        max = A[A.length-1];
        for (i = A.length-2; i >= 0; i--) {  
            right[i] = Math.max(max, A[i]);
            max = Math.max(max, A[i]);
        }  
  
        // trapped water (when i==0, it cannot trapped any water)
        for (i = 1; i < A.length-1; i++) {  
            int bit = Math.min(left[i], right[i]) - A[i];  
            if (bit > 0)  
                total += bit;  
        }  
  
        return total;  
    }
	
	
	上面的代码时间空间复杂度都是O（n），扫描了两次数组

 

算法5：可以换一种思路，如下图，如果我们求出了两个红色框的面积，然后再减去框内黑色柱子的面积，就是水的面积了，时间复杂度O(N),空间O(1), 数组扫描2次

image

如何求红色框内的面积呢，我们先求出最大的柱子高度，然后左右分开求。求面积是是一层一层的累加

class Solution {
public:
    int trap(int A[], int n) {
        int maxHeight = 0, maxIdx = 0;
        for(int i = 0; i < n; i++)//求最大高度
            if(A[i] > maxHeight)
            {
                maxHeight = A[i];
                maxIdx = i;
            }
        int height = 0;
        int pillarArea = 0;//柱子面积
        int totalArea = 0;//总面积
        for(int i = 0; i < maxIdx; i++)
        {
            if(A[i] > height)
            {
                totalArea += (A[i] - height)*(maxIdx - i);
                height = A[i];
            }
            pillarArea += A[i];
        }
        height = 0;
        for(int i = n-1; i > maxIdx; i--)
        {
            if(A[i] > height)
            {
                totalArea += (A[i] - height)*(i - maxIdx);
                height = A[i];
            }
            pillarArea += A[i];
        }
        return totalArea - pillarArea;
    }
};
 

算法6：参考here，也是和算法5一样求面积，但是这里利用上一题的左右指针思想，只需要扫描一遍数组


class Solution {
public:
    int trap(int A[], int n) {
        int left = 0, right = n-1;
        int totalArea = 0, pillarArea = 0, height = 0;
        while(left <= right)
        {
            if(A[left] < A[right])
            {
                if(A[left] > height)
                {
                    totalArea += (A[left]-height)*(right - left + 1);
                    height = A[left];
                }
                pillarArea += A[left];
                left++;
            }
            else
            {
                if(A[right] > height)
                {
                    totalArea += (A[right]-height)*(right - left + 1);
                    height = A[right];
                }
                pillarArea += A[right];
                right--;
            }
        }
        return totalArea - pillarArea;
    }
};
 