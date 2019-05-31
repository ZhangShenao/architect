package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/31 15:10
 * @Description:https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class Solution26 {
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 5, 6, 6, 6, 6};
        System.err.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        //因为数组是有序的,所以仅记录上一次比较的值的索引即可
        int prevIdx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[prevIdx]) {
                nums[++prevIdx] = nums[i];
            }
        }

        return prevIdx + 1;
    }
}
