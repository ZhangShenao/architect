package william.algorithm.sort.advanced;

import william.algorithm.util.ArrayUtils;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/20 10:13
 * @Description:双路快速排序 单路快速排序的弊端:当存在大量重复元素时,与基准值相等的元素都被分在了同一个数组中,造成两个数组元素数量的极不均匀,极端情况下,时间复杂度会退化成O(n*n)
 * 因此使用双路快速排序进行优化,将与基准值相同的元素均匀地分配中两个数组中,就可以解决此问题
 */
public class TwoRouteQuickSort {
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
     * 双路快排
     * 对数组arr[l,r]范围进行partition操作,返回索引p,使得arr[l,p-1] < arr[p] 且 arr[p+1,r] > arr[p]
     */
    private static int partition(int[] arr, int l, int r) {
        //随机选取一个元素作为基准值
        int randomIndex = ThreadLocalRandom.current().nextInt(l, r + 1);

        //将随机选出的元素与第一个元素进行交换
        ArrayUtils.swapArrayElement(arr, l, randomIndex);

        int v = arr[l];

        //索引定义
        //arr[l + 1,i] <= v arr[j,r] >= v
        int i = l + 1;
        int j = r;

        //从i和j两个方向开始遍历数组,找到第一个arr[i] >= v 和 arr[j] <= v 的元素,进行交换
        while (true) {
            while (i <= r && arr[i] < v) {
                i++;
            }
            while (j > l && arr[j] > v) {
                j--;
            }
            if (i >= j) {
                break;
            }
            ArrayUtils.swapArrayElement(arr, i, j);
            i++;
            j--;
        }

        //遍历结束后,j是<=v的最后一个元素的位置,交换arr[l]和arr[j],并返回j
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
