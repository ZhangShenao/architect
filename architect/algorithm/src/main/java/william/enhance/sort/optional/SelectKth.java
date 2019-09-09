package william.enhance.sort.optional;

import william.utils.AlgorithmUtils;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/18 17:33
 * @Description:利用快速排序的partition思想,找出数组中第k大的元素
 */
public class SelectKth {
    public static int selectKth(Integer[] arr, int k) {
        if (arr == null || arr.length < k) {
            throw new IllegalArgumentException("数组长度过小");
        }
        return selectKth(arr, 0, arr.length - 1, k);
    }

    //在数组arr[start,end]范围内查找第k大的元素
    private static int selectKth(Integer[] arr, int start, int end, int k) {
        if (start >= end) {
            return arr[start];
        }

        int p = partition(arr, start, end, k);

        if (p == k) {
            return arr[p];
        }

        if (p < k) {
            return selectKth(arr, p + 1, end, k);
        }
        return selectKth(arr, start, p - 1, k);
    }

    //利用快速排序的partition操作
    private static int partition(Integer[] arr, int start, int end, int k) {
        int v = arr[start];

        //l指向>v的最后一个元素
        int l = start;
        int i = start + 1;  //arr[l,i) > v arr(i,end] <= v

        while (i <= end) {
            if (arr[i] > v) {
                AlgorithmUtils.swap(arr, ++l, i);
            }
            ++i;
        }

        AlgorithmUtils.swap(arr, start, l);
        return l;
    }

    public static void main(String[] args) {
        int k = 3;
        Integer[] arr = AlgorithmUtils.genRandomArray(10, -100, 100);
        Integer[] arr1 = AlgorithmUtils.copyArray(arr);
        System.err.println(selectKth(arr,k));
        Arrays.sort(arr1,Comparator.reverseOrder());
        System.err.println(arr1[k]);
    }
}
