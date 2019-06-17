package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/17 10:43
 * @Description:
 */
public class Solution35 {
    public int searchInsert(int[] nums, int target) {
        int i = nums.length - 1;
        while (i >= 0 && target <= nums[i]) {
            if (target == nums[i]) {
                return i;
            }
            --i;
        }
        return i + 1;
    }
}
