package william.algo.array;

/**
 * @Author zhangshenao
 * @Date 2021-09-26
 * @Description 移动零
 * https://leetcode-cn.com/problems/move-zeroes/
 */
public class Solution283 {
    public void moveZeroes(int[] nums) {
        //边界条件
        if (nums == null || nums.length == 0) {
            return;
        }


        //Filter模板：遍历一遍数组，留下满足条件的
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) { //留下不等于0的
                nums[n++] = nums[i];
            }
        }

        //在数组末尾补0
        while (n < nums.length) {
            nums[n++] = 0;
        }

    }
}
