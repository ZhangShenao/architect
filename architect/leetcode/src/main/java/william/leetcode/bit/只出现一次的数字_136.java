package william.leetcode.bit;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/15 上午11:16
 * <p>
 * 利用异或(^)的特性:
 * 任意数字与自己异或,结果都是0
 * 任意数字以0异或,结果都是自身
 * <p>
 * 首先定义初始结果0
 * 然后遍历数组,依次与每个元素进行异或
 * 因为其它数字都出现过两次,异或得到的结果都是0
 * 因此最终的结果就是仅出现了一次的元素
 * <p>
 * 时间复杂度O(N)
 * 空间复杂度O(1)
 */
public class 只出现一次的数字_136 {
    public int singleNumber(int[] nums) {
        //边界条件
        if (nums == null || nums.length == 0) {
            return -1;
        }

        //定义初始结果
        int result = 0;

        //遍历数组,依次与每个元素进行异或
        for (int n : nums) {
            result = (result ^ n);
        }

        //因为其它数字都出现过两次,异或得到的结果都是0
        //因此最终的结果就是仅出现了一次的元素
        return result;
    }
}
