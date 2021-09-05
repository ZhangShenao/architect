package william.algo.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Author zhangshenao
 * @Date 2021-09-05
 * @Description
 */
public class 字典序最小的k个数的子序列 {
    //给定一个正整数数组和 k，要求依次取出 k 个数，输出其中数组的一个子序列，需要满足：1. 长度为 k；2.字典序最小
    //时间复杂度O(n):每个元素都要经历一次入栈和出栈
    //空间复杂度O(n):最坏情况下所有元素都要入栈
    public static int[] findSmallSeq(int[] nums, int k) {
        //边界条件
        if (nums == null || nums.length <= 0 || nums.length < k) {
            return new int[] {};
        }

        int[] result = new int[k];

        //构建单调递增栈,栈中内容保存数组的元素。
        Deque<Integer> stack = new ArrayDeque<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            //如果当前元素比栈顶元素小,则依次消除栈顶元素,并将当前元素入栈
            int cur = nums[i];

            //注意边界条件:当剩余的元素数量不足k-1时，就不能再弹栈了
            int remainNum = nums.length - i;

            while (!stack.isEmpty() && cur < stack.peek() && stack.size() + remainNum > k) {
                stack.pop();
            }

            //将当前元素入栈
            stack.push(cur);
        }

        //如果递增栈里面的数太多,那么我们只需要取出前k个就可以了,多余的栈中的元素需要扔掉。
        while (stack.size() > k) {
            stack.pop();
        }

        //把k个元素取出来，注意这里取的顺序!
        for (int i = k - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        assert (Arrays.equals(new int[] {1, 2, 3}, findSmallSeq(new int[] {9, 2, 4, 5, 1, 2, 6, 3, 100, 4}, 3)));
        assert (Arrays.equals(new int[] {1, 2}, findSmallSeq(new int[] {9, 2, 4, 5, 1, 2, 6, 3, 100, 4}, 2)));
        assert (Arrays.equals(new int[] {1}, findSmallSeq(new int[] {9, 2, 4, 5, 1, 2, 6, 3, 100, 4}, 1)));
        assert (Arrays.equals(new int[] {1}, findSmallSeq(new int[] {1, 1, 1, 1, 1}, 1)));
    }

}
