package william.leetcode.array;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/4 上午11:28
 * <p>
 * https://leetcode-cn.com/problems/move-zeroes/
 */
public class 移动零_283 {
    //使用双指针i、j
    //指针i用于遍历数组
    //指针j用于记录非零元素的位置
    //对数组进行两次遍历
    //第一次遍历完成之后,指针i遍历完成之后,指针j指向第一个零元素的位置
    //然后对数组进行第二次遍历,从j开始将元素赋值为0
    //时间复杂度O(n)
    //空间复杂度O(1)
    public void moveZeroes(int[] nums) {
        //边界条件
        if (nums == null || nums.length <= 1) {
            return;
        }

        //双指针
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            //如果i位置的元素非零,则将i位置的元素赋值给j位置的元素
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }

        //遍历一次之后,j指向第一个零元素的位置
        //进行第二次遍历,赋值零元素
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
