package william.leetcode;

import java.util.Arrays;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/12 16:32
 * @Description:https://leetcode.com/problems/majority-element/
 */
public class Solution169 {
    public static void main(String[] args) {
        Solution169 s = new Solution169();
        int[] nums = {6, 6, 6, 7, 7};
        System.err.println(s.majorityElement(nums));
    }

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /*public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        //使用一个Map,记录每个元素出现的次数
        Map<Integer, Integer> times = new HashMap<>();

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (!times.containsKey(nums[i])) {
                times.put(nums[i], 1);
            } else {
                int t = times.get(nums[i]);
                times.put(nums[i], ++t);
                if (t > len / 2) {
                    return nums[i];
                }
            }
        }

        throw new IllegalArgumentException("No majority!!");
    }*/

}
