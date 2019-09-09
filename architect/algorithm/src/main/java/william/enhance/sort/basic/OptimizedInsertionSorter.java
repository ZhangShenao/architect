package william.enhance.sort.basic;

import william.enhance.sort.Sorter;
import william.utils.AlgorithmUtils;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/17 21:51
 * @Description:对选择排序的优化 基础选择排序每次都需要交换数据, 效率较低, 优化后用赋值操作代替交换操作, 每个元素只需找到所在的位置插入即可
 */
public class OptimizedInsertionSorter<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public void sort(T[] arr) {
       /* //第一个元素已经有序,因此从第二个元素开始
        for (int i = 1; i < arr.length; i++) {
            //记录当前元素
            T e = arr[i];

            int j = i;
            //如果当前元素已经有序,则可以提前结束本次内层循环,这也是为什么插入排序在数组近乎有序的情况下效率很高
            while (j > 0 && e.compareTo(arr[j - 1]) < 0) {
                //将元素后移一位,用赋值操作代替交换操作
                arr[j] = arr[j - 1];
                --j;
            }

            //将元素放到指定位置
            arr[j] = e;
        }*/

        insertionSort(arr, 0, arr.length - 1);
    }

    /**
     * 对数组arr[start,end]范围进行插入排序
     */
    public void insertionSort(T[] arr, int start, int end) {
        //从第二个元素开始
        for (int i = start + 1;i <= end;i++){
            //记录当前元素
            T e = arr[i];
            int j = i;

            //将当前元素依次向前比较,找到合适的位置插入
            //如果当前元素已经有序,则可以提前结束本次内层循环,这也是为什么插入排序在数组近乎有序的情况下效率很高
            while (j > start && e.compareTo(arr[j - 1]) < 0){
                //将元素后移一位,用赋值操作代替交换操作
                arr[j] = arr[j - 1];
                --j;
            }
            arr[j] = e;
        }
    }

    @Override
    public String name() {
        return "优化的插入排序";
    }

    public static void main(String[] args) {
        Integer[] arr = AlgorithmUtils.genNearlyOrderedArray(10000, 10);
        Integer[] arr1 = AlgorithmUtils.copyArray(arr);
        Integer[] arr2 = AlgorithmUtils.copyArray(arr);
        AlgorithmUtils.sortBenchmark(SelectionSorter.class, arr);
        AlgorithmUtils.sortBenchmark(BasicInsertionSorter.class, arr1);
        AlgorithmUtils.sortBenchmark(OptimizedInsertionSorter.class, arr2);
    }
}
