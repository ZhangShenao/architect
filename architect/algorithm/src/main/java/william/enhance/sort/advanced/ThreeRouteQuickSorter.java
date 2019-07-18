package william.enhance.sort.advanced;

import william.enhance.sort.Sorter;
import william.enhance.sort.basic.OptimizedInsertionSorter;
import william.utils.AlgorithmUtils;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/18 13:55
 * @Description:三路快排 在双路快排的基础上进行优化, 将数组分为 >v ==v 和 <v三个部分,当数组存在大量重复元素时可以有效提升性能
 */
public class ThreeRouteQuickSorter<T extends Comparable<T>> implements Sorter<T> {
    private OptimizedInsertionSorter insertionSorter = new OptimizedInsertionSorter();

    //使用插入排序的阈值
    private static final int USE_INSERTION_SORT_THRESHOLD = 15;

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

        //优化:当当前排序的数组中元素较少时,转而采用插入排序
        if (end - start <= USE_INSERTION_SORT_THRESHOLD) {
            insertionSorter.insertionSort(arr, start, end);
            return;
        }

        //---------------三路partition操作---------------

        //优化:随机选择标定元素,避免在数组近乎有序的情况下,固定选择第一个元素为标定点而导致的算法效率退化的问题
        int idx = ThreadLocalRandom.current().nextInt(start, end + 1);
        AlgorithmUtils.swap(arr, idx, start);
        //选择标定值,默认选择第一个元素
        T v = arr[start];

        //定义索引
        int lt = start;     //arr[start + 1,lt] < v
        int gt = end + 1;   //arr[gt,end] > v
        int i = start + 1;  //arr[lt + 1,i) == v

        //遍历数组,当i=gt时表示全部遍历完成
        while (i < gt) {
            //如果当前元素==v,直接遍历下一个元素
            if (arr[i].compareTo(v) == 0) {
                ++i;
            }

            //如果当前元素<v,则将当前元素与lt的下一个元素进行交换
            else if (arr[i].compareTo(v) < 0) {
                AlgorithmUtils.swap(arr, i++, ++lt);
            }

            //如果当前元素>v,则将当前元素与gt的前一个元素进行交换
            else {
                AlgorithmUtils.swap(arr, i, --gt);
                //注:这里交换过来的是一个为处理过的元素,因此不要执行i++
            }
        }

        //遍历后,lt指向<v的最后一个元素,交换start和lt
        AlgorithmUtils.swap(arr, start, lt);

        //然后递归对<v 和 >v的两部分数组执行三路快排, ==v的部分不用考虑
        quickSort(arr, start, lt - 1);
        quickSort(arr, gt, end);
    }

    @Override
    public String name() {
        return "三路快排";
    }
}
