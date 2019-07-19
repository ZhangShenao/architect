package william.enhance.sort.advanced;

import william.enhance.heap.ArrayMaxHeap;
import william.enhance.heap.MaxHeap;
import william.enhance.sort.Sorter;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/19 17:07
 * @Description:优化的堆排序,直接通过数组建堆
 */
public class OptimizedHeapSorter<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public void sort(T[] arr) {
        MaxHeap<T> heap = new ArrayMaxHeap<>(arr);

        for (int i = 0; i < arr.length; i++) {
            arr[arr.length - 1 - i] = heap.extractMax();
        }
    }

    @Override
    public String name() {
        return "优化堆排序";
    }
}
