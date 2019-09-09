package william.enhance.heap;

import william.utils.AlgorithmUtils;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/19 16:23
 * @Description:基于数组实现的最大堆 从数组索引1开始
 * 插入、获取最大元素的时间复杂度均为O(nlogn)
 */
public class ArrayMaxHeap<T extends Comparable<T>> implements MaxHeap<T> {
    //使用数组保存堆中的元素,从索引1开始,有效范围[1,size]
    private T[] data;

    //记录堆中元素数量
    private int size;

    //记录堆的容量
    private int capacity;

    public ArrayMaxHeap(int capacity) {
        this.capacity = capacity;
        this.data = (T[]) new Comparable[capacity + 1];
        this.size = 0;
    }

    public ArrayMaxHeap(T[] arr) {
        this(arr.length);

        //执行heapify操作,直接通过数组建堆
        for (int i = 0; i < arr.length; i++) {
           this.data[i + 1] = arr[i];
        }
        this.size = arr.length;
        heapify();
    }

    private void heapify() {
        //从数组的最后一个非叶子节点开始,直到第一个节点,依次执行shiftDown操作
        //最后一个非叶子节点的索引=size/2
        for (int i = size / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public T extractMax() {
        if (isEmpty()) {
            return null;
        }

        //直接获取第一个元素
        T t = data[1];

        //将数组最后一个元素移动到首位
        data[1] = data[size--];

        //对数组第一个元素执行shiftDown
        shiftDown(1);

        return t;
    }

    private void shiftDown(int i) {
        //如果当前节点小于子节点,则将当前节点与子节点中最大的进行交换
        while (leftChild(i) <= size) {
            int max = i;
            if (data[leftChild(i)].compareTo(data[i]) > 0) {
                max = leftChild(i);
            }
            if (rightChild(i) <= size && data[rightChild(i)].compareTo(data[max]) > 0) {
                max = rightChild(i);
            }
            if (max == i) {
                return;
            }
            AlgorithmUtils.swap(data, i, max);
            i = max;
        }
    }

    @Override
    public void insert(T t) {
        //容量校验
        if (size == capacity) {
            throw new IllegalStateException("堆已满!!");
        }

        //将元素放入数组末尾
        data[++size] = t;

        //对最后一个元素执行shiftUp操作
        shiftUp(size);
    }

    private void shiftUp(int i) {
        //如果当前节点大于其父节点,则将当前节点与父节点交换
        while (i > 1 && data[i].compareTo(data[parent(i)]) > 0) {
            AlgorithmUtils.swap(data, i, parent(i));
            i = parent(i);
        }
    }

    private int parent(int i) {
        return (i / 2);
    }

    private int leftChild(int i) {
        return i * 2;
    }

    private int rightChild(int i) {
        return i * 2 + 1;
    }

    public static void main(String[] args) {
        int capacity = 20;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        MaxHeap<Integer> heap = new ArrayMaxHeap<>(20);

        for (int i = 0; i < capacity; i++) {
            heap.insert(random.nextInt(-100, 101));
        }

        StringBuilder builder = new StringBuilder();
        while (!heap.isEmpty()) {
            builder.append(heap.extractMax()).append(" , ");
        }
        System.err.println(builder.toString());

    }

}
