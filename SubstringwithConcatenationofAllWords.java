

import java.util.ArrayList;
import java.util.HashMap;

/**
 * You are given a string, S, and a list of words, L, that are all of the same
 * length. Find all starting indices of substring(s) in S that is a
 * concatenation of each word in L exactly once and without any intervening
 * characters.
 * 
 * For example, given: S: "barfoothefoobarman" L: ["foo", "bar"]
 * 
 * You should return the indices: [0,9]. (order does not matter).
 */

public class SubstringwithConcatenationofAllWords {
	public ArrayList<Integer> findSubstring(String S, String[] L) {
		int slen = S.length();
		int m = L.length;
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (m == 0)
			return ret;
		int n = L[0].length();
		HashMap<String, Integer> smap = new HashMap<String, Integer>();
		for (String s : L) {
			if (smap.containsKey(s)) {
				smap.put(s, smap.get(s).intValue() + 1);
			} else {
				smap.put(s, 1);
			}
		}
		if (m * n > slen)
			return ret;
		int start = 0, end = m * n - 1;
		int index = 0;
		while (end < slen) {
			index = check(S, start, end, m, n, L, smap);
			if (index >= 0)
				ret.add(index);
			start++;
			end++;
		}
		return ret;
	}

	private int check(String s, int start, int end, int m, int n, String[] L,
			HashMap<String, Integer> smap) {
		HashMap<String, Integer> exitMap = new HashMap<String, Integer>();
		int sidx = start;
		while (sidx <= end) {
			String k = s.substring(sidx, sidx + n);
			if (smap.containsKey(k)) {
				if (exitMap.containsKey(k)) {
					Integer i = exitMap.get(k);
					if (i == smap.get(k))
						return -1;
					else {
						exitMap.put(k, i + 1);
					}
				} else {
					exitMap.put(k, 1);
				}
				sidx += n;
			} else {
				return -1;
			}
		}
		return start;
	}
}




这道题看似比较复杂，其实思路和Longest Substring Without Repeating Characters差不多。因为那些单词是定长的，
所以本质上和单一个字符一样。和Longest Substring Without Repeating Characters的区别只在于我们需要维护一个字典，
然后保证目前的串包含字典里面的单词有且仅有一次。思路仍然是维护一个窗口，如果当前单词在字典中，
则继续移动窗口右端，否则窗口左端可以跳到字符串下一个单词了。假设源字符串的长度为n，字典中单词的长度为l。
因为不是一个字符，所以我们需要对源字符串所有长度为l的子串进行判断。做法是i从0到l-1个字符开始，得到开始index分别为i
, i+l, i+2*l, ...的长度为l的单词。这样就可以保证判断到所有的满足条件的串。因为每次扫描的时间复杂度是O(2*n/l)
(每个单词不会被访问多于两次，一次是窗口右端，一次是窗口左端)，总共扫描l次（i=0, ..., l-1)，
所以总复杂度是O(2*n/l*l)=O(n)，是一个线性算法。空间复杂度是字典的大小，即O(m*l)，其中m是字典的单词数量。
代码如下：





public ArrayList<Integer> findSubstring(String S, String[] L) {
    // Note: The Solution object is instantiated only once and is reused by each test case.
    ArrayList<Integer> res = new ArrayList<Integer>();
    if(S==null || S.length()==0 || L==null || L.length==0)
        return res;
    HashMap<String,Integer> map = new HashMap<String,Integer>();
    for(int i=0;i<L.length;i++)
    {
        if(map.containsKey(L[i]))
        {
            map.put(L[i],map.get(L[i])+1);
        }
        else
        {
            map.put(L[i],1);
        }
    }
	
	//具体开始的位置
    for(int i=0;i<L[0].length();i++)
    {
        HashMap<String,Integer> curMap = new HashMap<String,Integer>();
        int count = 0;
        int left = i;
		// 开始计算是否相同
        for(int j=i;j<=S.length()-L[0].length();j+=L[0].length())
        {
            String str = S.substring(j,j+L[0].length());
            
            if(map.containsKey(str))
            {
                if(curMap.containsKey(str))
                    curMap.put(str,curMap.get(str)+1);
                else
                    curMap.put(str,1);
                if(curMap.get(str)<=map.get(str))
                    count++;
                else
                {
                    while(curMap.get(str)>map.get(str))
                    {
                        String temp = S.substring(left,left+L[0].length());
                        if(curMap.containsKey(temp))
                        {
                            curMap.put(temp,curMap.get(temp)-1);
                            if(curMap.get(temp)<map.get(temp))
                                count--;
                        }
                        left += L[0].length();
                    }
                }
                if(count == L.length)
                {
                    res.add(left);
                    //if(left<)
                    String temp = S.substring(left,left+L[0].length());
                    if(curMap.containsKey(temp))
                        curMap.put(temp,curMap.get(temp)-1);
                    count--;
                    left += L[0].length();
                }
            }
            else
            {
                curMap.clear();
                count = 0;
                left = j+L[0].length();
            }
        }
    }
    return res;
}



算法1

暴力解法，从字符串s的每个位置都判断一次（如果从当前位置开始的子串长度小于L中所有单词长度，不用判断），从当前位置开始的子串的前段部分能不能由集合L里面的单词拼接而成。

从某一个位置 i 判断时，依次判断单词s[i,i+2], s[i+3,i+5], s[i+6, i+8]…是否在集合中，如果单词在集合中，就从集合中删除该单词。

我们用一个hash map来保存单词，这样可以在O(1)时间内判断单词是否在集合中

算法的时间复杂度是O（n*(l*k)）n是字符串的长度，l是单词的个数，k是单词的长度

 

递归代码如下：


