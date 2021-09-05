package william.algo.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Author zhangshenao
 * @Date 2021-09-05
 * @Description
 */
public class 右边第一个比我小 {
    //一个整数数组 A，找到每个元素：右边第一个比我小的下标位置，没有则用 -1 表示。
    //时间复杂度O(n)：每个元素都需要入栈、出栈
    //空间复杂度O(n)：最坏情况下所有元素都要入栈
    public static int[] findRightSmall(int[] arr) {
        //边界条件
        if (arr == null || arr.length <= 0) {
            return new int[0];
        }

        int[] result = new int[arr.length];

        //构建单调递增栈,栈中存储的内容是数组中元素的下标
        Deque<Integer> stack = new ArrayDeque<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            //为了保证栈的单调性,将当前元素与栈中的元素依次比较,如果当前元素比栈中元素小,则消除栈中元素,并将结果数组的相应位置都置为当前元素
            int cur = arr[i];
            while (!stack.isEmpty() && cur < arr[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i;
            }

            //将当前元素下标入栈
            stack.push(i);
        }

        //栈中未被消除的元素下标,说明其右边没有更小的元素,则将其对应位置统一置为-1
        while (!stack.isEmpty()) {
            int idx = stack.poll();
            result[idx] = -1;
        }

        return result;
    }

    public static void main(String[] args) {
        assert (Arrays.equals(new int[] {1, -1}, findRightSmall(new int[] {5, 4})));
        assert (Arrays.equals(new int[] {5, 5, 5, 4, 5, -1, -1}, findRightSmall(new int[] {1, 2, 4, 9, 4, 0, 5})));
    }


}
