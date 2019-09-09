package william.enhance.sort.advanced;

import william.enhance.sort.Sorter;
import william.enhance.sort.basic.OptimizedInsertionSorter;
import william.utils.AlgorithmUtils;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/18 10:24
 * @Description:优化的归并排序
 * 在基础归并排序上进行两点优化:
 * 1.如果划分后的左、右两个子数组已经有序,则归并排序可以提前结束
 * 2.当当前排序的数组中元素较少时,转而采用插入排序
 */
public class OptimizedMergeSort<T extends Comparable<T>> implements Sorter<T> {
    private OptimizedInsertionSorter insertionSorter = new OptimizedInsertionSorter();

    //使用插入排序的阈值
    private static final int USE_INSERTION_SORT_THRESHOLD = 15;

    @Override
    public void sort(T[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 对数组arr[start,end]范围进行归并排序
     */
    private void mergeSort(T[] arr, int start, int end) {
        //递归结束条件
        if (start >= end) {
            return;
        }

        //优化:当当前排序的数组中元素较少时,转而采用插入排序
        if (end - start <= USE_INSERTION_SORT_THRESHOLD){
            insertionSorter.insertionSort(arr,start,end);
            return;
        }

        //将数组分为左、右两个部分
        int mid = start + (end - start) / 2;

        //对左、右两个部分分别进行归并排序
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        //优化:如果划分后的左、右两个子数组已经有序,则归并排序可以提前结束
        if (arr[mid].compareTo(arr[mid + 1]) <= 0){
            return;
        }

        //将两部分排好序的数组合并成一个有序的数组
        merge(arr, start, mid, end);
    }

    private void merge(T[] arr, int leftStart, int leftEnd, int rightEnd) {
        int len = (rightEnd - leftStart + 1);

        //声明一个临时数组
        T[] tmp = (T[]) new Comparable[len];

        //定义索引
        int l = leftStart;      //左半部分数组的索引
        int r = leftEnd + 1;    //右半部分数组的索引
        int i = 0;              //合并后新数组的索引

        //合并数组——依次从左、右两部分数组中选取最小的元素,放入新数组中,并移动索引
        while (l <= leftEnd && r <= rightEnd) {
            if (arr[r].compareTo(arr[l]) < 0) {
                tmp[i++] = arr[r++];
            } else {
                tmp[i++] = arr[l++];
            }
        }

        //如果某部分数组已经遍历完,则直接把另一部分数组的元素放入新数组中
        while (l <= leftEnd) {
            tmp[i++] = arr[l++];
        }

        while (r <= rightEnd) {
            tmp[i++] = arr[r++];
        }

        //将临时数组复制给新数组
        for (int k = 0; k < len; k++) {
            arr[leftStart + k] = tmp[k];
        }

    }

    @Override
    public String name() {
        return "优化的归并排序";
    }

    public static void main(String[] args) {
        Integer[] arr = AlgorithmUtils.genNearlyOrderedArray(50000, 10);
//        Integer[] arr = AlgorithmUtils.genRandomArray(10000,-1000,1000);
        Integer[] arr1 = AlgorithmUtils.copyArray(arr);
        Integer[] arr2 = AlgorithmUtils.copyArray(arr);
        Integer[] arr3 = AlgorithmUtils.copyArray(arr);
        Integer[] arr4 = AlgorithmUtils.copyArray(arr);
        Integer[] arr5 = AlgorithmUtils.copyArray(arr);
       /* AlgorithmUtils.sortBenchmark(SelectionSorter.class, arr);
        AlgorithmUtils.sortBenchmark(BubbleSorter.class, arr1);
        AlgorithmUtils.sortBenchmark(BasicInsertionSorter.class, arr2);*/
        AlgorithmUtils.sortBenchmark(OptimizedInsertionSorter.class, arr3);
        AlgorithmUtils.sortBenchmark(BasicMergeSorter.class, arr4);
        AlgorithmUtils.sortBenchmark(OptimizedMergeSort.class, arr5);
    }

}
