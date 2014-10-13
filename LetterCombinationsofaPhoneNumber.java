

import java.util.ArrayList;

/**
 * Given a digit string, return all possible letter combinations that the number
 * could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * 
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */

public class LetterCombinationsofaPhoneNumber {
	private char[][] map = new char[][] { { 'a', 'b', 'c' }, { 'd', 'e', 'f' },
			{ 'g', 'h', 'i' }, { 'j', 'k', 'l' }, { 'm', 'n', 'o' },
			{ 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };

	public ArrayList<String> letterCombinations(String digits) {
		ArrayList<String> ret = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		letterCombinations(digits, 0, sb, ret);
		return ret;
	}

	private void letterCombinations(String digits, int i, StringBuilder sb,
			ArrayList<String> ret) {
		if (i >= digits.length()) {
			ret.add(sb.toString());
		} else {
			int index = digits.charAt(i) - '1' - 1;
			int size = map[index].length;
			for (int j = 0; j < size; j++) {
				sb.append(map[index][j]);
				letterCombinations(digits, i + 1, sb, ret);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
}


public ArrayList<String> letterCombinations(String digits) {
      ArrayList<String> result=new ArrayList<String>();
      if (digits==null)
          return result;

      String[] keyboard={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
      StringBuilder current=new StringBuilder();
      
      int index=0;
      buildResult(digits, index, current, keyboard, result);
      return result;
  }
  
  private void buildResult(String digits, int index, StringBuilder current, String[] keyboard, ArrayList<String> result){
      if (index==digits.length()){
	  //stringbuilder 也要转换为String
        result.add(current.toString());
        return;
        }
        
      int num=digits.charAt(index)-'0';//get integer number
      for (int i=0; i<keyboard[num].length(); i++){
        current.append(keyboard[num].charAt(i));
        buildResult(digits, index+1, current, keyboard, result);
        current.deleteCharAt(current.length()-1);
        }
    }

dfs递归解法

class Solution {
public:
    vector<string> letterCombinations(string digits) {
        vector<string> res;
        string tmpres(digits.size(), ' ');
        dfs(digits, 0, tmpres, res);
        return res;
    }
     
    void dfs(const string &digits, int index, string &tmpres, vector<string>&res)
    {
        string numap[] = {" ","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        if(index == digits.size())
        {
            res.push_back(tmpres);
            return;
        }
        for(int i = 0; i < numap[digits[index] - '0'].size(); i++)
        {
            tmpres[index] = numap[digits[index] - '0'][i];
            dfs(digits, index+1, tmpres, res);
        }
    }
};
 

 

根据编程之美-3.2电话号码对应英语单词 中的代码3-4，我们有如下的非递归解法，其实是把上述递归改为非递归

复制代码
 class Solution {
  public:
      vector<string> letterCombinations(string digits) {
          vector<string>res;
          vector<int> ansIndex(digits.size(), 0);
          string numap[] = {" ","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
          
          while(true)
          {
            string tmp(digits.size(),' ');
			for(int i = 0; i < digits.size(); i++)
                 tmp[i] = numap[digits[i]-'0'][ansIndex[i]];
             res.push_back(tmp);
             int k = digits.size() - 1;
             while(k >= 0)
             {
                 if(ansIndex[k] < numap[digits[k]-'0'].size() - 1)
                 {
                     ansIndex[k]++;
                     break;
                 }
                 else 
                 {
                     ansIndex[k] = 0;
                     k--;
                 }
             }
             if(k < 0)break;
         }
         
         return res;
     }
 };
bfs非递归解法（类似于求集合子集的算法2）

class Solution {
public:
    vector<string> letterCombinations(string digits) {
        vector<string> res(1,"");
        string numap[] = {" ","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        for(int i = 0; i < digits.size(); i++)
        {
            vector<string>tmp;
            for(int j = 0; j < res.size(); j++)
                for(int k = 0; k < numap[digits[i] - '0'].size(); k++)
                    tmp.push_back(res[j] + numap[digits[i] - '0'][k]);
            res = tmp;
        }
         
        return res;
    }
};








