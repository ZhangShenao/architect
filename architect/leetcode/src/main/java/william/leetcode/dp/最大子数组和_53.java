package william.leetcode.dp;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/18 上午10:37
 * <p>
 * https://leetcode.cn/problems/maximum-subarray/
 */
public class 最大子数组和_53 {
    //采用动态规划(Dynamic Programming)思想
    //遍历数组,保存当前最大子数组和
    //当访问nums[i]时,根据nums[i]的值,判断是将nums[i]加入当前子数组中,还是从nums[i]开始重置子数组
    //状态转移公式 newSum=Max(nums[i],sum + nums[i])
    //时间复杂度O(N) 只需遍历一次原数组
    //空间复杂度O(1)
    public int maxSubArray(int[] nums) {
        //边界条件
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int maxSum = nums[0];  //记录最大子数组和
        int prevSum = nums[0];  //记录当前最大子数组和

        //遍历数组
        for (int i = 1; i < nums.length; i++) {
            //根据当前元素的值,判断是将当前元素加入到子数组中,还是从当前元素开始重置子数组
            int sum = nums[i] + prevSum;
            prevSum = Math.max(sum, nums[i]);   //更新当前记录的最大子数组和

            //更新最大子数组和
            if (prevSum > maxSum) {
                maxSum = prevSum;
            }
        }


        //返回最大子数组和
        return maxSum;

    }
}
