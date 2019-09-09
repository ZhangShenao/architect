package william.leetcode;

import java.util.Arrays;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/27 19:53
 * @Description:https://leetcode.com/problems/longest-common-prefix/
 */
public class Solution14 {
    public static void main(String[] args) {
        Solution14 s = new Solution14();
        String[] strs = {"flower", "flow", "flight"};
        System.err.println(s.longestCommonPrefix(strs));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        //首先对字符串数组进行排序,默认按照字母顺序排列
        Arrays.sort(strs);

        //取最小的长度
        String first = strs[0];
        String last = strs[strs.length - 1];
        int len = Math.min(first.length(), last.length());

        StringBuilder builder = new StringBuilder();

        //依次对比每个字符
        for (int i = 0; i < len; i++) {
            //只需比较首位字符串是否相等,即可判断每个字符串是否相等
            if (first.charAt(i) != last.charAt(i)) {
                break;
            }
            builder.append(first.charAt(i));
        }
        return builder.toString();
    }
}
