package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/31 15:41
 * @Description:https://leetcode.com/problems/remove-element/
 */
public class Solution27 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        System.err.println(removeElement(nums, 3));
    }

    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                ++count;
            }
        }
        return count;
    }
}
