package william.enhance.sort.basic;

import william.enhance.sort.Sorter;
import william.utils.AlgorithmUtils;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/17 17:44
 * @Description:选择排序
 */
public class SelectionSorter<T extends Comparable<T>> implements Sorter<T> {
    public void sort(T[] arr) {
        //依次从数组的每个位置开始,选择剩余元素中最小的,放在这个位置上
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIdx]) < 0) {
                    minIdx = j;
                }
            }

            //交换元素
            if (minIdx != i) {
                AlgorithmUtils.swap(arr, minIdx, i);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = AlgorithmUtils.genRandomArray(20, -100, 100);
        AlgorithmUtils.sortBenchmark(SelectionSorter.class,arr);
    }
}
