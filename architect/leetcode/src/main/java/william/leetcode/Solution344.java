package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/14 17:53
 * @Description:https://leetcode.com/problems/reverse-string/
 */
public class Solution344 {
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }

        for (int i = 0; i < s.length; i++) {
            int j = s.length - 1 - i;
            if (i >= j) {
                return;
            }
            swap(s, i, j);
        }
    }

    private void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
}
