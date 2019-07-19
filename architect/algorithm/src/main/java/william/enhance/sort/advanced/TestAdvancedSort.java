package william.enhance.sort.advanced;

import william.utils.AlgorithmUtils;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/18 13:18
 * @Description:测试各种归并排序和快速排序的性能
 */
public class TestAdvancedSort {
    public static void main(String[] args) {
        //普通随机数组:快速排序由于归并排序
        System.err.println("普通随机数组");
        Integer[] arr1 = AlgorithmUtils.genRandomArray(100000, -1000, 1000);
        Integer[] arr2 = AlgorithmUtils.copyArray(arr1);
        Integer[] arr3 = AlgorithmUtils.copyArray(arr1);
        Integer[] arr4 = AlgorithmUtils.copyArray(arr1);
        Integer[] arr5 = AlgorithmUtils.copyArray(arr1);
        Integer[] arr6 = AlgorithmUtils.copyArray(arr1);
        Integer[] arr7 = AlgorithmUtils.copyArray(arr1);
        AlgorithmUtils.sortBenchmark(OptimizedMergeSort.class, arr1);
        AlgorithmUtils.sortBenchmark(OptimizedSingleRouteQuickSorter.class, arr2);
        AlgorithmUtils.sortBenchmark(DoubleRouteQuickSorter.class, arr3);
        AlgorithmUtils.sortBenchmark(ThreeRouteQuickSorter.class, arr4);
        AlgorithmUtils.sortBenchmark(BasicHeapSorter.class, arr5);
        AlgorithmUtils.sortBenchmark(OptimizedHeapSorter.class, arr6);
        AlgorithmUtils.sortBenchmark(InPlaceHeapSorter.class, arr7);

        System.err.println("-----------------------------------");

        //近乎有序数组:性能差不多,归并排序略好
        System.err.println("近乎有序数组");
        arr1 = AlgorithmUtils.genNearlyOrderedArray(100000, 10);
        arr2 = AlgorithmUtils.copyArray(arr1);
        arr3 = AlgorithmUtils.copyArray(arr1);
        arr4 = AlgorithmUtils.copyArray(arr1);
        arr5 = AlgorithmUtils.copyArray(arr1);
        arr6 = AlgorithmUtils.copyArray(arr1);
        arr7 = AlgorithmUtils.copyArray(arr1);
        AlgorithmUtils.sortBenchmark(OptimizedMergeSort.class, arr1);
        AlgorithmUtils.sortBenchmark(OptimizedSingleRouteQuickSorter.class, arr2);
        AlgorithmUtils.sortBenchmark(DoubleRouteQuickSorter.class, arr3);
        AlgorithmUtils.sortBenchmark(ThreeRouteQuickSorter.class, arr4);
        AlgorithmUtils.sortBenchmark(BasicHeapSorter.class, arr5);
        AlgorithmUtils.sortBenchmark(OptimizedHeapSorter.class, arr6);
        AlgorithmUtils.sortBenchmark(InPlaceHeapSorter.class, arr7);

        System.err.println("-----------------------------------");

        //大量重复元素数组:单路快排性能退化成O(n*n),双路快排有一定提升,三路快排性能最优
        System.err.println("大量重复元素数组");
        arr1 = AlgorithmUtils.genRandomArray(100000, 0, 10);
        arr2 = AlgorithmUtils.copyArray(arr1);
        arr3 = AlgorithmUtils.copyArray(arr1);
        arr4 = AlgorithmUtils.copyArray(arr1);
        arr5 = AlgorithmUtils.copyArray(arr1);
        arr6 = AlgorithmUtils.copyArray(arr1);
        arr7 = AlgorithmUtils.copyArray(arr1);
        AlgorithmUtils.sortBenchmark(OptimizedMergeSort.class, arr1);
        AlgorithmUtils.sortBenchmark(OptimizedSingleRouteQuickSorter.class, arr2);
        AlgorithmUtils.sortBenchmark(DoubleRouteQuickSorter.class, arr3);
        AlgorithmUtils.sortBenchmark(ThreeRouteQuickSorter.class, arr4);
        AlgorithmUtils.sortBenchmark(BasicHeapSorter.class, arr5);
        AlgorithmUtils.sortBenchmark(OptimizedHeapSorter.class, arr6);
        AlgorithmUtils.sortBenchmark(InPlaceHeapSorter.class, arr7);

        System.err.println("-----------------------------------");
    }
}
