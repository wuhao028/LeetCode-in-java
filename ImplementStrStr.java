

/**
 * Implement strStr().
 * 
 * Returns a pointer to the first occurrence of needle in haystack, or null if
 * needle is not part of haystack.
 */

public class ImplementStrStr {
	public String strStr(String haystack, String needle) {
		int i = 0;
		int len1 = haystack.length();
		int len2 = needle.length();
		if (len1 == 0 && len2 == 0)
			return "";
		for (; i < len1; i++) {
			int a = i;
			int b = 0;
			boolean match = true;
			while (b < len2) {
				if (a >= len1 || haystack.charAt(a) != needle.charAt(b)) {
					match = false;
					break;
				} else {
					a++;
					b++;
				}
			}
			if (match)
				return haystack.substring(i);
		}
		return null;
	}
}

// my version   也可以采用KMP算法 rolling hash?
    public String strStr(String haystack, String needle) {
        int m=haystack.length();
        int n=needle.length();
//needle为空时返回haystack
         if(needle==null || needle.length()==0)
         return haystack;
         
         for(int i=0;i<m;i++){
             if (haystack.length() - i < needle.length())
              return null;
              
             int t=0;
             int q=i;
             
             while(haystack.charAt(q)==needle.charAt(t) && t<n && q<m)
              {
                  q++;
                  t++;
              
              if(t==n)
              return haystack.substring(i);
              
              }
         }
        return null;
        
    }

	
	
	public String strStr(String haystack, String needle) {
    if (needle.length() == 0)
        return haystack;
 
    for (int i = 0; i < haystack.length(); i++) {
        if (haystack.length() - i + 1 < needle.length())
            return null;
 
        int k = i;
        int j = 0;
 
        while (j < needle.length() && k < haystack.length() && needle.charAt(j) == haystack.charAt(k)) {
            j++;
            k++;
            if (j == needle.length())
                return haystack.substring(i);
        }
 
    }
    return null;
}