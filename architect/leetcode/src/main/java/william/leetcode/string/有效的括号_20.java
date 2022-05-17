package william.leetcode.string;

import java.util.Stack;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/16 下午4:04
 * <p>
 * https://leetcode.cn/problems/valid-parentheses/
 */
public class 有效的括号_20 {
    //借助一个栈结构,利用其FILO特性
    //遍历字符串,如果遇到左括号,则直接入栈
    //如果遇到右括号,则需要判断栈顶元素是否为匹配的左括号
    //时间复杂度O(N)
    //空间复杂度O(N)
    public boolean isValid(String s) {
        //边界条件
        if (s == null || s.length() == 0) {
            return true;
        }

        //借助栈
        Stack<Character> stack = new Stack<>();

        //遍历字符串
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {  //遇到左括号,直接入栈
                stack.push(c);
                continue;
            }

            //遇到右括号,需要与当前栈顶元素进行匹配
            if (stack.isEmpty()) {   //栈为空,非法字符串
                return false;
            }

            //匹配括号
            Character top = stack.pop();
            if (c == ')' && top != '(') {
                return false;
            }
            if (c == ']' && top != '[') {
                return false;
            }
            if (c == '}' && top != '{') {
                return false;
            }
        }

        //最后判断栈是否为空
        return stack.isEmpty();
    }
}
