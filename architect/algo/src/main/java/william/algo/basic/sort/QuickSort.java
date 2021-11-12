package william.algo.basic.sort;

import java.util.Arrays;

/**
 * @Author zhangshenao
 * @Date 2021-11-12
 * @Description 快速排序
 */
public class QuickSort {
    public void sort(int[] arr) {
        //边界
        if (arr == null || arr.length == 0) {
            return;
        }

        //分治
        quickSort(arr, 0, arr.length - 1);
    }

    //对数组arr的[start,end]区间进行快速排序
    private void quickSort(int[] arr, int start, int end) {
        //递归终止条件
        if (start >= end) {
            return;
        }

        //找到分区点
        int partition = partition(arr, start, end);

        //对分区点的左、右两部分分别进行快排
        quickSort(arr, start, partition - 1);
        quickSort(arr, partition + 1, end);
    }

    private int partition(int[] arr, int start, int end) {
        //固定选择第一个元素作为基准值
        int pivot = arr[start];

        //双路指针
        int i = start + 1;
        int j = end;

        while (true) {
            while (i <= end && arr[i] < pivot) {    //找到左边第一个比基准值大的元素
                ++i;
            }
            while (j >= start && arr[j] > pivot) {  //找到右边第一个比基准值小的元素
                --j;
            }
            if (i >= j) {
                break;
            }

            //交换元素
            swap(arr, i, j);

            //进行下一轮
            i++;
            j--;
        }

        //j就是基准值的位置,交换元素
        swap(arr, start, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] arr = new int[] {-100, 99, -200, 98, -300, 97, 0, 0, 100, 200, 100, 200, -999, -1001, 1005, -100, 99};
        sort.sort(arr);
        System.err.println(Arrays.toString(arr));
    }
}
