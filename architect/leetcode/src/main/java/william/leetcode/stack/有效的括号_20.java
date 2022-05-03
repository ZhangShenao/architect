package william.leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class 有效的括号_20 {
    public boolean isValid(String s) {
        //边界条件
        if (s == null || s.length() <= 0) {
            return true;
        }

        //使用栈暂存左括号
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //如果是左括号,则直接入栈
            if (('(' == c) || ('[' == c) || ('{' == c)) {
                stack.push(c);
                continue;
            }

            //如果是右括号,则需要匹配栈顶的左括号
            if (stack.isEmpty()) {
                return false;
            }

            char b = stack.pop();
            if (!match(c, b)) {
                return false;
            }
        }

        //最后返回栈是否为空
        return stack.isEmpty();
    }

    private boolean match(char right, char left) {
        if (')' == right) {
            return ('(' == left);
        }
        if (']' == right) {
            return ('[' == left);
        }
        return ('{' == left);
    }
}
