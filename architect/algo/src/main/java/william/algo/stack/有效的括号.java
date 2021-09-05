package william.algo.stack;

import java.util.Stack;

/**
 * @Author zhangshenao
 * @Date 2021-09-02
 * @Description https://leetcode-cn.com/problems/valid-parentheses/
 */
public class 有效的括号 {
    //判断一个字符串是否是有效的括号
    //假设字符串中只包含'('和')'
    //可以记录左括号的数量来代替入栈操作,降低空间复杂度
    //时间复杂度O(n) 空间复杂度O(1)
    public static boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> t = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '(' || c == '[') {
                t.push(c);
            } else if (c == '}') {
                if (t.empty() || t.peek() != '{') {
                    return false;
                }
                t.pop();
            } else if (c == ']') {
                if (t.empty() || t.peek() != '[') {
                    return false;
                }
                t.pop();
            } else if (c == ')') {
                if (t.empty() || t.peek() != '(') {
                    return false;
                }
                t.pop();
            } else {
                return false;
            }
        }

        return t.empty();


    }

    public static void main(String[] args) {
        String str1 = "()()()()";
        System.err.println(str1 + ": is valid: " + isValid(str1));

        String str2 = "()()()(";
        System.err.println(str2 + ": is valid: " + isValid(str2));

        String str3 = "((()))";
        System.err.println(str3 + ": is valid: " + isValid(str3));

        String str4 = "((())";
        System.err.println(str4 + ": is valid: " + isValid(str4));

        String str5 = "((()))(";
        System.err.println(str5 + ": is valid: " + isValid(str5));
    }
}
