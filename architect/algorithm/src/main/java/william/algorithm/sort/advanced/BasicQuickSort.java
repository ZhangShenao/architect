package william.algorithm.sort.advanced;

import william.algorithm.util.ArrayUtils;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/19 10:05
 * @Description:基础快速排序:核心是对数组进行一次partition操作,找到一个索引p,使得arr[0,p-1] < arr[p] 且 arr[p+1,n-1] > arr[p]
 * 时间复杂度:O(n*logn)
 * 空间复杂度:O(1)
 */
public class BasicQuickSort {
    public static void quickSort(int[] arr) {
        doQuickSort(arr, 0, arr.length - 1);
    }

    /**
     * 对数组arr[l,r]区间进行快速排序
     */
    private static void doQuickSort(int[] arr, int l, int r) {
        //递归退出条件
        if (l >= r) {
            return;
        }

        //首先进行partition操作,将数组分为两部分
        int p = partition(arr, l, r);

        //然后分别对左右两部分递归进行doQuickSort()
        doQuickSort(arr, l, p - 1);
        doQuickSort(arr, p + 1, r);

    }

    /**
     * 对数组arr[l,r]范围进行partition操作,返回索引p,使得arr[l,p-1] < arr[p] 且 arr[p+1,r] > arr[p]
     */
    private static int partition(int[] arr, int l, int r) {
        //固定选取第一个元素作为基准值
        int v = arr[l];

        //定义索引arr[l,j] < v 且 arr[i,r] > v,i为当前遍历到的元素
        int j = l;

        //从第二个元素开始遍历
        for (int i = l + 1; i <= r; i++) {
            //如果当前遍历到的元素<v,则将当前元素与j的后一个元素交换
            if (arr[i] < v){
                ArrayUtils.swapArrayElement(arr,i,++j);
            }
        }

        //将arr[j] 与 arr[l]交换
        ArrayUtils.swapArrayElement(arr, l, j);
        return j;
    }

    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(10, -100, 100);
        ArrayUtils.printArray(arr);
        quickSort(arr);
        ArrayUtils.printArray(arr);
    }
}
