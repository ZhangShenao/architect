package william.algorithm.sort.advanced;

import william.algorithm.util.ArrayUtils;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/20 08:30
 * @Description:随机快速排序,在基本快速排序的基础上,每次随机选取一个元素作为基准值,这样极大地减小了在近乎有序的情况下时间复杂度退化成O(n*n)的概率
 */
public class RandomQuickSort {
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
        //随机选取一个元素作为基准值
        int randomIndex = ThreadLocalRandom.current().nextInt(l,r + 1);

        //将随机选出的元素与第一个元素进行交换
        ArrayUtils.swapArrayElement(arr,l,randomIndex);

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
