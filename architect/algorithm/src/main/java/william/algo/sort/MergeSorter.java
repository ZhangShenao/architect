package william.algo.sort;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/2 18:05
 * @Description:归并排序
 */
public class MergeSorter implements Sorter {
    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    //对数组arr的[start,end]范围内的元素进行归并排序
    private void mergeSort(int[] arr, int start, int end) {
        //递归退出条件
        if (start >= end) {
            return;
        }

        //对左、右两部分分别进行归并排序,然后合并两部分元素
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        merge(arr, start, mid + 1, end);
    }

    private void merge(int[] arr, int leftStart, int rightStart, int rightEnd) {
        int size = (rightEnd - leftStart + 1);
        int[] tmp = new int[size];

        int l = leftStart;
        int r = rightStart;
        int idx = 0;

        while (l < rightStart && r <= rightEnd) {
            if (arr[r] < arr[l]) {
                tmp[idx++] = arr[r++];
            } else {
                tmp[idx++] = arr[l++];
            }
        }

        while (l < rightStart) {
            tmp[idx++] = arr[l++];
        }

        while (r <= rightEnd) {
            tmp[idx++] = arr[r++];
        }

        for (int i = 0; i < size; i++) {
            arr[leftStart + i] = tmp[i];
        }

    }
}
