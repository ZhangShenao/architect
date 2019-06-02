package william.ds.queue;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/2 10:16
 * @Description:基于循环数组实现的队列
 */
public class ArrayQueue<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 10;

    //使用数组保存元素
    private E[] data;

    //记录队列中元素的数量
    private int size;

    //队头元素的索引
    private int head;

    //队尾元素的索引
    private int tail;

    public ArrayQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive!!");
        }
        this.data = (E[]) new Object[capacity];
        this.size = 0;
        this.head = this.tail = -1;
    }

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
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
    public void offer(E e) {
        //offer to empty queue
        if (isEmpty()) {
            data[0] = e;
            head = tail = 0;
            ++size;
            return;
        }

        if (size == data.length) {
            grow();
        }

        tail = (tail + 1) % data.length;
        data[tail] = e;
        ++size;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = data[head];
        head = (head + 1) % data.length;
        --size;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return data[head];
    }

    //将数组大小扩容为原来的2倍
    private void grow() {
        int len = data.length;
        E[] tmp = (E[]) new Object[len * 2];

        //拷贝元素,并重置队头、队尾索引
        for (int i = 0; i < size; i++) {
            tmp[i] = data[(head + i) % len];
        }
        head = 0;
        tail = size - 1;
        data = tmp;
    }
}
