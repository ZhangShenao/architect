package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/12 09:46
 * @Description:https://leetcode.com/problems/valid-palindrome/
 */
public class Solution125 {
    public static void main(String[] args) {
        Solution125 s = new Solution125();
        System.err.println(s.isPalindrome("0P"));
    }
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() <= 1) {
            return true;
        }

        int l = 0;
        int r = s.length() - 1;

        while (l <= r) {
            while (l < r && !isAlphanumeric(s.charAt(l))) {
                ++l;
            }
            while (r > l && !isAlphanumeric(s.charAt(r))) {
                --r;
            }
            if (!String.valueOf(s.charAt(l)).equalsIgnoreCase(String.valueOf(s.charAt(r)))) {
                return false;
            }
            ++l;
            --r;
        }

        return true;

    }

    //是否是数字或字母
    private boolean isAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }
}
