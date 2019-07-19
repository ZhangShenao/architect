package william.enhance.sort.advanced;

import william.enhance.heap.ArrayMaxHeap;
import william.enhance.heap.MaxHeap;
import william.enhance.sort.Sorter;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/19 16:57
 * @Description:
 */
public class BasicHeapSorter<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public void sort(T[] arr) {
        int capacity = arr.length;
        MaxHeap<T> heap = new ArrayMaxHeap<>(capacity);

        for (int i = 0; i < capacity; i++) {
            heap.insert(arr[i]);
        }

        for (int i = 0; i < capacity; i++) {
            arr[capacity - 1 - i] = heap.extractMax();
        }
    }

    @Override
    public String name() {
        return "基础堆排序";
    }
}
