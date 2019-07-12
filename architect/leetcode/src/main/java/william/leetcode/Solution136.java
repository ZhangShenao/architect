package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/12 10:00
 * @Description:https://leetcode.com/problems/single-number/
 */
public class Solution136 {
    public static void main(String[] args) {
        Solution136 s = new Solution136();
        int[] nums = {4, 1, 2, 1, 2};
        System.err.println(s.singleNumber(nums));
    }

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            //使用异或运算
            res ^= nums[i];
        }
        return res;
    }
}
