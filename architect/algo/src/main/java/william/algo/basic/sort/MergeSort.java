package william.algo.basic.sort;

import java.util.Arrays;

/**
 * @Author zhangshenao
 * @Date 2021-11-12
 * @Description 归并排序
 */
public class MergeSort {
    public void sort(int[] arr) {
        //边界
        if (arr == null || arr.length == 0) {
            return;
        }

        //分治
        mergeSort(arr, 0, arr.length - 1);
    }

    //对数组arr的[start,end]区间进行归并排序
    private void mergeSort(int[] arr, int start, int end) {
        //递归终止条件
        if (start >= end) {
            return;
        }

        //找到中间点
        int mid = start + (end - start) / 2;

        //分别对左、右两部分进行归并排序
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        //合并另个有序数组
        merge(arr, start, mid, end);
    }

    private void merge(int[] arr, int leftStart, int leftEnd, int rightEnd) {
        int size = (rightEnd - leftStart) + 1;
        int[] tmp = new int[size];    //申请一个临时数组
        int l = leftStart;
        int r = leftEnd + 1;
        int i = 0;

        //按照大小依次进行merge
        while (l <= leftEnd && r <= rightEnd) {
            if (arr[l] <= arr[r]) {  //保持排序的稳定性
                tmp[i] = arr[l];
                ++l;
            } else {
                tmp[i] = arr[r];
                ++r;
            }
            ++i;
        }

        //处理其中一个数组有剩余元素的情况
        while (l <= leftEnd) {
            tmp[i] = arr[l];
            ++l;
            ++i;
        }

        while (r <= rightEnd) {
            tmp[i] = arr[r];
            ++r;
            ++i;
        }

        //将临时数组拷贝回原数组
        for (int k = 0; k < size; k++) {
            arr[leftStart + k] = tmp[k];
        }

        //释放临时数组
        tmp = null;
    }

    public static void main(String[] args) {
        MergeSort sort = new MergeSort();
        int[] arr = new int[] {-100, 99, -200, 98, -300, 97, 0, 0, 100, 200, 100, 200, -999, -1001, 1005};
        sort.sort(arr);
        System.err.println(Arrays.toString(arr));
    }
}
