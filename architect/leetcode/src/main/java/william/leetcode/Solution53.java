package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/26 13:01
 * @Description:https://leetcode.com/problems/maximum-subarray/
 */
public class Solution53 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        //记录当前sum
        int sum = nums[0];

        //记录最大值
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            //如果之前的sum<=0,则丢弃,从当前元素开始
            if (sum <= 0) {
                sum = nums[i];
            }

            //否则将当前元素也加到一起
            else {
                sum += nums[i];
            }

            //比较max值
            max = Math.max(max, sum);

        }

        return max;
    }
}
