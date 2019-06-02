package william.ds.stack;

import java.util.Arrays;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/2 09:49
 * @Description:基于数组实现的栈
 */
public class ArrayStack<E> implements Stack<E> {
    private static final int DEFAULT_CAPACITY = 10;

    //使用数组保存元素
    private E[] data;

    //记录栈中元素的数量
    private int size;

    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive!!");
        }
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }


    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(E e) {
        if (size == data.length) {
            grow();
        }
        data[size++] = e;
    }


    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        return data[--size];
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return data[size - 1];
    }

    //将数组元素扩大为原来的2倍
    private void grow() {
        data = Arrays.copyOf(data, data.length * 2);
    }
}
