package william.algorithm.sort.advanced;

import william.algorithm.util.ArrayUtils;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/17 10:38
 * @Description:基本归并排序 先将整个数组均分为左右两个部分, 将左右两部分分别排序, 然后再合并两个分别有序的数组, 使得整个数组有序
 * 时间复杂度O(n*longn)
 * 非原地排序：空间复杂度O(n)
 */
public class BasicMergeSort {
    public static void mergeSort(int[] arr) {
        doMergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 对数组arr的[l,r]范围内的元素进行归并排序
     */
    private static void doMergeSort(int[] arr, int l, int r) {
        //当数组中仅有一个元素或没有元素时,直接返回
        if (l >= r) {
            return;
        }

        //对数组的左右两部分分别进行归并排序
        int mid = (l + r) / 2;
        doMergeSort(arr, l, mid);
        doMergeSort(arr, mid + 1, r);

        //将排好序的左右两个数组合并为一个数组
        doMerge(arr, l, mid + 1, r);
    }

    /**
     * 将两个有序数组分别为arr[lStart,rStart - 1] 和 arr[rStart,rEnd],合并为一个有序数组
     */
    private static void doMerge(int[] arr, int lStart, int rStart, int rEnd) {
        int lEnd = rStart - 1;

        //先声明一个临时数组,长度为(rEnd - lStart + 1)
        int size = (rEnd - lStart + 1);
        int[] tmp = new int[size];

        //声明索引
        int l = lStart;     //左边元素的索引
        int r = rStart;     //右边元素的索引

        //将左右两个数组按照顺序,合并到tmp中
        for (int i = 0; i < size; ) {
            //如果左边数组已经合并完,则直接合并右边数组
            if (l > lEnd) {
                while (r <= rEnd) {
                    tmp[i++] = arr[r++];
                }
                break;
            }

            //右边数组同理
            if (r > rEnd) {
                while (l <= lEnd) {
                    tmp[i++] = arr[l++];
                }
                break;
            }

            //如果左右数组都未遍历完,则每次去两边数组中当前最小的元素,放入tmp中
            if (arr[r] < arr[l]) {
                tmp[i++] = arr[r++];
            } else {
                tmp[i++] = arr[l++];
            }
        }

        //将tmp拷贝到原数组
        for (int i = 0; i < size; i++) {
            arr[lStart + i] = tmp[i];
        }

        tmp = null;     //help gc

    }

    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(10, -100, 100);
        ArrayUtils.printArray(arr);
        mergeSort(arr);
        ArrayUtils.printArray(arr);
    }
}
