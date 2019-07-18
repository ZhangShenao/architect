package william.enhance.sort.advanced;

import william.enhance.sort.Sorter;
import william.enhance.sort.basic.OptimizedInsertionSorter;
import william.utils.AlgorithmUtils;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/18 13:26
 * @Description:双路快排 当数组中存在大量重复元素时, 采用单路快排会将重复的元素全部放在其中一个数组中, 导致递归树极不平衡, 极端情况下快排会退化成O(n * n)
 * 因此采用双路快排进行优化,将与基准值相等的元素平均分布在左右两个数组中,提升递归树的平衡性
 */
public class DoubleRouteQuickSorter<T extends Comparable<T>> implements Sorter<T> {
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

        //选出标定索引
        int p = partition2Route(arr, start, end);

        //对标定索引两侧的子数组分别进行快速排序
        quickSort(arr, start, p - 1);
        quickSort(arr, p + 1, end);
    }

    /**
     * 双路partition操作:选择标定索引p,使得arr[start,p - 1] <= arr[p] && arr[p + 1,end] >= arr[p]
     */
    private int partition2Route(T[] arr, int start, int end) {
        //优化:随机选择标定元素,避免在数组近乎有序的情况下,固定选择第一个元素为标定点而导致的算法效率退化的问题
        int idx = ThreadLocalRandom.current().nextInt(start, end + 1);
        AlgorithmUtils.swap(arr, idx, start);

        //选择标定值,默认选择第一个元素
        T v = arr[start];

        //定义索引
        int i = start + 1;      //arr[start+1,i] <= v
        int j = end;            //arr[j,end] >= v

        while (true) {
            //从左往右找到第一个>=v的元素
            while (i <= end && arr[i].compareTo(v) < 0) {
                ++i;
            }

            //从右往左找到第一个<=v的元素
            while (j >= start && arr[j].compareTo(v) > 0) {
                --j;
            }

            //如果遍历结束,则直接退出
            if (i > j) {
                break;
            }

            //交换i,j处的元素
            AlgorithmUtils.swap(arr, i, j);

            //移动索引
            ++i;
            --j;

        }

        //遍历结束后,j指向最后一个<=v的元素
        AlgorithmUtils.swap(arr, start, j);
        return j;
    }

    @Override
    public String name() {
        return "双路快排";
    }
}
