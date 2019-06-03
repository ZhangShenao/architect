package william.algo.sort;

import william.algorithm.util.ArrayUtils;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/3 13:43
 * @Description:快速排序
 * 优化1:选择基准值时,可以选取第一个元素、中间元素和最后一个元素的中间值,避免基准值选取过大或过小导致两部分子数组极不均匀
 * 优化2:当数组中元素数量小于一定规模时,可以使用插入排序代替快速排序
 * 优化3:实现3分区快排,将与基准值相同的元素放到一个单独的分区,避免数组中重复元素过多造成的效率下降
 */
public class QuickSorter implements Sorter {
    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    //对数组arr的[start,end]范围进行快速排序
    private void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        //进行一次partition操作,选出基准值的索引
        int pivotIdx = partition(arr, start, end);

        //对基准值索引两侧的子数组,分别进行快速排序
        quickSort(arr, start, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, end);
    }

    //对数组arr的[start,end]范围进行partition操作,返回基准值的索引
    private int partition(int[] arr, int start, int end) {
        //默认选择数组的第一个元素作为基准值
        int pivot = arr[start];

        //通过l,r两个指针进行双路快排
        int l = start + 1;
        int r = end;

        //在[start,l]范围内找到第一个大于pivot的元素,在[r,end]范围内找到第一个小于pivot的元素,然后交换两个元素
        while (l <= r) {
            while (l <= r && arr[l] <= pivot) {
                ++l;
            }
            while (l <= r && arr[r] >= pivot) {
                --r;
            }

            if (l < r) {
                ArrayUtils.swapArrayElement(arr, l, r);
                ++l;
                --r;
            }
        }

        //交换基准元素,基准索引为l - 1;
        ArrayUtils.swapArrayElement(arr, start, l - 1);
        return (l - 1);
    }
}
