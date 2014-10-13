

/**
 * Given an integer, convert it to a roman numeral.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 */

public class IntegertoRoman {
	public String intToRoman(int num) {
		String a[][] = {
				{ "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
				{ "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
				{ "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },
				{ "M", "MM", "MMM", "", "", "", "", "", "" } };
		String result = "";
		int key = 0;
		while (num != 0) {
			int d = num - num / 10 * 10;
			if (d != 0) {
				result = a[key][d - 1] + result;
			}
			num /= 10;
			key++;
		}
		return result;
	}
}

Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
题解：

这道题。。还有哪个roman to integer。。第一件事 就是先把roman认全吧。。

 

 罗马数字拼写规则（转自Wikipedia：http://zh.wikipedia.org/wiki/%E7%BD%97%E9%A9%AC%E6%95%B0%E5%AD%97）：

羅馬數字共有7個，即I（1）、V（5）、X（10）、L（50）、C（100）、D（500）和M（1000）。按照下述的規則可以表示任意正整數。需要注意的是罗马数字中没有“0”，與進位制無關。一般認為羅馬數字只用來記數，而不作演算。

重複數次：一個羅馬數字重複幾次，就表示這個數的幾倍。
右加左減：
在較大的羅馬數字的右邊記上較小的羅馬數字，表示大數字加小數字。
在較大的羅馬數字的左邊記上較小的羅馬數字，表示大數字减小數字。
左减的数字有限制，仅限于I、X、C。比如45不可以写成VL，只能是XLV
但是，左減時不可跨越一個位數。比如，99不可以用IC（100 - 1）表示，而是用XCIX（[100 - 10] + [10 - 1]）表示。（等同於阿拉伯數字每位數字分別表示。）
左減數字必須為一位，比如8寫成VIII，而非IIX。
右加數字不可連續超過三位，比如14寫成XIV，而非XIIII。（見下方“數碼限制”一項。）
加線乘千：
在羅馬數字的上方加上一條橫線或者加上下標的?，表示將這個數乘以1000，即是原數的1000倍。
同理，如果上方有兩條橫線，即是原數的1000000（1000^{2}）倍。
數碼限制：
同一數碼最多只能出現三次，如40不可表示為XXXX，而要表示為XL。
例外：由於IV是古羅馬神話主神朱庇特（即IVPITER，古羅馬字母裡沒有J和U）的首字，因此有時用IIII代替IV。
 

 

根据上述规则，我们可以发现两条对解这道题有帮助的内容：

左减的数字有限制，仅限于I、X、C。比如45不可以写成VL，只能是XLV
但是，左減時不可跨越一個位數。比如，99不可以用IC（100 - 1）表示，而是用XCIX（[100 - 10] + [10 - 1]）表示。（等同於阿拉伯數字每位數字分別表示。）
左減數字必須為一位，比如8寫成VIII，而非IIX。
有以上三个内容我们可以发现在减数的时候有特定的规律，就是只能减1位且不能跨越一个位数，同时只限于I X C。

像上面那个例子，99 不能用IC(100-1)表示，是因为如果一个数字超过90（或等于90），其罗马数字的表示就必须包含一个XC（100-10）。

同理，对I X C都适用这个原则。

这个判断，要从最大往下找，如果一个数字是5，那么他就是属于大于等于5，罗马数字包含V，而无需写成IIIII。

所以，递归的，代码可以写成如下（代码源自leetcode discussion）：

 

复制代码
 1     public String intToRoman(int num) {
 2         if(num>=1000) return "M"+intToRoman(num-1000);
 3         if(num>=900) return "CM"+intToRoman(num-900);
 4         if(num>=500) return "D"+intToRoman(num-500);
 5         if(num>=400) return "CD"+intToRoman(num-400);
 6         if(num>=100) return "C"+intToRoman(num-100);
 7         if(num>=90) return "XC"+intToRoman(num-90);
 8         if(num>=50) return "L"+intToRoman(num-50);
 9         if(num>=40) return "XL"+intToRoman(num-40);
10         if(num>=10) return "X"+intToRoman(num-10);
11         if(num>=9) return "IX"+intToRoman(num-9);
12         if(num>=5) return "V"+intToRoman(num-5);
13         if(num>=4) return "IV"+intToRoman(num-4);
14         if(num>=1) return "I"+intToRoman(num-1);
15         return "";
16     }

public String intToRoman(int num) {
        String str = "";    
        String [] symbol = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};    
        int [] value = {1000,900,500,400, 100, 90,  50, 40,  10, 9,   5,  4,   1};   
		
		// for循环里面有while循环
        for(int i=0;num!=0;i++){  
            while(num >= value[i]){  
                num -= value[i];  
                str += symbol[i];  
            }  
        }  
        return str;  
    }