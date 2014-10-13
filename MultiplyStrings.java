

Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.


http://www.cnblogs.com/TenosDoIt/p/3735309.html




public class MultiplyStrings {
	public String multiply(String num1, String num2) {
		int length1 = num1.length();
		int length2 = num2.length();
		int[] m = new int[length1 + length2];
		for (int k = length2 - 1, offset2 = 0; k >= 0; k--, offset2++) {
			for (int i = length1 - 1, offset1 = 0; i >= 0; i--, offset1++) {
				m[length1 + length2 - 1 - offset1 - offset2] += (num1.charAt(i) - '0')
						* (num2.charAt(k) - '0');
			}
		}
		int add = 0;
		for (int t = length1 + length2 - 1; t >= 0; t--) {
			int value = m[t] + add;
			add = value / 10;
			m[t] = value % 10;
		}
		StringBuffer sb = new StringBuffer();
		int w = 0;
		for (; w < length1 + length2; w++) {
			if (m[w] != 0)
				break;
		}
		for (int e = w; e < length1 + length2; e++) {
			sb.append(m[e]);
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}
}

直接乘会溢出，所以每次都要两个single digit相乘，最大81，不会溢出。
比如385 * 97, 就是个位=5 * 7，十位=8 * 7 + 5 * 9 ，百位=3 * 7 + 8 * 9 …
可以每一位用一个Int表示，存在一个int[]里面。
这个数组最大长度是num1.len + num2.len，比如99 * 99，最大不会超过10000，所以4位就够了。
这种个位在后面的，不好做（10的0次方，可惜对应位的数组index不是0而是n-1），
所以干脆先把string reverse了代码就清晰好多。
最后结果前面的0要清掉。
    public String multiply(String num1, String num2) {
	
	//由于各位在后面，方便计算先反转
           num1 = new StringBuilder(num1).reverse().toString();
           num2 = new StringBuilder(num2).reverse().toString();
      // even 99 * 99 is < 10000, so maximaly 4 digits
      int[] d = new int[num1.length() + num2.length()];
      for (int i = 0; i < num1.length(); i++) {
          int a = num1.charAt(i) - '0';
          for (int j = 0; j < num2.length(); j++) {
              int b = num2.charAt(j) - '0';
              d[i + j] += a * b;
         }
         }
     StringBuilder sb = new StringBuilder();
     for (int i = 0; i < d.length; i++) {
         int digit = d[i] % 10;
         int carry = d[i] / 10;
		 // 由于前面有反转，所以这里是插在0位置处
         sb.insert(0, digit);
         if (i < d.length - 1)
             d[i + 1] += carry;
         }
     //trim starting zeros
     while (sb.length() > 0 && sb.charAt(0) == '0') {
         sb.deleteCharAt(0);
     }
     return sb.length() == 0 ? "0" : sb.toString();
 }
 
 
    首先我们把每一位相乘，得到一个没有进位的临时结果，如图中中间的一行红色数字就是临时结果，然后把临时结果从低位起依次进位。对于一个m位整数乘以n位整数的结果，最多只有m+n位。         本文地址

注意：结果中需要去掉前导0，还需要注意结果为0的情况

class Solution {
public:
    string multiply(string num1, string num2) {
        int n1 = num1.size(), n2 = num2.size();
        vector<int> tmpres(n1+n2, 0);
        int k = n1 + n2 - 2;
        for(int i = 0; i < n1; i++)
            for(int j = 0; j < n2; j++)
                tmpres[k-i-j] += (num1[i]-'0')*(num2[j]-'0');
        int carryBit = 0;
        for(int i = 0; i < n1+n2; i++)//处理进位
        {
            tmpres[i] += carryBit;
            carryBit = tmpres[i] / 10;
            tmpres[i] %= 10;
        }
        int i = k+1;
        while(tmpres[i] == 0)i--;//去掉乘积的前导0
        if(i < 0)return "0"; //注意乘积为0的特殊情况
        string res;
        for(; i >= 0; i--)
            res.push_back(tmpres[i] + '0');
        return res;
    }
};
