Maximum Product Subarray 

Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

�����뵽���Ƕ�̬�滮����path[i][j] ��¼i��j �ĳ˻�����������и������ֵ�������£�

[java] view plaincopy��CODE�ϲ鿴����Ƭ�������ҵĴ���Ƭ
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

�ύ���ֳ��ֳ�ʱ������Ϊ���԰�������һ���ܳ������飬���淽����Ҫ���ٺܴ�����鲢��д����ȽϺ�ʱO(n^2)��

��ʵ����һ�¿��Է��֣�һ��ѭ����ʵ�Ϳ��Խ�����⣬��Ϊ�����г�����������������������Ҫ��¼��ĳ��λ��ʱ�����ֵ����Сֵ����Ϊ��Сֵ��������һ�����Ը����ͱ�����ֵ����

�������£�

[java] view plaincopy��CODE�ϲ鿴����Ƭ�������ҵĴ���Ƭ
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

��ʵ�����о���candy������Ϊȫ������������Ա���Ȼ��Ҫ�򵥺ܶ࣬
��������Ļ��˻��ľ���ֵ��һֱ����ģ�
����Ҫ�Ը��ľ��Ǹ����ĸ�����Ϊż��ʱ���Ժ��ԣ�Ϊ����ʱ������������ߵ�һ����������
�ĳ˻������������ұߵ�һ���������ҵĳ˻�
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