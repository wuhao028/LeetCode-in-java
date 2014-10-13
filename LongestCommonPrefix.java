

/**
 * Write a function to find the longest common prefix string amongst an array of
 * strings.
 */

public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		int index = 0;
		while (index < strs[0].length()) {
			char c = strs[0].charAt(index);
			for (int i = 1; i < strs.length; ++i) {
				if (index >= strs[i].length() || strs[i].charAt(index) != c) {
					return strs[0].substring(0, index);
				}
			}
			index++;
		}
		return strs[0];
	}
}

//

private int minlen(String[] strs) {
        int min = Integer.MAX_VALUE;
        for(int i=0; i<strs.length;i++)
            min = Math.min(min,strs[i].length());
        return min;
    }
    
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        
        StringBuilder res = new StringBuilder();
        int index = 0;
        int len = minlen(strs);
        while (index < len){
            for (int i=1; i<strs.length;i++){
                if (strs[i].charAt(index) != strs[0].charAt(index))
                    return res.toString();
            }
            res.append(strs[0].charAt(index));
            index++;
        }
        return res.toString();
    }