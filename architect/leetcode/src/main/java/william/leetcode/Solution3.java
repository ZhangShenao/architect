package william.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/17 10:02
 * @Description:https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class Solution3 {
    //给定一个字符串，找到最长子字符串的长度而不重复字符。例如给定字符串"abcabcbb"，答案是"abc"，长度为3。
    public static void main(String[] args) {
        Solution3 s = new Solution3();
        System.err.println(s.lengthOfLongestSubstring("pwwkew"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        //记录当前不重复字符的最大长度
        int maxLen = 0;

        //使用一个Map保存不重复字符和其对应的索引
        Map<Character,Integer> char2Idx = new HashMap<>();

        for (int i = 0;i < s.length();){
            char c = s.charAt(i);
            //字符不重复,保存在Map中
            if (!char2Idx.containsKey(c)){
                char2Idx.put(c,i);
                ++i;
                continue;
            }

            //字符重复,记录当前最大长度,并从重复字符的下一个开始遍历
            if (char2Idx.size() > maxLen){
                maxLen = char2Idx.size();
            }
            int idx = char2Idx.get(c);
            i = idx + 1;
            char2Idx.clear();
        }


        return Math.max(maxLen,char2Idx.size());
    }
}
