

import java.util.HashMap;

/**
 * Given a string, find the length of the longest substring without repeating
 * characters. For example, the longest substring without repeating letters for
 * "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring
 * is "b", with the length of 1.
 */

public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
		if (s.length() == 0)
			return 0;
		int i = 0, j = 0;
		int result = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		while (j < s.length()) {
			Integer c = new Integer(s.charAt(j));
			if (!map.containsKey(c)) {
				map.put(c, j);
			} else {
				int length = j - i;
				if (result < length) {
					result = length;
				}
				Integer index = map.get(c);
				i = Math.max(i, index + 1);
				map.put(c, j);
			}
			j++;
		}

		if (result < j - i)
			return j - i;
		else
			return result;
	}
}

// my version 超时  时间复杂度为	O（n*n）
    public int lengthOfLongestSubstring(String s) {
        int n=s.length();
        int res=0;
        int max=0;
         for(int i=0;i<n;i++){
              HashSet<Character> hash=new HashSet<Character>();
             for(int j=i;j<n;j++){
             if(!hash.contains(s.charAt(j))){
                 hash.add(s.charAt(i));
                 res++;
             }
             
             if(hash.contains(s.charAt(i)))
             max=Math.max(max,res);
             
         }
         }
        return max;
    }
这道题用的方法是在LeetCode中很常用的方法，对于字符串的题目非常有用。 首先brute force的时间复杂度是O(n^3), 
对每个substring都看看是不是有重复的字符，找出其中最长的，复杂度非常高。优化一些的思路是稍微动态规划一下，每次定一个起点，
然后从起点走到有重复字符位置，过程用一个HashSet维护当前字符集，认为是constant操作，这样算法要进行两层循环，复杂度是O(n^2)。 
最后，我们介绍一种线性的算法，也是这类题目最常见的方法。基本思路是维护一个窗口，每次关注窗口中的字符串，在每次判断中，
左窗口和右窗口选择其一向前移动。同样是维护一个HashSet, 正常情况下移动右窗口，如果没有出现重复则继续移动右窗口，如果发现重复字符，
则说明当前窗口中的串已经不满足要求，继续移动有窗口不可能得到更好的结果，此时移动左窗口，直到不再有重复字符为止，中间跳过的这些串中不会有更好的结果，
因为他们不是重复就是更短。因为左窗口和右窗口都只向前，所以两个窗口都对每个元素访问不超过一遍，因此时间复杂度为O(2*n)=O(n),是线性算法。
空间复杂度为HashSet的size,也是O(n). 代码如下：
	时间复杂度为O（n）
	public int lengthOfLongestSubstring(String s) {
    if(s==null || s.length()==0)
        return 0;
    
    HashSet<Character> set = new HashSet<Character>();
    int max = 0;
    int walker = 0;
    int runner = 0;
    while(runner<s.length()){
        if(set.contains(s.charAt(runner))){
            if(max<runner-walker)
                max = runner-walker;
            
            while(s.charAt(walker)!=s.charAt(runner)){
                set.remove(s.charAt(walker));
                walker++;
            }
            walker++;
        }else
            set.add(s.charAt(runner));
            
        runner++;
    }
    max = Math.max(max,runner-walker);
    return max;
}

题：从一个字符串中找到一个连续子串，该子串中任何两个字符不能相同，求子串的最大长度并输出一条最长不重复子串。

本节从最直接的方法逐步优化，渐进探索了四种实现方式，并最终找到时间复杂度为O(N)，辅助空间为常数的方案，内容如下：

==基本算法 使用Hash==

==DP方案==

==DP + Hash 方案==

==DP + Hash 优化方案==

==================================

基本算法 使用Hash

要求子串中的字符不能重复，判重问题首先想到的就是hash，寻找满足要求的子串，最直接的方法就是遍历每个字符起始的子串，辅助hash，寻求最长的不重复子串，由于要遍历每个子串故复杂度为O(n^2)，n为字符串的长度，辅助的空间为常数hash[256]。代码如下：


