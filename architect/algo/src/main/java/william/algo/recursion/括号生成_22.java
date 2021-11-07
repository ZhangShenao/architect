package william.algo.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangshenao
 * @Date 2021-11-07
 * @Description https://leetcode-cn.com/problems/generate-parentheses/?utm_source=LCUS&utm_medium=ip_redirect
 * &utm_campaign=transfer2china
 * 递归：目标是生成2*n长度的字符串,每次拿出'('或者')'进行append，递归终止条件是括号用光了,或者字符串非法
 * 时间复杂度O(n^2)
 */
public class 括号生成_22 {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        String str = "";
        gen(result, str, n, n);
        return result;
    }

    /**
     * 采用左括号或者右括号生成字符串
     *
     * @param result 用于生成的结果集合
     * @param str 当前字符串
     * @param left 剩余可用的左括号数
     * @param right 剩余可以的右括号数
     */
    private void gen(List<String> result, String str, int left, int right) {
        //递归终止条件:左、右括号都用完了
        if (left == 0 && right == 0) {
            //直接保存生成的字符串,退出
            result.add(str);
            return;
        }

        //左括号只要还要剩余,就可以一直加
        if (left > 0) {
            gen(result, str + "(", left - 1, right);
        }

        //加右括号时,要判断右括号是否还有剩余,且字符串是否合法(剩余的右括号比左括号多)
        if (right > 0 && right > left) {
            gen(result, str + ")", left, right - 1);
        }

    }
}
