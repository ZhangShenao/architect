package william.leetcode.sort;

import java.util.Arrays;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/14 上午11:04
 * <p>
 * 归并排序
 * 时间复杂度O(N*LogN)
 * 空间复杂度O(N)
 */
public class 归并排序 {
    public static void sort(int[] arr) {
        //边界条件
        if (arr == null || arr.length == 0) {
            return;
        }

        mergeSort(arr, 0, arr.length - 1);
    }

    //对数组arr的[start,end]范围进行归并排序
    //递归实现
    private static void mergeSort(int[] arr, int start, int end) {
        //递归终止条件
        if (start >= end) {
            return;
        }

        //首先将数组分为左、右两个部分
        int mid = start + ((end - start) >> 1);

        //递归实现:对左、右两部分分别进行归并排序,排序后两部分均为有序
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        //最后将两个部分有序的数组进行merge,这样就实现了整个数组有序
        merge(arr, start, mid, end);
    }

    //对数组arr的[lStart,lEnd]和[lEnd+1,rEnd]两部分进行merge操作
    //其中[lStart,lEnd]和[lEnd+1,rEnd]两个数组均为有序数组
    private static void merge(int[] arr, int lStart, int lEnd, int rEnd) {
        //申请临时数组
        int size = (rEnd - lStart) + 1;
        int[] tmp = new int[size];

        //定义指针
        int l = lStart;
        int r = lEnd + 1;
        int i = 0;

        //依次遍历两个有序数组,将较小的元素放入临时数组中
        while (l <= lEnd || r <= rEnd) {
            if (l > lEnd) {  //如果左数组已经遍历完,则直接添加右数组元素
                tmp[i] = arr[r];
                r++;
            } else if (r > rEnd) {    //如果右数组已经遍历完,则直接添加左数组元素
                tmp[i] = arr[l];
                l++;
            } else { //两个数组均未遍历完,则添加较小元素
                if (arr[l] <= arr[r]) {
                    tmp[i] = arr[l];
                    l++;
                } else {
                    tmp[i] = arr[r];
                    r++;
                }
            }
            i++;
        }

        //最后将临时数组拷贝回原数组
        for (int j = 0; j < size; j++) {
            arr[lStart + j] = tmp[j];
        }
    }

    public static void main(String[] args) {
        int[] arr = ArrayGenerator.genRandomIntArr(20, -1000, 1000);
        System.out.println("原始数组");
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后数组");
        System.out.println(Arrays.toString(arr));
    }
}