/* 最长不重复子串 设串不超过30
 * 我们记为 LNRS
 */
int maxlen;
int maxindex;
void output(char * arr);
 
/* LNRS 基本算法 hash */
char visit[256];
 
void LNRS_hash(char * arr, int size)
{
    int i, j;
    for(i = 0; i < size; ++i)
    {
        memset(visit,0,sizeof visit);
        visit[arr[i]] = 1;
        for(j = i+1; j < size; ++j)
        {
            if(visit[arr[j]] == 0)
            {
                visit[arr[j]] = 1;
            }else
            {
                if(j-i > maxlen)
                {
                    maxlen = j - i;
                    maxindex = i;
                }
                break;
            }
        }
        if((j == size) && (j-i > maxlen))
        {
            maxlen = j - i;
            maxindex = i;
        }
    }
    output(arr);
}
==================================

DP方案

前面刚刚讨论过最长递增子序列的问题，咋一想就觉得二者有点类似，何不向DP方面想一下，为什么说二者类似，在LIS问题中，对于当前的元素，要么是与前面的LIS构成新的最长递增子序列，要么就是与前面稍短的子序列构成新的子序列或单独构成新子序列；

同理，对于最长不重复子串，某个当前的字符，如果它与前面的最长不重复子串中的字符没有重复，那么就可以以它为结尾构成新的最长子串；如果有重复，且重复位置在上一个最长子串起始位置之后，那么就与该起始位置之后的稍短的子串构成新的子串或者单独成一个新子串。

举个例子：例如字符串“abcdeab”，第二个字符a之前的最长不重复子串是“abcde”，a与最长子串中的字符有重复，但是它与稍短的“bcde”串没有重复，于是它可以与其构成一个新的子串，之前的最长重复子串“abcde”结束；

再看一个例子：字符串“abcb”，跟前面类似，最长串“abc”结束，第二个字符b与稍短的串“c”构成新的串；

这两个例子，可以看出些眉目：当一个最长子串结束时（即遇到重复的字符），新的子串的长度是与第一个重复的字符的下标有关的，如果该下标在上一个最长子串起始位置之前，则dp[i] = dp[i-1] + 1，即上一个最长子串的起始位置也是当前最长子串的起始位置；如果该下标在上一个最长子串起始位置之后，则新的子串是从该下标之后开始的。

于是类似LIS，对于每个当前的元素，我们“回头”去查询是否有与之重复的，如没有，则最长不重复子串长度+1，如有，则考察上一个子串起始位置与重复字符下标的关系，当然，如果DP使用O(n^2)的方案，则我们只需在内层循环遍历到上一个最长子串的起始位置即可，如下。

O(N^2)的DP方案，我们可以与LIS的DP方案进行对比，是一个道理的。代码如下：

/* LNRS dp */
int dp[30];
 
void LNRS_dp(char * arr, int size)
{
    int i, j;
    int last_start = 0;     // 上一次最长子串的起始位置
    maxlen = maxindex = 0;
 
    dp[0] = 1;
    for(i = 1; i < size; ++i)
    {
        for(j = i-1; j >= last_start; --j) // 遍历到上一次最长子串起始位置
        {
            if(arr[j] == arr[i])
            {
                dp[i] = i - j;
                last_start = j+1; // 更新last_start
                break;
            }else if(j == last_start) // 无重复
            {
                dp[i] = dp[i-1] + 1;
            }
        }
        if(dp[i] > maxlen)
        {
            maxlen = dp[i];
            maxindex = i + 1 - maxlen;
        }
    }
    output(arr);
}
==================================

DP + Hash 方案

