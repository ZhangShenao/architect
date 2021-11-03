package william.algo.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangshenao
 * @Date 2021-11-02
 * @Description https://leetcode-cn.com/problems/valid-anagram/description/?utm_source=LCUS&utm_medium=ip_redirect
 * &utm_campaign=transfer2china
 * 方法一：使用Map记录两个字符串中字符出现的次数,判断两个Map是否相等
 * 方法二：对两个字符串进行排序,然后比较两个字符串
 */
public class 有效的字母同位词_242 {
    //时间复杂度=O(n) * O(1) = O(n)
    //空间复杂度=O(2*n) = O(n)

    public boolean isAnagram(String s, String t) {
        //边界
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() != t.length()) {
            return false;
        }

        //使用两个Map,保存两个字符串每个字母的出现次数 key=字母 value=次数
        Map<Character, Integer> sMap = new HashMap<>(s.length());
        Map<Character, Integer> tMap = new HashMap<>(t.length());

        //遍历两个字符串,维护两个Map
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (sMap.containsKey(c)) {
                sMap.put(c, sMap.get(c) + 1);
            } else {
                sMap.put(c, 0);
            }
        }

        //遍历两个字符串,维护两个Map
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (tMap.containsKey(c)) {
                tMap.put(c, tMap.get(c) + 1);
            } else {
                tMap.put(c, 0);
            }
        }

        //判断两个Map是否相等
        return (sMap.equals(tMap));
    }
}
