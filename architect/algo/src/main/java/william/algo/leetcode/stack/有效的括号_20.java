package william.algo.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author zhangshenao
 * @Date 2021-10-31
 * @Description 时间复杂度O(n) 空间复杂度O(n)
 */
public class 有效的括号_20 {
    public boolean isValid(String s) {
        //边界:空字符串认为合法
        if (s == null || s.length() == 0) {
            return true;
        }

        //边界:奇数长度的字符串一定非法
        if (s.length() % 2 == 1) {
            return false;
        }

        //使用Map维护括号映射关系
        Map<Character, Character> mapping = new HashMap<>(3);
        mapping.put(')', '(');
        mapping.put(']', '[');
        mapping.put('}', '{');

        //使用栈
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (mapping.containsKey(c)) {    //右括号需要去栈顶匹配左括号
                Character left = mapping.get(c);
                if (stack.isEmpty() || !left.equals(stack.peek())) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(c);  //左括号直接压栈
            }
        }

        //如果栈为空,说明匹配成功
        return stack.isEmpty();
    }
}
