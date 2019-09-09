package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/8 18:05
 * @Description:https://leetcode.com/problems/implement-strstr/
 */
public class Solution28 {
    public static void main(String[] args) {
        Solution28 s = new Solution28();
        System.err.println(s.strStr("aaa","aaaa"));
    }

    public int strStr(String haystack, String needle) {
        if (haystack == null){
            return -1;
        }
        if (needle == null ){
            return -1;
        }
        if (haystack.length() == 0 && needle.length() != 0){
            return -1;
        }
        if (needle.length() == 0){
            return 0;
        }
        if (haystack.equals(needle)){
            return 0;
        }

        for (int i = 0;i < haystack.length();){
            if (haystack.charAt(i) != needle.charAt(0)){
                ++i;
            }

            int j = 0;
            while (j < needle.length() && i + j < haystack.length()){
                if (haystack.charAt(i + j) != needle.charAt(j)){
                    break;
                }
                ++j;
            }

            if (j >= needle.length()){
                return i;
            }else {
                ++i;
            }
        }

        return -1;

    }
}
