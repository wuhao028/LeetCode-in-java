

/**
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 * 
 * Your algorithm should run in O(n) time and uses constant space.
 */

public class FirstMissingPositive {
	public int firstMissingPositive(int[] A) {
		if (A.length == 0)
			return 1;
			
			// 依次把能够安排在正确位置的数 安排在正确位置！！
		for (int i = 0; i < A.length; i++) {
			if (A[i] > 0 && A[i] - 1 < A.length && A[i] - 1 != i
					&& A[i] != A[A[i] - 1]) {
				int t = A[A[i] - 1];
				A[A[i] - 1] = A[i];
				A[i] = t;
				i--;
			}
		}

		for (int j = 0; j < A.length; j++) {
			if (A[j] - 1 != j) {
				return j + 1;
			}
		}
		return A.length + 1;
	}
}

// my version 用到了hashset 但是空间复杂度和int最大值相关，不满足为constant的要求
    public int firstMissingPositive(int[] A) {
        int max=Integer.MIN_VALUE;
        
        for(int t=0;t<A.length;t++){
            max=Math.max(max,A[t]);
        }
        
        HashSet<Integer> hash=new HashSet<Integer>();
        for(int i=0;i<A.length;i++){
            hash.add(A[i]);
        }
        
        
        for(int i=1;i<=A.length;i++){
            if(!hash.contains(i)){
              return i;
            }
             
        }
        
        return 1111;
    }

    public int firstMissingPositive(int[] A) {
        int max=Integer.MIN_VALUE;
        
        for(int t=0;t<A.length;t++){
            max=Math.max(max,A[t]);
        }
        
        HashSet<Integer> hash=new HashSet<Integer>();
        for(int i=0;i<A.length;i++){
            hash.add(A[i]);
        }
        
        
        for(int i=1;i<=A.length;i++){
            if(!hash.contains(i)){
              return i;
            }
             
        }
        
        return 1111;
    }

	
	算法1
首先最容易想到的是：先对数组排序，然后在查找，但是这样不满足线性时间要求。以下代码oj还是能通过的


class Solution {
public:
    int firstMissingPositive(int A[], int n) {
        sort(A, A+n);
        int k = 1;
        for(int i = 0; i < n; i++)
            if(A[i] < k);//为了处理小于1的数 或者 处理连续出现相同的数
            else if(A[i] != k)return k;
            else k++;
        return k;
    }
};
算法2
使用哈希表来记录某个数字是否出现过（当然也可以使用bitmap）。这样的话空间复杂度和int的最大值有关


class Solution {
public:
    int firstMissingPositive(int A[], int n) {
        unordered_set<int> uset;
        for(int i = 0; i < n; i++)
            if(A[i] > 0)uset.insert(A[i]);
        for(int i = 1; ;i++)
         if(uset.count(i) == 0)return i;
    }
};
算法3
注意到大小为n的数组，缺失的最小正整数一定在范围[1,n+1]内，因此改进一下算法2，可以使用大小为n+1的哈希表。空间复杂度是O（n），不符合题意

class Solution {
public:
    int firstMissingPositive(int A[], int n) {
        vector<int> hashtable(n+2, 0);//hashtable[i] = 1表示数字i出现过
        for(int i = 0; i < n; i++)
            if(A[i] > 0 && A[i] <= n+1)hashtable[A[i]] = 1;
        for(int i = 1; i <= n+1; i++)
            if(hashtable[i] == 0)return i;
    }
};
算法4

上述算法3中，我们可以用数组本身来充当哈希表。稍微变通一下，在遍历数组的过程中把数字 i 放在A[i-1]的位置。最后如果A[k] != k+1就说明k+1这个数字没有出现过。由于数组的大小是n，因此如果原始数组中的数字是1，2…n，则最后应该返回n+1。

还需要注意的是if中判断条件：A[i] != A[A[i]-1]；即如果某个位置A[i]已经放置了i+1或者数字A[i]即将要放入的位置(A[A[i]-1])原本就是A[i]，则跳过。这样可以避免出现死循环（如数组[1,2]和[1,1]）                          本文地址

class Solution {
public:
    int firstMissingPositive(int A[], int n) {
        for(int i = 0; i < n; )
            if(A[i] > 0 && A[i] <= n && A[i] != A[A[i]-1])
                swap(A[i], A[A[i]-1]);
            else i++;
        for(int i = 0; i < n; i++)
            if(A[i] != i+1)return i+1;
        return n+1;
    }
};
 