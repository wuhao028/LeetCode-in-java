

import java.util.ArrayList;

/**
 * Given a string containing only digits, restore it by returning all possible
 * valid IP address combinations.
 * 
 * For example: Given "25525511135",
 * 
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */

public class RestoreIPAddresses {
	public ArrayList<String> restoreIpAddresses(String s) {
		ArrayList<String> ips = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		restoreIpAddresses(s, 0, ips, sb, 0);
		return ips;
	}

	private void restoreIpAddresses(String s, int i, ArrayList<String> ips,
			StringBuffer ip, int step) {
		if ((step >= 4 && i < s.length()) || (step < 4 && i >= s.length()))
			return;
		if (step >= 4 && i >= s.length()) {
			ips.add(ip.substring(0, ip.length() - 1));
		} else {
			if (s.charAt(i) == '0') {
				ip.append('0');
				ip.append('.');
				restoreIpAddresses(s, i + 1, ips, ip, step + 1);
				ip.deleteCharAt(ip.length() - 1);
				ip.deleteCharAt(ip.length() - 1);
			} else {
				for (int j = 1; j <= 3 && i + j <= s.length(); j++) {
					StringBuffer nextSeg = new StringBuffer();
					nextSeg.append(s.substring(i, i + j));
					int n = Integer.parseInt(nextSeg.toString());
					if (n > 0 && n <= 255) {
						ip.append(nextSeg.toString());
						ip.append('.');
						restoreIpAddresses(s, i + j, ips, ip, step + 1);
						ip.deleteCharAt(ip.length() - 1);
						for (int k = 0; k < nextSeg.length(); k++)
							ip.deleteCharAt(ip.length() - 1);
					} else {
						break;
					}
				}
			}
		}
	}
}


利用循环递归解决子问题。对于每个段内数来说，最多3位最少1位，所以在每一层可以循环3次，来尝试填段。因为IP地址最多4个分段，当层数是3的时候说明已经尝试填过3个段了，那么把剩余没填的数段接到结尾即可。

这个过程中要保证的是填的数是合法的，最后拼接的剩余的数也是合法的。

 注意开头如果是0的话要特殊处理，如果开头是0，判断整个串是不是0，不是的话该字符就是非法的。因为001，01都是不对的。

代码如下：
public ArrayList<String> restoreIpAddresses(String s) {  
        ArrayList<String> res = new ArrayList<String>();  
        String item = new String();
        if (s.length()<4||s.length()>12) 
        return res;  
        
        dfs(s, 0, item, res);  
        return res;  
    }  
      
    public void dfs(String s, int start, String item, ArrayList<String> res){  
        if (start == 3 && isValid(s)) {  
            res.add(item + s);  
            return;  
        }  
        for(int i=0; i<3 && i<s.length()-1; i++){  
            String substr = s.substring(0,i+1);  
            if (isValid(substr))
			//substring 函数中取s.length说明最终是从第一个参数到第二个参数的前一个字符结束
                dfs(s.substring(i+1, s.length()), start+1, item + substr + '.', res);  
        }  
    }  
      
    public boolean isValid(String s){  
        if (s.charAt(0)=='0')
            return s.equals("0");  
            int num = Integer.parseInt(s);
            
        if(num <= 255 && num > 0)
            return true;
        else
            return false;
    }



// my version 不够简洁  而且出错  没能AC
public class Solution {
    public List<String> restoreIpAddresses(String s) {
        char[] array=s.toCharArray(s);
        char[] A=array;
        int n=A.length;
        
        List<String> result=new ArrayList<String>();
        String item=new String();
        helper(A,0,4,result,item);
        return result;
        
    }
    
        private void helper(int[] A,int start,int remain,List<String> result, String item){
            String str=new String(); 
            for(int i=start;i<n;i++){
                if(A[i]<256){
                    start++;
                    remain--;
                    
                    if(remain<0)
                    return;
                    
                     if(remain==0){
                    String str=A[i]+"";
                    item.add(str);
                    result.add(item);
                    }
                    
                    if(remain>0){
                    String str=A[i]+".";
                    item.add(str);
                    }
                    
                    helper(A,start,remain,result,item);
                    
                }
                
                if(A[i]*10+A[i+1]<256){
                    start=start+2;
                    remain--;
                    
                    if(remain<0)
                    return;
                    
                     if(remain==0){
                    String str=A[i]*10+A[i+1]+"";
                    item.add(str);
                    result.add(item);
                    }
                    
                    if(remain>0){
                    String str=A[i]*10+A[i+1]+".";
                    item.add(str);
                    }
                    
                   helper(A,start,remain,result,item);
                  }
                
                if(A[i]*100+A[i+1]*10+A[i+2]<256){
                    start=start+3;
                    remain--;
                    
                    if(remain<0)
                    return;
                    
                     if(remain==0){
                    String str=A[i]*100+A[i+1]*10+A[i+2]+"";
                    item.add(str);
                    result.add(item);
                    }
                    
                    if(remain>0){
                    String str=A[i]*100+A[i+1]*10+A[i+2]+".";
                    item.add(str);
                    }
                    
                    helper(A,start,remain,result,item);
                  }
                
                
                
            }
        }
}