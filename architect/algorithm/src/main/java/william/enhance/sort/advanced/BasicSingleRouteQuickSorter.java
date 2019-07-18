package william.enhance.sort.advanced;

import william.enhance.sort.Sorter;
import william.enhance.sort.basic.OptimizedInsertionSorter;
import william.utils.AlgorithmUtils;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/18 11:29
 * @Description:基础单路快排
 */
public class BasicSingleRouteQuickSorter<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public void sort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 对数组arr[start,end]范围进行快速排序
     */
    private void quickSort(T[] arr, int start, int end) {
        //递归结束条件
        if (start >= end) {
            return;
        }

        //选出标定索引
        int p = partition(arr, start, end);

        //对标定索引两侧的子数组分别进行快速排序
        quickSort(arr, start, p - 1);
        quickSort(arr, p + 1, end);
    }

    /**
     * partition操作:选择标定索引p,使得arr[start,p - 1] < arr[p] && arr[p + 1,end] > arr[p]
     */
    private int partition(T[] arr, int start, int end) {
        //选择标定值,默认选择第一个元素
        T v = arr[start];

        //定义索引
        //i为当前遍历元素的索引
        //arr[start + 1,j] < v arr[j + 1,i) > v
        int j = start;
        int i = start + 1;

        while (i <= end) {
            //如果arr[i] > v,则无需处理,直接遍历下一个元素
            //如果arr[i] < v,则将i与j+1处的元素交换
            if (arr[i].compareTo(v) < 0) {
                AlgorithmUtils.swap(arr, i, ++j);
            }
            ++i;
        }

        //交换j和start处的元素
        AlgorithmUtils.swap(arr, j, start);

        //标定索引为j
        return j;
    }

    @Override
    public String name() {
        return "基础单路快排";
    }

    public static void main(String[] args) {
        //对于近乎有序的数组来说,普通单路快排退化成了O(n*n)级别,效率很低
        Integer[] arr = AlgorithmUtils.genNearlyOrderedArray(50000, 10);
//        Integer[] arr = AlgorithmUtils.genRandomArray(100000, -1000, 1000);
        Integer[] arr1 = AlgorithmUtils.copyArray(arr);
        Integer[] arr2 = AlgorithmUtils.copyArray(arr);
        AlgorithmUtils.sortBenchmark(OptimizedInsertionSorter.class, arr);
        AlgorithmUtils.sortBenchmark(OptimizedMergeSort.class, arr1);
        AlgorithmUtils.sortBenchmark(BasicSingleRouteQuickSorter.class, arr2);
    }
}
