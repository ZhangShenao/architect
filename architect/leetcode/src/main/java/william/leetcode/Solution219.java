package william.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/15 10:26
 * @Description:https://leetcode.com/problems/contains-duplicate-ii/
 */
public class Solution219 {
    public static void main(String[] args) {
        Solution219 s = new Solution219();
        int[] nums = {1, 0, 1, 1};
        int k = 1;
        System.err.println(s.containsNearbyDuplicate(nums, k));
    }

    /*public boolean containsNearbyDuplicate(int[] nums, int k) {
        //直接在原数组上进行判断
        if (nums == null || nums.length < 2) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= k; j++) {
                if (i + j >= nums.length) {
                    break;
                }
                if (nums[i] == nums[i + j]) {
                    return true;
                }
            }
        }
        return false;
    }*/

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        //借助一个Hash保存每个元素和所在的索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int idx = map.get(nums[i]);
                if (Math.abs(i - idx) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
