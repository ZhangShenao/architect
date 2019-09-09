package william.leetcode;

import java.util.Stack;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/13 10:34
 * @Description:https://leetcode.com/problems/evaluate-reverse-polish-notation/
 */
public class Solution150 {
    public int evalRPN(String[] tokens) {
        //创建一个栈,保存所有整数
        Stack<Integer> nums = new Stack<>();

        //遍历数组
        for (String token : tokens) {
            //如果是运算符,则出栈两个操作数,计算结果,并将结果入栈
            if (isOperator(token)) {
                int num2 = nums.pop();
                int num1 = nums.pop();
                nums.push(calc(num1, num2, token));
            } else {
                nums.push(Integer.parseInt(token));
            }
        }
        return nums.pop();
    }

    private boolean isOperator(String token) {
        return ("+".equals(token)
                || "-".equals(token)
                || "*".equals(token)
                || "/".equals(token));
    }

    private int calc(int num1, int num2, String token) {
        if ("+".equals(token)) {
            return num1 + num2;
        }
        if ("-".equals(token)) {
            return num1 - num2;
        }
        if ("*".equals(token)) {
            return num1 * num2;
        }
        return num1 / num2;
    }
}
