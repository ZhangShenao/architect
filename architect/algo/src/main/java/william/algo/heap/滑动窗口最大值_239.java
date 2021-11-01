package william.algo.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author zhangshenao
 * @Date 2021-11-01
 * @Description https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class 滑动窗口最大值_239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //边界
        if (k <= 0) {
            throw new IllegalArgumentException();
        }

        if (nums == null || nums.length == 0) {
            return new int[] {};
        }

        ArrayList<Integer> result = new ArrayList<>();      //使用集合暂存每次最大值

        //维护一个容量为k的最大堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            if (maxHeap.size() == k) {
                result.add(maxHeap.peek());
            }
            if (maxHeap.size() < k) {   //如果最大值中元素未满,则直接将元素放入堆中
                maxHeap.offer(k);
            } else {
                //如果对中元素已满,则记录当前最大值,然后删除最早进入堆的元素
                int idx = i - k;
                maxHeap.remove(nums[idx]);
                maxHeap.offer(nums[i]);
            }

        }

        int[] arr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }

        return arr;
    }
}
