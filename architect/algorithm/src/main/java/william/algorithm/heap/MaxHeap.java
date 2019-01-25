package william.algorithm.heap;

import william.algorithm.util.ArrayUtils;

import java.util.Arrays;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/13 10:52
 * @Description:使用数组实现的大顶二叉堆，插入、删除数据的时间复杂度均为O(logn)
 * parent(i) = i / 2
 * left(i) = i * 2
 * right(i) = i * 2 + 1
 */
public class MaxHeap {
    private int[] data; //data range [1,capacity]

    private int capacity;

    private int size;

    public MaxHeap(int capacity){
        this.data = new int[capacity + 1];
        this.capacity = capacity;
        this.size = 0;
    }

    public MaxHeap(int[] arr){
        int len = arr.length;
        data = new int[len + 1];
        size = len;
        capacity = len;

        for (int i = 0;i < len;i++){
            data[i + 1] = arr[i];
        }

        heapify();
    }

    public void insert(int element){
        if (size >= capacity){
            enlarge();
        }

        data[++size] = element;
        shiftUp(size);
    }

    public int getLargest(){
        int retVal = data[1];
        ArrayUtils.swapArrayElement(data,1,size);
        size--;
        shiftDown(1);
        return retVal;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size <= 0;
    }

    private void shiftDown(int i) {
        while (i * 2 <= size){
            //Find Largest Child of Node i
            int max = i * 2;
            if (max + 1 <= size){
                int right = max + 1;
                if (data[right] > data[max]){
                    max = right;
                }
            }
            if (data[max] <= data[i]){
                break;
            }
            ArrayUtils.swapArrayElement(data,i,max);
            i = max;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    private void enlarge(){
        int[] tmp = Arrays.copyOf(data,size * 2);
        data = tmp;
    }

    private void shiftUp(int i){
        while (i > 1 && data[i] > data[i / 2]){
            ArrayUtils.swapArrayElement(data,i,i / 2);
            i /= 2;
        }
    }

    /**
     * Convert a Array to Max-Heap
     * The Index of Last Non-Leaf Node is size / 2
     */
    private void heapify() {
        for (int i = size / 2;i >= 1;i--){
            shiftDown(i);
        }
    }

}
