package william.ds.list;

import java.util.Arrays;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/31 10:06
 * @Description:基于数组实现的线性表
 */
public class ArrayList<E> implements List<E> {
    //默认初始化容量
    private static final int DEFAULT_CAPACITY = 10;

    //底层使用数组保存元素
    private E[] data;

    //记录当前线性表中元素个数
    private int size;

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive!!");
        }
        this.size = 0;
        this.data = (E[]) new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(E e) {
        return (indexOf(e) >= 0);
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public void set(int index, E e) {
        checkIndex(index);
        data[index] = e;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index out of bounds: " + index);
        }
        if (size == data.length){
            grow();
        }

        //将[index,size-1]范围内的元素后移一位
        for (int i = size - 1;i >= index;i++){
            data[i + 1] = data[i];
        }
        data[index] = e;
        ++size;
    }


    @Override
    public E remove(int index) {
        checkIndex(index);

        //将[index+1,size-1]范围内的元素迁移一位
        E e = data[index];
        for (int i = index + 1;i < size;i++){
            data[i - 1] = data[i];
        }
        data[size--] = null;
        return e;
    }

    @Override
    public void addLast(E e) {
        add(size,e);
    }

    @Override
    public void addFirst(E e) {
        add(0,e);
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("ArrayList [");
        for (int i = 0;i < size - 1;i++){
            s.append(data[i]).append(",");
        }
        return s.append(data[size - 1]).append("]").toString();
    }

    private void checkIndex(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index out of bounds: " + index);
        }
    }

    private void grow() {
        //将数组容量扩大为当前的2倍
        data = Arrays.copyOf(data,data.length * 2);
    }

}
