package william.leetcode.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/sliding-window-maximum/description/
 */
public class 滑动窗口最大值239 {
    //使用双端队列构造一个单调递减队列,队头元素就是当前队列中的最大值
    private ArrayDeque<Integer> queue = new ArrayDeque<>();

    //入队
    private void offer(int v) {
        //保证队列的单调性
        while (!queue.isEmpty() && v > queue.getLast()) {
            queue.removeLast();
        }
        queue.addLast(v);
    }

    //出队——仅当队头元素与指定元素相等时才出队
    private int poll(int v) {
        if (!queue.isEmpty() && queue.getFirst() == v) {
            return queue.removeFirst();
        }

        return -1;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        //边界条件
        if (nums == null || nums.length <= 0 || k <= 0 || k > nums.length) {
            return new int[0];
        }

        //使用List保存结果
        List<Integer> result = new ArrayList<>();

        //遍历数组
        for (int i = 0; i < nums.length; i++) {
            //将当前元素入队
            offer(nums[i]);

            if (i + 1 < k) { //未达到窗口
                continue;
            }

            //记录当前窗口最大值
            result.add(queue.getFirst());

            //将之前的最大值出队
            if (i - k + 1 >= 0) {
                int last = nums[i - k + 1];
                poll(last);
            }
        }

        return result.stream().mapToInt(x -> x).toArray();
    }
}
