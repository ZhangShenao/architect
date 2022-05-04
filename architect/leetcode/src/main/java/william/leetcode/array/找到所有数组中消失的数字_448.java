package william.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/4 上午11:36
 */
public class 找到所有数组中消失的数字_448 {
    //利用数组元素的特性nums[i]在[1,n]范围内
    //对数组进行两次遍历,第一次遍历将元素进行hash映射,将nums[i]-1位置的数字变为负数
    //第一次遍历完成之后,在[1,n]范围内出现的元素值都会>0
    //之后对数组进行第二次遍历,找到<0的元素,其下标i+1即为未出现过的数字
    //时间复杂度O(n)
    //空间复杂度O(1)
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //边界条件
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        int n = nums.length;

        //第一次遍历,对数组进行hash映射
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;
            if (nums[idx] > 0) {
                nums[idx] = -nums[idx];
            }
        }

        //第二次遍历,找到<=n的元素,其下标+1就是未出现过的数字
        List<Integer> result = new ArrayList<>(n);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        找到所有数组中消失的数字_448 s = new 找到所有数组中消失的数字_448();
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result = s.findDisappearedNumbers(nums);
        System.out.println(result);
    }
}
