package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/25 10:42
 * @Description:https://leetcode.com/problems/palindrome-number/
 */
public class Solution9 {
    public boolean isPalindrome(int x) {
        if (x < 0){
            return false;
        }
        String str = String.valueOf(x);
        if (str.length() == 1){
            return true;
        }
        char[] chars = str.toCharArray();
        int l = 0;
        int r = chars.length - 1;
        while (l < chars.length && r >= 0){
            if (l >= r){
                return true;
            }
            if (chars[l] != chars[r]){
                return false;
            }
            ++l;
            --r;
        }

        return true;
    }
}
