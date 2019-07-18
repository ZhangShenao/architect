package william.enhance.sort.basic;

import william.enhance.sort.Sorter;
import william.utils.AlgorithmUtils;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/18 09:58
 * @Description:冒泡排序
 */
public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public void sort(T[] arr) {
        //从第一个元素开始,每次比较相邻的两个元素,让较大的那个放在后面
        for (int i = 0;i < arr.length - 2;i++){
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    AlgorithmUtils.swap(arr, j, j + 1);
                }
            }
        }
    }

    @Override
    public String name() {
        return "冒泡排序";
    }

    public static void main(String[] args) {
//        Integer[] arr = AlgorithmUtils.genNearlyOrderedArray(10000, 10);
        Integer[] arr = AlgorithmUtils.genRandomArray(10000,-1000,1000);
        Integer[] arr1 = AlgorithmUtils.copyArray(arr);
        Integer[] arr2 = AlgorithmUtils.copyArray(arr);
        Integer[] arr3 = AlgorithmUtils.copyArray(arr);
        AlgorithmUtils.sortBenchmark(SelectionSorter.class, arr);
        AlgorithmUtils.sortBenchmark(BubbleSorter.class, arr1);
        AlgorithmUtils.sortBenchmark(BasicInsertionSorter.class, arr2);
        AlgorithmUtils.sortBenchmark(OptimizedInsertionSorter.class, arr3);
    }
}
