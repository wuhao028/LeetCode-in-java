

/**
 * Implement atoi to convert a string to an integer.
 * 
 * Hint: 
 * 
 * Carefully consider all possible input cases. If you want a challenge,
 * please do not see below and ask yourself what are the possible input cases.
 * 
 * Notes: 
 * 
 * It is intended for this problem to be specified vaguely (ie, no given
 * input specs). You are responsible to gather all the input requirements up front.
 * 
 * Requirements for atoi: The function first discards as many whitespace
 * characters as necessary until the first non-whitespace character is found.
 * Then, starting from this character, takes an optional initial plus or minus
 * sign followed by as many numerical digits as possible, and interprets them as
 * a numerical value.
 * 
 * The string can contain additional characters after those that form the
 * integral number, which are ignored and have no effect on the behavior of this
 * function.
 * 
 * If the first sequence of non-whitespace characters in str is not a valid
 * integral number, or if no such sequence exists because either str is empty or
 * it contains only whitespace characters, no conversion is performed.
 * 
 * If no valid conversion could be performed, a zero value is returned. If the
 * correct value is out of the range of representable values, INT_MAX
 * (2147483647) or INT_MIN (-2147483648) is returned.
 */

public class StringtoIntegerAtoi {
	public int atoi(String str) {
		str = str.trim();
		int length = str.length();
		if (length == 0)
			return 0;
		int i = 0;
		boolean minus = false;
		if (str.charAt(0) == '-') {
			minus = true;
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}
		long MIN_VALUE = Integer.MIN_VALUE;
		long MAX_VALUE = Integer.MAX_VALUE;
		long num = 0;
		boolean finished = false;
		for (; i < length && !finished; i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				num *= 10;
				num += c - '0';
			} else {
				finished = true;
			}
			if (minus && 0 - num < MIN_VALUE) {
				return Integer.MIN_VALUE;
			}
			if (!minus && num > MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
		}
		return minus ? new Long(0 - num).intValue() : new Long(num).intValue();
	}
}

1、对于非法输入，函数输出0（除了溢出）

2、处理输入NULL

3、处理数字前面的空格

4、处理数字前面的0

5、处理溢出

6、注意“+ 123”输入是非法的


public int atoi(String str) {
    if (str == null || str.length() < 1)
        return 0;
 
    // trim white spaces
    str = str.trim();
 
    char flag = '+';
 
    // check negative or positive
    int i = 0;
    if (str.charAt(0) == '-') {
        flag = '-';
        i++;
    } else if (str.charAt(0) == '+') {
        i++;
    }
    // use double to store result
    double result = 0;
 
    // calculate value
    while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
        result = result * 10 + (str.charAt(i) - '0');
        i++;
    }
 
    if (flag == '-')
        result = -result;
 
    // handle max and min
    if (result > Integer.MAX_VALUE)
        return Integer.MAX_VALUE;
 
    if (result < Integer.MIN_VALUE)
        return Integer.MIN_VALUE;
 
    return (int) result;
}