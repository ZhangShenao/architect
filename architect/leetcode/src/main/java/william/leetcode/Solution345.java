package william.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/14 18:03
 * @Description:https://leetcode.com/problems/reverse-vowels-of-a-string/
 */
public class Solution345 {
    private static Set<Character> vowels = new HashSet<>();

    static {
        vowels.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
    }

    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] chars = s.toCharArray();
        int l = 0;
        int r = chars.length - 1;

        while (l <= r) {
            while (l <= chars.length - 1 && !vowels.contains(chars[l])) {
                ++l;
            }
            while (r >= 0 && !vowels.contains(chars[r])) {
                --r;
            }
            if (l >= r) {
                break;
            }
            swap(chars, l, r);
            ++l;
            --r;
        }
        return new String(chars);
    }

    private void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
}
