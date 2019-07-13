package william.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/13 15:05
 * @Description:https://leetcode.com/problems/contains-duplicate/
 */
public class Solution217 {
    public static void main(String[] args) {
        Solution217 s = new Solution217();
        int[] nums = {1, 2, 3, 1};
        System.err.println(s.containsDuplicate(nums));
    }

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        //借助一个hash表
        Set<Integer> hash = new HashSet<>();

        for (int num : nums) {
            if (hash.contains(num)) {
                return true;
            }
            hash.add(num);
        }

        return false;
    }


}
