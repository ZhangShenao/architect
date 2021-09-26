package william.algo.array;

/**
 * @Author zhangshenao
 * @Date 2021-09-26
 * @Description 删除有序数组中的重复项
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class Solution26 {
    public int removeDuplicates(int[] nums) {
        //边界条件
        if (nums == null || nums.length == 0) {
            return 0;
        }


        //Filter模板：遍历一遍数组，判断哪些是需要留下的
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            //留下不重复的元素
            if (i < 1 || nums[i] != nums[i - 1]) {  //边界条件：第一个元素是一定要留下的
                nums[n] = nums[i];
                n++;
            }
        }

        return n;
    }


}
