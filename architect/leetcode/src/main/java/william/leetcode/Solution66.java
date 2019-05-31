package william.leetcode;


import java.util.Arrays;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/31 15:57
 * @Description:https://leetcode.com/problems/plus-one/
 */
public class Solution66 {
    public static void main(String[] args) {
        int[] digits = {7, 9, 9, 9};
        System.err.println(Arrays.toString(plusOne(digits)));
    }

    public static int[] plusOne(int[] digits) {
        int len = digits.length;

        for (int i = len - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                return digits;
            }
        }

        if (digits[0] != 0) {
            return digits;
        }

        int[] dst = new int[len + 1];
        System.arraycopy(digits, 0, dst, 1, len);
        dst[0] = 1;
        return dst;
    }
}
