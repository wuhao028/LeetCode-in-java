

/**
 * Given two binary strings, return their sum (also a binary string).
 * 
 * For example, a = "11" b = "1" Return "100".
 */

public class AddBinary {
	public String addBinary(String a, String b) {
		int i = a.length() - 1;
		int j = b.length() - 1;
		int da = 0;
		int db = 0;
		int adv = 0;
		StringBuffer result = new StringBuffer();
		while (i >= 0 && j >= 0) {
			da = a.charAt(i--) == '0' ? 0 : 1;
			db = b.charAt(j--) == '0' ? 0 : 1;
			int d = da + db + adv;
			result.append(d % 2 == 0 ? '0' : '1');
			adv = d >> 1;
		}
		if (i >= 0) {
			while (i >= 0) {
				da = a.charAt(i--) == '0' ? 0 : 1;
				int d = da + adv;
				result.append(d % 2 == 0 ? '0' : '1');
				adv = d >> 1;
			}
		} else if (j >= 0) {
			while (j >= 0) {
				db = b.charAt(j--) == '0' ? 0 : 1;
				int d = db + adv;
				result.append(d % 2 == 0 ? '0' : '1');
				adv = d >> 1;
			}
		}
		if (adv == 1) {
			result.append('1');
		}
		
		// 需要反转
		return result.reverse().toString();
	}
}








package Level2;

/**
 * Add Binary
 *
 * Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
 */
public class S67 {

	public static void main(String[] args) {
		String a = "1111";
		String b = "1111";
		System.out.println(addBinary(a, b));
	}
	
	public static String addBinary(String a, String b) {
        int i = a.length() - 1;		// i指向a的末尾
        int j = b.length() - 1;		// j指向b的末尾
        int c = 0;		// carry 进位
        // 先把String转为char数组便于处理
        char[] achar = a.toCharArray();
        char[] bchar = b.toCharArray();
        // 结果数组
        char[] reschar = new char[Math.max(achar.length, bchar.length)+2];
        int k = 0;		// k指向结果数组的开头
        
        while(true){
        	if(i<0 && j<0 && c==0){
        		break;
        	}
        	
        	int aint = 0;
        	int bint = 0;
        	
        	if(i >= 0){
        		aint = achar[i] - '0';
        	}
        	if(j >= 0){
        		bint = bchar[j] - '0';
        	}
        	if(aint + bint + c > 1){
        		reschar[k] = (char) ('0' + aint + bint + c - 2);
        		c = 1;
        	}else{
        		reschar[k] = (char) ('0' + aint + bint + c);
        		c = 0;
        	}
        	k++;
        	i--;
        	j--;
        }
         // char数组转string，然后翻转
        return new StringBuffer(new String(reschar, 0, k)).reverse().toString();
    }

}



















































