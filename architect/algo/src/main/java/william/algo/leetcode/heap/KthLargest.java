package william.algo.leetcode.heap;

import java.util.PriorityQueue;

/**
 * @Author zhangshenao
 * @Date 2021-11-01
 * @Description https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/
 * 整体时间复杂度O(n*logk)
 */
public class KthLargest {
    //维护一个容量为k的小顶堆。JDK提供的PriorityQueue默认就是小顶堆
    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        if (k <= 0 || nums == null) {
            throw new IllegalArgumentException();
        }
        this.minHeap = new PriorityQueue<>(k);
        this.k = k;
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    //添加元素,维护小顶堆
    //时间复杂度O(logk)
    public int add(int val) {
        //如果堆中元素未满,则直接将元素放入堆中
        if (minHeap.size() < k) {
            minHeap.offer(val);
            return minHeap.peek();
        }

        //如果当前元素比堆顶元素大,则移除堆顶元素,并且将当前元素放入堆中
        if (val > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(val);
        }
        return minHeap.peek();
    }
}
