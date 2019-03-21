package william.algorithm.sort.advanced;

import william.algorithm.util.ArrayUtils;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/21 12:58
 * @Description:查询数组中第k小的元素 基于快速排序的partition思想, 时间复杂度O(n)
 */
public class SelectKth {
    private static int selectKth(int[] arr, int k) {
        return doSelectKth(arr, 0, arr.length - 1, k);
    }

    /**
     * 查询中数组arr[l,r]中的第k小的元素
     */
    private static int doSelectKth(int[] arr, int l, int r, int k) {
        //退出条件:查找到了最后一个元素
        if (l >= r) {
            return arr[l];
        }

        //首先进行一次partition操作,找到数组中第p大的元素
        int p = partition(arr, l, r);

        //如果p的位置就是k,则直接返回
        if (k == p){
            return arr[p];
        }

        //如果k<p,则需要在前半部分数组中继续查找第k大的元素
        if (k < p){
            return doSelectKth(arr,l,p - 1,k);
        }

        //否则k > p,则在后半部分数组中查找
        return doSelectKth(arr,p + 1,r,k);
    }

    private static int partition(int[] arr, int l, int r) {
        int randomIndex = ThreadLocalRandom.current().nextInt(l, r + 1);
        ArrayUtils.swapArrayElement(arr, l, randomIndex);
        int v = arr[l];

        //arr[l + 1,j] <= v arr[i,r] >= v
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < v) {
                ArrayUtils.swapArrayElement(arr, i, ++j);
            }
        }

        ArrayUtils.swapArrayElement(arr, l, j);
        return j;
    }

    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(10, -100, 100);
        Arrays.sort(arr);
        ArrayUtils.printArray(arr);
        int k = selectKth(arr, 5);
        System.err.println(k);
    }
}
