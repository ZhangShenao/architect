package william.leetcode;

import java.util.Arrays;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/3 17:54
 * @Description:https://leetcode.com/problems/move-zeroes/
 */
public class Solution283 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.err.println(Arrays.toString(nums));
    }

    public static void moveZeroes(int[] nums) {
        //记录当前数组中非0元素的个数
        int count = 0;
        int len = nums.length;

        //依次存放非0元素
        for (int i = 0;i < len;i++){
            if (nums[i] != 0){
                nums[count++] = nums[i];
            }
        }

        //拷贝0元素
        for (int i = count;i < len;i++){
            nums[i] = 0;
        }
    }
}
