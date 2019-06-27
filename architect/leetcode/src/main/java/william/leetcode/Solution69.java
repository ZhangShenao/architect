package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/27 19:16
 * @Description:https://leetcode.com/problems/sqrtx/
 */
public class Solution69 {
    public static void main(String[] args) {
        Solution69 s = new Solution69();

        System.err.println(s.mySqrt(4));
    }

    public int mySqrt(int x) {
        int start = 0;
        int end = x;
        while (true) {
            if (start * start == x) {
                return start;
            }
            if (end * end == x) {
                return end;
            }
            if (end - start <= 1) {
                return start;
            }
            long mid = (start + end) / 2;
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid > x) {
                end = (int) mid;
            } else {
                start = (int) mid;
            }
        }
    }
}
