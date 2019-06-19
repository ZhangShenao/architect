package william.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/19 08:30
 * @Description:https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class Solution378 {
    public int kthSmallest(int[][] matrix, int k) {
        //借助一个大顶堆,保存矩阵中最小的k个元素
        //注:PriorityQueue默认是一个小顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0;i < matrix.length;i++){
            for (int j = 0;j < matrix[i].length;j++){
                heap.offer(matrix[i][j]);

                //当堆中元素数量超过k个时,将最大的元素移除
                if (heap.size() > k){
                    heap.poll();
                }
            }
        }

        //堆顶元素就是最小的k个元素中最大的,即第k小的
        return heap.poll();
    }
    
    
}
