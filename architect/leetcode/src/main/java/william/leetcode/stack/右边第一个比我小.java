package william.leetcode.stack;

import java.util.Stack;

public class 右边第一个比我小 {
    public static int[] findRightSmall(int[] arr) {
        //边界条件
        if (arr == null || arr.length <= 0) {
            return new int[0];
        }

        //初始化结果数组
        int[] result = new int[arr.length];

        //使用递增栈暂存元素下标
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i];

            //如果当前元素破坏了栈的递增性,则将栈顶元素依次出栈,并记录当前元素为最小
            while (!stack.isEmpty() && cur < arr[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i;
            }

            //当前元素下标入栈
            stack.push(i);
        }

        //栈中剩余的元素无法被消除,则对应结果为-1
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            result[idx] = -1;
        }

        return result;

    }
}
