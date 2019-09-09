package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/26 13:19
 * @Description:https://leetcode.com/problems/length-of-last-word/
 */
public class Solution58 {
    public static void main(String[] args) {
        Solution58 s = new Solution58();
        System.err.println(s.lengthOfLastWord("    day"));
    }

    public int lengthOfLastWord(String s) {
        if (s == null || "".equals(s) || " ".equals(s) || s.length() == 0) {
            return 0;
        }

        //记录当前单词的长度
        int len = 0;
        char[] chars = s.toCharArray();


        for (int i = 0; i < chars.length; i++) {
            //遇到普通字符,直接增加长度
            if (chars[i] != ' '){
                ++len;
                continue;
            }


            //遇到' ',则寻找第一个非' '的字符
            while (i < chars.length && chars[i] == ' '){
                ++i;
            }

            //如果都是' ',则返回当前长度
            if (i > chars.length - 1){
                return len;
            }

            //否则重置长度
            --i;
            len = 0;
        }


        return len;
    }
}