上面的DP方案是O(n^2)的，之所以是n^2，是因为“回头”去寻找重复元素的位置了，受启发于最初的Hash思路，我们可以用hash记录元素是否出现过，我们当然也可以用hash记录元素出现过的下标，既然这样，在DP方案中，我们何不hash记录重复元素的位置，这样就不必“回头”了，而时间复杂度必然降为O(N)，只不过需要一个辅助的常数空间visit[256]，典型的空间换时间。

代码如下：这样遍历一遍便可以找到最长不重复子串

/* LNRS dp + hash 记录下标 */
void LNRS_dp_hash(char * arr, int size)
{
    memset(visit, -1, sizeof visit);
    memset(dp, 0, sizeof dp);
    maxlen = maxindex = 0;
    dp[0] = 1;
    visit[arr[0]] = 0;
    int last_start = 0;
 
    for(int i = 1; i < size; ++i)
    {
        if(visit[arr[i]] == -1)
        {
            dp[i] = dp[i-1] + 1;
            visit[arr[i]] = i; /* 记录字符下标 */
        }else
        {
            if(last_start <= visit[arr[i]])
            {
                dp[i] = i - visit[arr[i]];
                last_start = visit[arr[i]] + 1;
                visit[arr[i]] = i; /* 更新最近重复位置 */
            }else
            {
                dp[i] = dp[i-1] + 1;
                visit[arr[i]] = i; /* 更新最近重复位置 */
            }
 
        }
        if(dp[i] > maxlen)
        {
            maxlen = dp[i];
            maxindex = i + 1 - maxlen;
        }
    }
    output(arr);
}
==================================

DP + Hash 优化方案

写到这里，还是有些别扭，因为辅助的空间多了，是不是还能优化，仔细看DP最优子问题解的更新方程：

1
dp[i] = dp[i-1] + 1;
dp[i-1]不就是更新dp[i]当前的最优解么？这与最大子数组和问题的优化几乎同出一辙，我们不需要O(n)的辅助空间去存储子问题的最优解，而只需O(1)的空间就可以了，至此，我们找到了时间复杂度O(N)，辅助空间为O(1)（一个额外变量与256大小的散列表）的算法，代码如下：

注意：当前最长子串的构成是与上一次最长子串相关的，故要记录上一次最长子串的起始位置！


/* LNRS dp + hash 优化 */
void LNRS_dp_hash_impro(char * arr, int size)
{
    memset(visit, -1, sizeof visit);
    maxlen = maxindex = 0;
    visit[arr[0]] = 0;
    int curlen = 1;
    int last_start = 0;
 
    for(int i = 1; i < size; ++i)
    {
        if(visit[arr[i]] == -1)
        {
            ++curlen;
            visit[arr[i]] = i; /* 记录字符下标 */
        }else
        {
            if(last_start <= visit[arr[i]])
            {
                curlen = i - visit[arr[i]];
                last_start = visit[arr[i]] + 1;
                visit[arr[i]] = i; /* 更新最近重复位置 */
            }else
            {
                ++curlen;
                visit[arr[i]] = i; /* 更新最近重复位置 */
            }
        }
        if(curlen > maxlen)
        {
            maxlen = curlen;
            maxindex = i + 1 - maxlen;
        }
    }
    output(arr);
}
最后给出输出函数与测试用例：


/* 输出LNRS */
void output(char * arr)
{
    if(maxlen == 0)
    {
        printf("NO LNRS\n");
    }
    printf("The len of LNRS is %d\n",maxlen);
 
    int i = maxindex;
    while(maxlen--)
    {
        printf("%c",arr[i++]);
    }
    printf("\n");
}
 
void main()
{
     char arr[] = "abcaacdeabacdefg";
 
     /* LNRS 基本算法 */
     LNRS_hash(arr,strlen(arr));
 
     /* LNRS dp */
     LNRS_dp(arr,strlen(arr));
 
     /* LNRS dp + hash 记录下标 */
     LNRS_dp_hash(arr,strlen(arr));
 
     /* LNRS dp + hash 优化方案 */
     LNRS_dp_hash_impro(arr,strlen(arr));
}