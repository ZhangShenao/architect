package william.ds.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/10 13:26
 * @Description:基于数组实现的大顶堆
 */
public class MaxHeap<T extends Comparable<T>> {
    //使用ArrayList保存元素,底层就是数组,省略了手动扩容的处理
    private List<T> data;

    public MaxHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    //基于数组构建一个大顶堆
    public MaxHeap(T[] arr){
        if (arr == null || arr.length == 0){
            throw new IllegalArgumentException("array must not be empty!!");
        }
        data = new ArrayList<>(arr.length);
        data.addAll(Arrays.asList(arr));
        //进行heapify操作
        heapify(arr.length);
    }

    //时间复杂度O(n*logn)
    private void heapify(int len){
        //从第一个非叶子节点开始,依次进行shiftDown操作
        for (int i = len / 2 - 1;i >= 0;i--){
            shiftDown(i);
        }
    }

    //向堆中插入元素,时间复杂度O(logn)
    public void insert(T item) {
        //向在数组末尾插入
        data.add(data.size(),item);

        //进行shiftUp操作
        shiftUp(data.size() - 1);
    }

    //对索引为idx处的元素进行shiftUp操作
    private void shiftUp(int idx) {
        //如果idx处的元素比父节点大,则将其与父节点进行交换,否则结束shiftUp
        int parent = parentIndex(idx);
        while (parentIndex(idx) > 0) {
            if (data.get(idx).compareTo(data.get(parentIndex(idx))) <= 0) {
                return;
            }
            swap(idx, parent);
            idx = parent;
            parent = parentIndex(idx);
        }
    }

    //获取堆中最大的元素,即堆顶元素,时间复杂度O(1)
    public T max(){
        if (isEmpty()){
            return null;
        }
        return data.get(0);
    }

    //获取堆中最大元素,并将其删除,时间复杂度O(logn)
    public T extractMax(){
        if (isEmpty()){
            return null;
        }
        //获取堆顶元素
        T max = data.get(0);

        //将堆中最后一个元素与堆顶元素交换
        T last = data.get(data.size() - 1);
        data.set(0,last);
        data.remove(data.size() - 1);

        //对新的堆顶元素进行shiftDown操作
        shiftDown(0);
        return max;
    }

    //对索引为idx处的元素进行shiftDown操作
    private void shiftDown(int idx){
        //如果idx处的元素为叶子节点,则结束shiftDown
        while (leftChildIndex(idx) < data.size()){
            //获取左、右子节点中最大的那个
            int max = idx;
            if (data.get(leftChildIndex(idx)).compareTo(data.get(idx)) > 0){
                max = leftChildIndex(idx);
            }
            if (rightChildIndex(idx) < data.size() && data.get(rightChildIndex(idx)).compareTo(data.get(max)) > 0){
                max = rightChildIndex(idx);
            }

            //如果根节点已经为左、右子树中最大的,则结束shiftDown
            if (max == idx){
                return;
            }

            //将根节点与最大的子节点进行交换
            swap(idx,max);
            idx = max;
        }
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    //计算父节点的索引
    private int parentIndex(int idx) {
        return (idx - 1) / 2;
    }

    //计算左子节点的索引
    private int leftChildIndex(int idx) {
        return (idx * 2) + 1;
    }

    //计算右子节点的索引
    private int rightChildIndex(int idx) {
        return (idx * 2) + 2;
    }

    private void swap(int i, int j) {
        T tmp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, tmp);
    }
}
