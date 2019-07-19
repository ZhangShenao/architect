package william.enhance.sort.advanced;

import william.enhance.sort.Sorter;
import william.utils.AlgorithmUtils;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/19 17:23
 * @Description:原地堆排序,直接在原数组上进行操作,避免了基本堆排序占用的O(n)的空间复杂度
 */
public class InPlaceHeapSorter<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public void sort(T[] arr) {
        //首先进行heapify操作,将数组变成一个堆
        heapify(arr);

        //每次将堆顶元素与数组末尾元素进行交换,然后对堆顶元素进行shiftDown操作,重新构建堆
        for (int i = arr.length - 1; i > 0; i--) {
            AlgorithmUtils.swap(arr, 0, i);
            shiftDown(arr, i, 0);
        }
    }

    private void heapify(T[] arr) {
        //最后一个非叶子节点的下标=(arr.length - 2) / 2)
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            shiftDown(arr, arr.length, i);
        }

    }

    //对长度为n的数组arr的索引i进行shiftDown操作
    private void shiftDown(T[] arr, int n, int i) {
        while (leftChild(i) < n) {
            int max = i;
            if (arr[leftChild(i)].compareTo(arr[i]) > 0) {
                max = leftChild(i);
            }
            if (rightChild(i) < n && arr[rightChild(i)].compareTo(arr[max]) > 0) {
                max = rightChild(i);
            }
            if (max == i) {
                return;
            }
            AlgorithmUtils.swap(arr, i, max);
            i = max;
        }
    }

    private int leftChild(int i) {
        return i * 2 + 1;
    }

    private int rightChild(int i) {
        return i * 2 + 2;
    }

    @Override
    public String name() {
        return "原地堆排序";
    }
}
