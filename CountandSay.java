

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 
 * 1 is read off as "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is read
 * off as "one 2, then one 1" or 1211.
 * 
 * Given an integer n, generate the nth sequence.
 * 
 * Note: The sequence of integers will be represented as a string.
 */

public class CountandSay {
	public String countAndSay(int n) {
		StringBuilder s1 = new StringBuilder("1");
		StringBuilder s2 = new StringBuilder();
		for (int i = 1; i < n; i++) {
			int j = 0;
			int len = s1.length();
			while (j < len) {
				int count = 1;
				char c = s1.charAt(j);
				while (j < len - 1 && s1.charAt(j + 1) == s1.charAt(j)) {
					count++;
					j++;
				}
				s2.append(count + "");
				s2.append(c);
				if (j == len - 1) {
					break;
				}
				j++;
			}
			s1 = s2;
			s2 = new StringBuilder();
		}
		return s1.toString();
	}
}



public String countAndSay(int n) {
    if(n<=0)
        return "";
    String curRes = "1";
    int start = 1;//从1开始算
    while(start < n){
        StringBuilder res = new StringBuilder();
        int count = 1;
        for(int j=1;j<curRes.length();j++){
            if(curRes.charAt(j)==curRes.charAt(j-1))
                count++;
            else{
                res.append(count);
                res.append(curRes.charAt(j-1));
                count = 1;
            }
        }
        res.append(count);
        res.append(curRes.charAt(curRes.length()-1));
        curRes = res.toString();
        start++;
    }
    return curRes;
}