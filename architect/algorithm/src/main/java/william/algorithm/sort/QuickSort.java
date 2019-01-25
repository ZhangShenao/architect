package william.algorithm.sort;

import william.algorithm.util.ArrayUtils;
import william.algorithm.util.RandomArrayGenerator;
import java.util.Arrays;
import java.util.Random;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/9 15:43
 * @Description:快速排序，平均时间复杂度O(n*logn)，最坏时间复杂度O(n*n)，但是出现最坏情况的几率极低
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = RandomArrayGenerator.generateRandomArray(10, -100, 100);
        System.err.println(Arrays.toString(array));
        quickSort(array);
        System.err.println(Arrays.toString(array));
    }

    public static void quickSort(int[] arr){
//        doQuickSort(arr,0,arr.length - 1);
        doTripleQuickSort(arr,0,arr.length - 1);
    }

    private static void doTripleQuickSort(int[] arr,int l,int r){
        if (l >= r){
            return;
        }
        int pivot = selectRandomIndex(l, r);
        ArrayUtils.swapArrayElement(arr, l, pivot);
        int v = arr[l];
        //[l,lt] < v [lt + 1,i] == v [gt,r] > v
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        while(i < gt){
            if (arr[i] < v){
                ArrayUtils.swapArrayElement(arr, lt + 1, i);
                i++;
                lt++;
            }
            else if (arr[i] == v){
                i++;
            }
            else {
                ArrayUtils.swapArrayElement(arr,i,gt - 1);
                gt--;
            }
        }
        ArrayUtils.swapArrayElement(arr, l, lt);
        doTripleQuickSort(arr,l,lt - 1);
        doTripleQuickSort(arr,gt,r);

    }

    private static void doQuickSort(int[] arr,int l,int r){
        if (l >= r){
            return;
        }
        int pivot = singlePartition(arr, l, r);
        doQuickSort(arr,l,pivot );
        doQuickSort(arr,pivot + 1,r);
    }

    private static int doublePartition(int[] arr, int l, int r){
        int pivot = selectRandomIndex(l, r);
        ArrayUtils.swapArrayElement(arr, l, pivot);
        int i = l + 1;
        int j = r;

        while (true){
            while (i <= r && arr[i] < arr[l]){
                i++;
            }

            while (j >= l + 1 && arr[j] > arr[l]){
                j--;
            }

            if (i >= j){
                break;
            }
            ArrayUtils.swapArrayElement(arr, i, j);

        }
        ArrayUtils.swapArrayElement(arr, l, j);
        return j;
    }

    private static int singlePartition(int[] arr, int l, int r){
        int pivot = arr[l];
        //[l + 1,i] < pivot,[i + 1,j) > pivot
        int i = l;
        for (int j = l + 1;j <= r;j++){
            if (arr[j] < pivot){
                ArrayUtils.swapArrayElement(arr, ++i, j);
            }
        }
        ArrayUtils.swapArrayElement(arr, l, i);
        return i;
    }

    private static int selectRandomIndex(int l,int r){
        return new Random().nextInt(r - l + 1) + l;
    }
}