class Solution {
private:
    int wordLen;
     
public:
    vector<int> findSubstring(string S, vector<string> &L) {
        unordered_map<string, int>wordTimes;
        for(int i = 0; i < L.size(); i++)
            if(wordTimes.count(L[i]) == 0)
                wordTimes.insert(make_pair(L[i], 1));
            else wordTimes[L[i]]++;
        wordLen = L[0].size();
         
        vector<int> res;
        for(int i = 0; i <= (int)(S.size()-L.size()*wordLen); i++)
            if(helper(S, i, wordTimes, L.size()))
                res.push_back(i);
        return res;
    }
 
    //判断子串s[index...]的前段是否能由L中的单词组合而成
    bool helper(string &s, const int index, 
        unordered_map<string, int>&wordTimes, const int wordNum)
    {
        if(wordNum == 0)return true;
        string firstWord = s.substr(index, wordLen);
        unordered_map<string, int>::iterator ite = wordTimes.find(firstWord);
        if(ite != wordTimes.end() && ite->second > 0)
        {
            (ite->second)--;
            bool res = helper(s, index+wordLen, wordTimes, wordNum-1);
            (ite->second)++;//恢复hash map的状态
            return res;
        }
        else return false;
    }
};
 

非递归代码如下：

class Solution {
private:
    int wordLen;
     
public:
    vector<int> findSubstring(string S, vector<string> &L) {
        unordered_map<string, int>wordTimes;
        for(int i = 0; i < L.size(); i++)
            if(wordTimes.count(L[i]) == 0)
                wordTimes.insert(make_pair(L[i], 1));
            else wordTimes[L[i]]++;
        wordLen = L[0].size();
         
        vector<int> res;
        for(int i = 0; i <= (int)(S.size()-L.size()*wordLen); i++)
            if(helper(S, i, wordTimes, L.size()))
                res.push_back(i);
        return res;
    }
     
    //判断子串s[index...]的前段是否能由L中的单词组合而成
    bool helper(const string &s, int index, 
        unordered_map<string, int>wordTimes, int wordNum)
    {
        for(int i = index; wordNum != 0 && i <= (int)s.size()-wordLen; i+=wordLen)
        {
            string word = s.substr(i, wordLen);
            unordered_map<string, int>::iterator ite = wordTimes.find(word);
            if(ite != wordTimes.end() && ite->second > 0)
                {ite->second--; wordNum--;}
            else return false;
        }
        if(wordNum == 0)return true;
        else return false;
    }
};
 

OJ递归的时间小于非递归时间，因为非递归的helper函数中，hash map参数是传值的方式，每次调用都要拷贝一次hash map，递归代码中一直只存在一个hash map对象

算法2

回想前面的题目：LeetCode:Longest Substring Without Repeating Characters 和 LeetCode:Minimum Window Substring ，都用了一种滑动窗口的方法。这一题也可以利用相同的思想。

比如s = “a1b2c3a1d4”L={“a1”,“b2”,“c3”，“d4”}

窗口最开始为空，

a1在L中，加入窗口 【a1】b2c3a1d4                            本文地址

b2在L中，加入窗口 【a1b2】c3a1d4

c3在L中，加入窗口 【a1b2c3】a1d4

a1在L中了，但是前面a1已经算了一次，此时只需要把窗口向右移动一个单词a1【b2c3a1】d4

d4在L中，加入窗口a1【b2c3a1d4】找到了一个匹配

如果把s改为“a1b2c3kka1d4”，那么在第四步中会碰到单词kk，kk不在L中，此时窗口起始位置移动到kk后面a1b2c3kk【a1d4

class Solution {
public:
    vector<int> findSubstring(string S, vector<string> &L) {
        unordered_map<string, int>wordTimes;//L中单词出现的次数
        for(int i = 0; i < L.size(); i++)
            if(wordTimes.count(L[i]) == 0)
                wordTimes.insert(make_pair(L[i], 1));
            else wordTimes[L[i]]++;
        int wordLen = L[0].size();
         
        vector<int> res;
        for(int i = 0; i < wordLen; i++)
        {//为了不遗漏从s的每一个位置开始的子串，第一层循环为单词的长度
            unordered_map<string, int>wordTimes2;//当前窗口中单词出现的次数
            int winStart = i, cnt = 0;//winStart为窗口起始位置,cnt为当前窗口中的单词数目
            for(int winEnd = i; winEnd <= (int)S.size()-wordLen; winEnd+=wordLen)
            {//窗口为[winStart,winEnd)
                string word = S.substr(winEnd, wordLen);
                if(wordTimes.find(word) != wordTimes.end())
                {
                    if(wordTimes2.find(word) == wordTimes2.end())
                        wordTimes2[word] = 1;
                    else wordTimes2[word]++;
                     
                    if(wordTimes2[word] <= wordTimes[word])
                        cnt++;
                    else
                    {//当前的单词在L中，但是它已经在窗口中出现了相应的次数，不应该加入窗口
                     //此时，应该把窗口起始位置想左移动到，该单词第一次出现的位置的下一个单词位置
                        for(int k = winStart; ; k += wordLen)
                        {
                            string tmpstr = S.substr(k, wordLen);
                            wordTimes2[tmpstr]--;
                            if(tmpstr == word)
                            {
                                winStart = k + wordLen;
                                break;
                            }
                            cnt--;
                        }
                    }
                     
                    if(cnt == L.size())
                        res.push_back(winStart);
                }
                else
                {//发现不在L中的单词
                    winStart = winEnd + wordLen;
                    wordTimes2.clear();
                    cnt = 0;
                }
            }
        }
        return res;
    }
};
算法时间复杂度为O（n*k)）n是字符串的长度，k是单词的长度

 


