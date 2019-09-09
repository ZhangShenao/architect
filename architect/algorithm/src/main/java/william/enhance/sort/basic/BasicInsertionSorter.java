package william.enhance.sort.basic;

import william.enhance.sort.Sorter;
import william.utils.AlgorithmUtils;


/**
 * @Author: ZhangShenao
 * @Date: 2019/7/17 21:32
 * @Description:基础插入排序 从第二个元素开始, 依次向前遍历, 如果比前一个元素小, 就交换
 */
public class BasicInsertionSorter<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public void sort(T[] arr) {
        //第一个元素已经有序,因此从第二个元素开始
        for (int i = 1; i < arr.length; i++) {
            int j = i;

            //如果当前元素已经有序,则可以提前结束本次内层循环,这也是为什么插入排序在数组近乎有序的情况下效率很高
            while (j > 0 && arr[j].compareTo(arr[j - 1]) < 0){
                AlgorithmUtils.swap(arr, j, --j);
            }
        }
    }

    @Override
    public String name() {
        return "基础插入排序";
    }

    public static void main(String[] args) {
        Integer[] arr = AlgorithmUtils.genRandomArray(10000, -100, 100);
        Integer[] arr1 = AlgorithmUtils.copyArray(arr);
        AlgorithmUtils.sortBenchmark(SelectionSorter.class, arr1);
        AlgorithmUtils.sortBenchmark(BasicInsertionSorter.class, arr);
    }
}
