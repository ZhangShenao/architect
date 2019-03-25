package william.algorithm.sort.advanced;

import william.algorithm.util.ArrayUtils;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/24 17:44
 * @Description:对数组进行原地堆排序 首先对数组进行一次Heapify操作, 将其转换成一个最大堆, 然后将第一个元素(最大元素)与数组末尾的元素交换, 接下来从倒数第二个元素开始重复次过程, 知道遍历到仅剩一个元素, 这样整个数组就排好序了
 * 时间复杂度O(n*logn)
 * 数组元素从下标0开始
 * 对于索引i,其父节点索引parent(i) = (i - 1) / 2,左子节点的索引leftChild(i) = i * 2 + 1,右子节点的索引rightChild(i) = i * 2 + 2,
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(10, -100, 100);
        ArrayUtils.printArray(arr);
        heapSort(arr);
        ArrayUtils.printArray(arr);
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;
        //首先进行Heapify操作
        for (int i = firstNonLeafIndex(n - 1); i >= 0; i--) {
            shiftDown(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            ArrayUtils.swapArrayElement(arr, 0, i);
            shiftDown(arr, i, 0);
        }
    }

    private static int parentIndex(int i) {
        return (i - 1) / 2;
    }

    private static int leftChildIndex(int i) {
        return i * 2 + 1;
    }

    private static int rightChildIndex(int i) {
        return i * 2 + 2;
    }

    private static int firstNonLeafIndex(int n) {
        return (n - 1) / 2;
    }

    /**
     * 在数组的arr[0,n)范围内,对下标i处的元素进行shiftDown操作
     */
    private static void shiftDown(int[] arr, int n, int i) {
        while (2 * i + 1 < n) {
            int j = 2 * i + 1;
            if (j + 1 < n && arr[j + 1] > arr[j]){
                j += 1;
            }

            if (arr[i] >= arr[j]) {
                break;
            }

            ArrayUtils.swapArrayElement(arr, i, j);
            i = j;
        }
    }
}
