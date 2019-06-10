package william.algo.sort;

import william.ds.heap.MaxHeap;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/10 14:15
 * @Description:堆排序
 */
public class HeapSorter implements Sorter{
    @Override
    public void sort(int[] arr) {
        Integer[] a = new Integer[arr.length];
        for (int i = 0;i < arr.length;i++){
            a[i] = arr[i];
        }
        MaxHeap<Integer> heap = new MaxHeap<>(a);
        for (int i = arr.length - 1;i >= 0;i--){
            arr[i] = heap.extractMax();
        }
    }
}
