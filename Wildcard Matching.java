Wildcard Matching Total Accepted: 14308 Total Submissions: 101243 My Submissions
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") �� false
isMatch("aa","aa") �� true
isMatch("aaa","aa") �� false
isMatch("aa", "*") �� true
isMatch("aa", "a*") �� true
isMatch("ab", "?*") �� true
isMatch("aab", "c*a*b") �� false

��ȷ��������̰��.

�����������

������ƥ��s��p

���ƥ���s++ , p++

�����ƥ��Ļ��Ϳ�p֮ǰ֪����*

��Ȼ�Ƿ���*������Ҫ��¼��,����*�ͼ�¼��ǰ*��λ�ú�ƥ�䵽��s��λ��

Ȼ���*����һλ��ƥ��,��ʼƥ��0���ַ�

���ok������,����ok,��ôƥ��1���ַ�...ͬ��2,3,4���ַ�(�е���ݵĸо���

����ʵ�����Ӷ���O(len(s) * len(p))

public class WildcardMatching {
	public boolean isMatch(String s, String p) {
		int i = 0;
		int j = 0;
		int star = -1;
		int mark = -1;
		while (i < s.length()) {
			if (j < p.length()
					&& (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
				++i;
				++j;
			} else if (j < p.length() && p.charAt(j) == '*') {
				star = j++;
				mark = i;
			} else if (star != -1) {
				j = star + 1;
				i = ++mark;
			} else {
				return false;
			}
		}
		// ���Ǳ߽����!!!
		while (j < p.length() && p.charAt(j) == '*') {
			++j;
		}
		return j == p.length();
	}
}





//my version û���ö�̬�滮  û��AC

    public boolean isMatch(String s, String p) {
        
        char[] schar=s.toCharArray();
        char[] pchar=p.toCharArray();
        int n=p.length();
        int m=s.length();
        int i=0;
        int j=0;
        while(i<m){
            if(p.charAt(i)!='?' && p.charAt(i)!='*'){
               if(p.charAt(i)==s.charAt(j)){
                   j++;
                   i++;
               }
               if(i<n && j<m && p.charAt(i)!=s.charAt(j))
                return false;
            
            }
        
            if(p.charAt(i)=='?'){
                i++;
                j++;
            }
            
            if(p.charAt(i)=='*'){
                i++;
                for(int k=j;j<m;j++){
                    return isMatch(s.substring(j,m),p.substring(i,n));
                }
            }
  
        }
        
        return true;

    }

	
	
	
	
	
	
	
	
	