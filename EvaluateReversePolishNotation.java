

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * 
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * 
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */

import java.util.Stack;

public class EvaluateReversePolishNotation {
	public int evalRPN(String[] tokens) {
		Stack<Integer> st = new Stack<Integer>();
		int size = tokens.length;
		for (int i = 0; i < size; i++) {
			if (isDigital(tokens[i])) {
				st.push(Integer.parseInt(tokens[i]));
			} else {
				st.push(calc(st.pop(), st.pop(), tokens[i]));
			}
		}
		return st.pop();
	}

	private Integer calc(Integer num2, Integer num1, String op) {
		switch (op.charAt(0)) {
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		case '*':
			return num1 * num2;
		case '/':
			return num1 / num2;
		default:
			return 0;
		}
	}

	private boolean isDigital(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}




/*
题解：
这道题，其实上过课的应该都知道，忘记是哪门课讲得了。。知道原理的话就比较容易写出来。下面引用一下什么是Reverse Polish Notation：
“标准的表达式如"A+B"，在数学上学名
叫中缀表达式（Infix Notation），原因是运算符号在两个运算对象的中间。相对应的还有前缀表达式（Prefix 
Notation）,如："+ - A * B C D"，转换成中缀表达式为："A - B * C + D"；后缀表达式（Postfix 
Notation），比如前所述的中缀表达式转换为后缀表达式为："A B C * - D +"。为了纪念波兰数学家鲁卡谢维奇（Jan 
Lukasiewicz），前缀表达式被称作波兰表达式，后缀表达式称为逆波兰表达式（Reverse Polish Notation）。
后缀表达式的优点是显而易见的，编译器在处理时候按照从左至右的顺序读取逆波兰表达式，遇到运算对象直接压入堆栈，遇到运算符就从堆栈提取后进的两个对象进行计算，这个过程正好符合了计算机计算的原理。
后缀表达式比前缀表达式更加易于转换，并且它的最左面一定为数字，这一点在实际编程的时候就会体会到它的好处了。
逆波兰表达式有一个更大的优点，就是拆括号，根据运算符的级别将中缀表达式转换成逆波兰表达式后，运算顺序就已经替代了运算符的级别，这样也避免了括号提高运算级别的特殊处理。
事
实上，人的思维方式很容易固定~~！正如习惯拉10进制
*/



public static int evalRPN(String[] tokens) {
        if(tokens==null||tokens.length==0)
            return 0;
        int ans = 0;
        Stack<Integer> res = new Stack<Integer>();
        for(int i = 0; i<tokens.length;i++){
            ans = 0;
            if(tokens[i].equals("/") || tokens[i].equals("*") || tokens[i].equals("+") || tokens[i].equals("-")){
                int b = res.pop();
                int a = res.pop();
                if(tokens[i].equals("/"))
                    ans += a/b;
                else if(tokens[i].equals("+"))
                    ans += a+b;
                else if(tokens[i].equals("-"))
                    ans += a-b;
                else if(tokens[i].equals("*"))
                    ans += a*b;
                res.push(ans);
            }else{
			// 从String变为int的函数
                res.push(Integer.parseInt(tokens[i]));
            }
        }
        return res.pop();
    }
	
	
	
	
	

	