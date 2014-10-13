Maximum Product Subarray 

Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

首先想到就是动态规划利用path[i][j] 记录i到j 的乘积，计算过程中更新最大值代码如下：

[java] view plaincopy在CODE上查看代码片派生到我的代码片
public class Solution {  
        public int maxProduct(int[] A) {  
        int len = A.length;  
        int [][] path = new int[len][];  
        for(int i=0;i<len;i++){  
            path[i] = new int[len];  
        }  
        int MaxPro = Integer.MIN_VALUE;  
        path[0][0] = A[0];  
        for(int i=1;i<len;i++){  
            path[i][i] = A[i];  
            path[0][i] = path[0][i-1] * A[i];  
            if(A[i]>MaxPro){  
                MaxPro = A[i];  
            }  
            if(path[0][i]>MaxPro){  
                MaxPro = path[0][i];  
            }  
        }  
        for(int i=1;i<len;i++){  
            for(int j=i+1;j<len;j++){  
                path[i][j] = path[i][j-1] * A[j];  
                if(path[i][j]>MaxPro){  
                    MaxPro = path[i][j];  
                }  
            }  
        }  
        return MaxPro;  
    }  
}  

提交发现出现超时错误，因为测试案例中有一个很长的数组，上面方法需要开辟很大的数组并填写数组比较耗时O(n^2)。

其实分析一下可以发现，一次循环其实就可以解决问题，因为数组中出现正数负数，所以我们需要记录到某个位置时的最大值与最小值，因为最小值可能在下一步乘以负数就变成最大值啦。

代码如下：

[java] view plaincopy在CODE上查看代码片派生到我的代码片
public class Solution {  
        public int maxProduct(int[] A){  
            if(A.length < 1){  
            return 0;  
        }  
        int min_temp = A[0];  
        int max_temp = A[0];  
        int result = A[0];;  
        for(int i=1;i<A.length;i++){  
            int a = max_temp * A[i];  
            int b = min_temp * A[i];  
            int c = A[i];  
            max_temp = Math.max(Math.max(a, b), c);  
            min_temp = Math.min(Math.min(a, b), c);  
            result = max_temp>result? max_temp:result;  
        }  
        return result;  
    }  
}  

其实这道题感觉和candy很像，因为全部是整数，相对比自然数要简单很多，
不考虑零的话乘积的绝对值是一直扩大的，
所以要对付的就是负数的个数，为偶数时可以忽略，为奇数时考虑舍弃最左边第一个负数以左
的乘积，和舍弃最右边第一个负数以右的乘积
class Solution {
public:
    int maxProduct(int A[], int n) 
    {
        int max_prod=INT_MIN;
        int prod=1;
        
        for(int i=0;i<n;i++)
        {
            prod*=A[i];
            max_prod=max(max_prod,prod);
            if(A[i]==0)
            prod=1;
        }
        
        prod=1;
        for(int i=n-1;i>=0;i--)
        {
            prod*=A[i];
            max_prod=max(max_prod,prod);
            if(A[i]==0)
            prod=1;
        }
        
        return max_prod;
    }
};