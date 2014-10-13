

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an array of strings, return all groups of strings that are anagrams.
 * 
 * Note: All inputs will be in lower-case.
 For example:

Input: ["tea","and","ate","eat","den"]

Output: ["tea","ate","eat"]
 */

public class Anagrams {
	public ArrayList<String> anagrams(String[] strs) {
		ArrayList<String> ret = new ArrayList<String>();
		ArrayList<String> ar = new ArrayList<String>();
		for (String s : strs) {
			char[] c = s.toCharArray();
			Arrays.sort(c);
			ar.add(new String(c));
		}
		int[] list = new int[strs.length];
		int tmp = 0;
		for (int i = 0; i < ar.size(); i++) {
			if (list[i] == 0) {
				list[i] = 1;
				tmp = 1;
				for (int j = i + 1; j < ar.size(); j++) {
					if (list[j] == 0 && ar.get(i).equals(ar.get(j))) {
						list[j] = 1;
						tmp++;
					}
				}
				if (tmp == 1) {
					list[i] = 0;
				}
			}
		}
		for (int i = 0; i < list.length; i++) {
			if (list[i] == 1)
				ret.add(strs[i]);
		}
		return ret;
	}
}


这道题看所给的字符串数组里面有多少个是同一个变形词变的。这道题同样使用HashMap来帮助存老值和新值，以及帮忙判断是否是变形词。
首先对每个string转换成char array然后排下序，HashMap里面的key存sort后的词，value存原始的词。然后如果这个排好序的词没在HashMap中出现过，
那么就把这个sorted word和unsortedword put进HashMap里面。如果一个sorted word是在HashMap里面存在过的，说明这个词肯定是个变形词，
除了把这个词加入到返回结果中，还需要把之前第一个存进HashMap里面的value存入result中。

 
public ArrayList<String> anagrams(String[] strs) {
     ArrayList<String> result=new ArrayList<String>();
     
     if (strs==null||strs.length==0)
         return result;
     
     HashMap<String,ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
     
     for (String s:strs){
         char[] temp=s.toCharArray();
         Arrays.sort(temp);
         String tempStr=new String(temp);
         
         if (hm.containsKey(tempStr)){
             if(hm.get(tempStr).size() == 1)
                result.add(hm.get(tempStr).get(0));
             hm.get(tempStr).add(s);
             result.add(s);
         }else{
             ArrayList<String> tempList=new ArrayList<String>();
             tempList.add(s);
             hm.put(tempStr, tempList);
             }
        }
        return result;
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 