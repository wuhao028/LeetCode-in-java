

import java.util.HashSet;

/**
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 * 
 * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive elements
 * sequence is [1, 2, 3, 4]. Return its length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 */

public class LongestConsecutiveSequence {
	public int longestConsecutive(int[] num) {
		int max = 0;
		HashSet<Integer> h = new HashSet<Integer>();
		for (int n : num) {
			h.add(n);
		}
		for (int n : num) {
			max = Math.max(max, getCount(h, n, false)
					+ getCount(h, n + 1, true));
		}
		return max;
	}

	public int getCount(HashSet<Integer> h, int value, boolean asc) {
		int count = 0;
		while (h.contains(value)) {
			count++;
			h.remove(value);
			if (asc)
				value++;
			else
				value--;
		}
		return count;
	}
}

    public int longestConsecutive(int[] num) {
       if(num.length==0 || num==null)return 0;
       HashSet<Integer> hash=new HashSet<Integer>();
       int n=num.length;
       for(int i=0;i<n;i++){
           hash.add(num[i]);
       }
        int max=0;
        for(int i=0;i<n;i++){
           int count=0;
            if(hash.contains(num[i])){
                hash.remove(num[i]);
                count=1;;
            
            
            int low=num[i]-1;
            
            while(hash.contains(low)){
                //注意先remove，再自减
                hash.remove(low);
                low=low-1;
                ++count;
            }
            
            int high=num[i]+1;
            
            while(hash.contains(high)){
               
                hash.remove(high);
                 high=high+1;
                ++count;
            }
            }
            
            max=Math.max(max,count);
            
        }
        
        
        return max;
    }
	
	这道题利用HashSet的唯一性解决，能使时间复杂度达到O(n)。首先先把所有num值放入HashSet，
	然后遍历整个数组，如果HashSet中存在该值，就先向下找到边界，找的同时把找到的值一个一个
	从set中删去，然后再向上找边界，同样要把找到的值都从set中删掉。所以每个元素最多会被遍历两边，
	时间复杂度为O(n)。
	
	
	public int longestConsecutive(int[] num) {  
        if(num == null||num.length == 0)
            return 0;
        
        HashSet<Integer> hs = new HashSet<Integer>();  
        
        for (int i = 0 ;i<num.length; i++)   
            hs.add(num[i]);  
         
        int max = 0;  
        for(int i=0; i<num.length; i++){  
            if(hs.contains(num[i])){
                int count = 1;  
                hs.remove(num[i]);
                
                int low = num[i] - 1; 
                while(hs.contains(low)){  
                    hs.remove(low);  
                    low--;  
                    count++;  
                }
                
                int high = num[i] + 1;  
                while(hs.contains(high)){  
                    hs.remove(high);  
                    high++;  
                    count++;  
                }  
                max = Math.max(max, count);  
            }  
        }  
        return max;  
    }
	
	算法1：首先想到的是排序，排序后遍历一遍就可以找出最长连续序列的长度，只是要稍微注意下判断连续序列的过程中有可能两个元素相同，比如1 2 2 3，排序复杂度n*log(n)，虽然题目要求O(n)复杂度，但是这个解法也可以通过OJ，代码如下：

class Solution {
public:
    int longestConsecutive(vector<int> &num) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int res = 1, len = num.size();
        if(len == 0)return 0;
        sort(num.begin(), num.end());
        int curr = 1;
        for(int i = 1; i < len; i++)
        {
            if(num[i] - num[i-1] == 1)
            {
                curr++;
                if(curr > res)res = curr;
            }
            else if(num[i] - num[i-1] == 0);
            else 
                curr = 1;
        }
        return res;
    }
};
算法2：想要O(n)的算法，我们只有以时间换空间，先把数组中所有元素映射到哈希表。然后以题目给出的数组为例：对于100，先向下查找99没找到，然后向上查找101也没找到，那么连续长度是1，从哈希表中删除100；然后是4，向下查找找到3,2,1，向上没有找到5，那么连续长度是4，从哈希表中删除4,3,2,1。这样对哈希表中已存在的某个元素向上和向下查找，直到哈希表为空。算法相当于遍历了一遍数组，然后再遍历了一遍哈希表，复杂的为O(n)。代码如下：                            本文地址

class Solution {
public:
    int longestConsecutive(vector<int> &num) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int res = 1, len = num.size();
        if(len == 0)return 0;
        unordered_set<int> hashtable;
        for(int i = 0; i < len; i++)
            hashtable.insert(num[i]);
        while(hashtable.empty() == false)
        {
            int currlen = 1;
            int curr = *(hashtable.begin());
            hashtable.erase(curr);
            int tmp = curr-1;
            while(hashtable.empty()==false && 
                hashtable.find(tmp) != hashtable.end())
            {
                hashtable.erase(tmp);
                currlen++;
                tmp--;
            }
            tmp = curr+1;
            while(hashtable.empty()==false && 
                hashtable.find(tmp) != hashtable.end())
            {
                hashtable.erase(tmp);
                currlen++;
                tmp++;
            }
            if(res < currlen)res = currlen;
        }
        return res;
    }
};