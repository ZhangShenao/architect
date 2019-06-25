package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/25 10:10
 * @Description:https://leetcode.com/problems/reverse-integer/
 */
public class Solution7 {
    public static void main(String[] args) {
        Solution7 s = new Solution7();
        System.err.println(s.reverse(1534236469));
    }

    public int reverse(int x) {
        //先判断符号
        if (x == 0) {
            return 0;
        }
        boolean positive = (x > 0);

        //取绝对值
        x = Math.abs(x);

        //从x的最低位开始处理
        int result = 0;
        while (x >= 10) {
            int low = x % 10;

            //末尾是0则跳过
            if (low == 0 && result == 0) {
                x = x / 10;
                continue;
            }
            result = result * 10 + low;
            x = x / 10;
        }

        //处理第一位
        if (x > 0) {
            result = result * 10 + x;
        }

        result = positive ? result : -result;
        //处理溢出的情况
        if (positive && result < 0) {
            return 0;
        }

        if (!positive && result > 0) {
            return 0;
        }
        return result;
    }
}
